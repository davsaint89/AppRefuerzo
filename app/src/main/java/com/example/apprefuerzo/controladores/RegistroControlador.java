package com.example.apprefuerzo.controladores;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.apprefuerzo.MainActivity;
import com.example.apprefuerzo.modelos.Usuario;
import com.example.apprefuerzo.vistas.RegistroActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class RegistroControlador {
    public static void registro(Context contexto, String nombre, String correo, String contraseña) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            guardarUsuario(contexto, nombre, correo);
                        }else{
                            Toast.makeText(contexto, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    private static void guardarUsuario(Context contexto, String nombre, String correo) {

        try{
            FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
            String id= firebaseUser.getUid();
            long tiempoRegistro = firebaseUser.getMetadata().getCreationTimestamp();
            Usuario usuario = new Usuario(id, nombre, correo, "", tiempoRegistro);
            FirebaseFirestore.getInstance()
                    .collection(ConstantesFirebase.USUARIOS)
                    .document(id)
                    .set(usuario, SetOptions.merge())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Intent intent = new Intent(contexto, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                contexto.startActivity(intent);
                            }else{
                                Toast.makeText(contexto, "Error al guardar datos de usuario", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }catch(NullPointerException e){
            e.getCause();
        }

    }
}
