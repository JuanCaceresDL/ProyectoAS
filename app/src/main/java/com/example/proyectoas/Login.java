package com.example.proyectoas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Login extends AppCompatActivity {

    EditText l_correo, l_password;
    Button b_logear;
    String slcorreo, slpassword, url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        l_correo = findViewById(R.id.correologin);
        l_password= findViewById(R.id.contraseñalogin);
        b_logear=findViewById(R.id.buttonlog);

        b_logear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(l_correo.getText().toString().matches("") || l_password.getText().toString().matches("") ) {
                    Toast.makeText(Login.this, "Llenar los campos", Toast.LENGTH_SHORT).show();
                }else{
                    slcorreo=l_correo.getText().toString();
                slpassword=l_password.getText().toString();
                url="http://192.168.178.150/android/login.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Login.this, response.trim(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("correo",slcorreo);
                        params.put("contraseña",slpassword);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                requestQueue.add(stringRequest);

                Intent intent_home = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent_home);
            }
        }});
    }

    public  void registro(View view){
        startActivity(new Intent(getApplicationContext(),Registro.class));
        finish();
    }
}