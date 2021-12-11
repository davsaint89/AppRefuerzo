package com.example.apprefuerzo.utiles;


import android.util.Patterns;

public class ValidarCorreo {

    public static  boolean validar(String correo){
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches();
    }
}
