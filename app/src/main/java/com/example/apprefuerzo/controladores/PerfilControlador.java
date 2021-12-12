package com.example.apprefuerzo.controladores;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class PerfilControlador {
    public static void actualizarDatos(Context contexto, String key, String valor) {

        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        HashMap<String, Object> map=new HashMap<>();
        map.put(key, valor);

        FirebaseFirestore.getInstance()
                .collection(ConstantesFirebase.USUARIOS)
                .document(firebaseUser.getUid())
                .set(map, SetOptions.merge())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(contexto, "datos Actualizados", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(contexto, "Error al intentar actaulizar datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public static void actualizarImagen(Context contexto, Uri uriImagen) {

        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        StorageReference archivoPath= FirebaseStorage.getInstance().getReference()
                .child(ConstantesFirebase.USUARIOS_ALMACENAMIENTO_IMG_PERFIL).child(firebaseUser.getUid()+".jpg");
        archivoPath.putFile(uriImagen)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        archivoPath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                actualizarImagenDB(contexto, uri.toString(), firebaseUser.getUid());

                            }
                        });
                    }
                });
    }

    private static void actualizarImagenDB(Context contexto, String urlImagen, String uusuarioId) {
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        HashMap<String, Object> map=new HashMap<>();
        map.put("imagen", urlImagen);

        FirebaseFirestore.getInstance()
                .collection(ConstantesFirebase.USUARIOS)
                .document(uusuarioId)
                .update(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(contexto, "Imagen guardada", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(contexto, "Error al guardar imagen", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
