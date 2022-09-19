package com.example.proyectoas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    EditText t_nombre, t_apellido, t_correo, t_nacionalidad, t_telefono, t_password;
    Button b_insertar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        t_nombre=findViewById(R.id.nombre);
        t_apellido=findViewById(R.id.apellido);
        t_correo=findViewById(R.id.correo);
        t_nacionalidad=findViewById(R.id.nacionalidad);
        t_telefono=findViewById(R.id.telefono);
        t_password=findViewById(R.id.contraseña);
        b_insertar=findViewById(R.id.btnpalogin);

        b_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarDatos();
            }
        });
    }

    private void insertarDatos() {
        final String nombre=t_nombre.getText().toString().trim();
        final String apellido=t_apellido.getText().toString().trim();
        final String correo=t_correo.getText().toString().trim();
        final String nacionalidad=t_nacionalidad.getText().toString().trim();
        final String telefono=t_telefono.getText().toString().trim();
        final String password=t_password.getText().toString().trim();

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");

        if (nombre.isEmpty()){
            t_nombre.setError("Complete el campo");
            return;
        }else if (apellido.isEmpty()){
            t_apellido.setError("Complete el campo");
            return;
        }else if (correo.isEmpty()){
            t_correo.setError("Complete el campo");
            return;
        }else if (nacionalidad.isEmpty()){
            t_nacionalidad.setError("Complete el campo");
            return;
        }else if (telefono.isEmpty()){
            t_telefono.setError("Complete el campo");
            return;
        }
        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/android/save.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("El usuario fue creado exitosamente")) {
                        Toast.makeText(Registro.this, "datos insertados correctamente", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Intent intent = new Intent(Registro.this, Login.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Registro.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Toast.makeText(Registro.this, "Los datos no se insertaron correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Registro.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String>params=new HashMap<>();
                    params.put("nombre",nombre);
                    params.put("apellido",apellido);
                    params.put("correo",correo);
                    params.put("nacionalidad",nacionalidad);
                    params.put("telefono",telefono);
                    params.put("contraseña",password);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(Registro.this);
            requestQueue.add(request);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public  void login(View view){
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}