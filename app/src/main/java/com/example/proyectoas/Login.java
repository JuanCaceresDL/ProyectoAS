package com.example.proyectoas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText l_correo, l_password;
    Button b_logear;
    String slcorreo, slpassword,sId , url;

    private static final String KEY_ID = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        l_correo = findViewById(R.id.correologin);
        l_password= findViewById(R.id.contraseñalogin);
        b_logear=findViewById(R.id.buttonlog);
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("estado", Context.MODE_PRIVATE);
        sId = sharedPreferences.getString(KEY_ID,null);
        if (sId != null) {
            Intent intent_home = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent_home);
        }
        AwesomeValidation mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        b_logear.setOnClickListener(view -> logeando(mAwesomeValidation.validate()));
    }

    public void logeando(boolean validacion){
        Loading loading = new Loading(this);
        loading.showDialog("Ingresando");
        if (validacion){
            if(l_correo.getText().toString().matches("") || l_password.getText().toString().matches("") ) {
                Toast.makeText(Login.this, "Llenar los campos", Toast.LENGTH_SHORT).show();
            }else{
                slcorreo=l_correo.getText().toString();
                slpassword=l_password.getText().toString();
                url="http://192.168.1.36/android/proy2/login.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.hideDialog();
                        if (!response.equals("-1")){
                            SharedPreferences sharedPreferences;
                            sharedPreferences = getSharedPreferences("estado", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_ID, response);
                            editor.apply();
                            Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                            Intent intent_home = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent_home);
                            finish();
                        }
                        else{
                            SharedPreferences sharedPreferences;
                            sharedPreferences = getSharedPreferences("estado", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_ID, null);
                            editor.apply();
                            Toast.makeText(Login.this, "Algo falló", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.hideDialog();
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
            }
        }
    }

    public  void registro(View view){
        startActivity(new Intent(getApplicationContext(),Registro.class));
        finish();
    }
}