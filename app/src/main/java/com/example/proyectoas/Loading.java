package com.example.proyectoas;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

public class Loading {
    Context context;
    Dialog dialog;

    public Loading(Context context) {
        this.context = context;
    }

    public void showDialog(String title){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView progressText = dialog.findViewById(R.id.progress_text);
        progressText.setText(title);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.create();
        dialog.show();
    }

    public void hideDialog(){
        dialog.dismiss();
    }
}
