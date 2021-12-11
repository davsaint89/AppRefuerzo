package com.example.apprefuerzo.controladores;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.apprefuerzo.MainActivity;
import com.example.apprefuerzo.vistas.RecuperarContrasenaActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarContraControlador {
    public static void login(Activity activity, String correo) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(correo)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            activity.finish();
                            Toast.makeText(activity, "Se ha enviado un correo para cambiar contraseña", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity, "Error al recuperar contraseña", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
