package com.example.proyectoas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    EditText t_nombre, t_apellido, t_correo, t_nacionalidad, t_telefono, t_password,t_passwordconf;
    Button b_insertar;
    String snombre,sapellido,scorreo, snacionalidad,stelefono,spassword,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        t_nombre=(EditText)findViewById(R.id.nombre);
        t_apellido=(EditText)findViewById(R.id.apellido);
        t_correo=(EditText)findViewById(R.id.correo);
        t_nacionalidad=(EditText)findViewById(R.id.nacionalidad);
        t_telefono=(EditText)findViewById(R.id.telefono);
        t_password=(EditText)findViewById(R.id.contraseña);
        t_passwordconf=(EditText)findViewById(R.id.contraseñaconf);
        b_insertar=(Button)findViewById(R.id.btnpalogin);

        AwesomeValidation mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        mAwesomeValidation.addValidation(t_passwordconf,t_password, "las contraseñas no son iguales");

        b_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (mAwesomeValidation.validate()){
                   snombre = t_nombre.getText().toString();
                   sapellido = t_apellido.getText().toString();
                   scorreo = t_correo.getText().toString();
                   snacionalidad = t_nacionalidad.getText().toString();
                   stelefono = t_telefono.getText().toString();
                   spassword = t_password.getText().toString();
                   url = "http://192.168.178.150/android/save.php";
                   StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                           new Response.Listener<String>() {
                               @Override
                               public void onResponse(String response) {
                                   Toast.makeText(Registro.this, response.trim(), Toast.LENGTH_SHORT).show();
                               }
                           },
                           new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {
                                   Toast.makeText(Registro.this,error.toString(),Toast.LENGTH_SHORT).show();
                               }
                           }){
                       @Nullable
                       @Override
                       protected Map<String, String> getParams() {
                           Map<String,String> params = new HashMap<String, String>();
                           params.put("nombre",snombre);
                           params.put("apellido",sapellido);
                           params.put("correo",scorreo);
                           params.put("nacionalidad",snacionalidad);
                           params.put("telefono",stelefono);
                           params.put("contraseña",spassword);
                           return params;
                       }
                   };
                RequestQueue requestQueue = Volley.newRequestQueue(Registro.this);
                requestQueue.add(stringRequest);
                   Intent intent_log = new Intent(getApplicationContext(),Login.class);
                   startActivity(intent_log);
               }
            }
        });
    }

    public  void login(View view){
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}