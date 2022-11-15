package com.example.proyectoas.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.proyectoas.Api.ApiClient;
import com.example.proyectoas.Api.ItemsApi;
import com.example.proyectoas.Bean.Perfil;
import com.example.proyectoas.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class contrAdapter {
    Context context;
    Dialog dialog;
    private ItemsApi mApi;
    private int usuarioId;

    public contrAdapter(Context context, int usuarioId) {
        this.context = context;
        this.usuarioId = usuarioId;
        this.mApi = ApiClient.getInstance().create(ItemsApi.class);
    }

    public void showDialog(){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.editarcontra);
        Window window = dialog.getWindow();
        window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        EditText contraN = dialog.findViewById(R.id.contraseña1);
        EditText contraConf = dialog.findViewById(R.id.contraseña1conf);
        Button closeButton = dialog.findViewById(R.id.cancelar);
        Button confirmButton = dialog.findViewById(R.id.confirmar);
        closeButton.setOnClickListener(view -> hideDialog());

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contranueva =   contraN.getText().toString();
                String contraconf = contraConf.getText().toString();
                AwesomeValidation mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
                mAwesomeValidation.addValidation(contraN,contraConf, "las contraseñas no son iguales");
                if (mAwesomeValidation.validate()){
                    Call<String> contrasave = mApi.savePass(usuarioId,contranueva);
                    contrasave.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.body().equals("La contraseña fue editada exitosamente")) {
                                hideDialog();
                                Toast.makeText(context, "Sus datos se cambiaron exitosamente", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(context, "algo", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            System.out.println(t.toString());
                            hideDialog();
                        }
                    });
                }
            }
        });

        dialog.create();
        dialog.show();
    }

    public void hideDialog(){
        dialog.dismiss();
    }


}


