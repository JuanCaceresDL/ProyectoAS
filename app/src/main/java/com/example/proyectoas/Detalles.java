package com.example.proyectoas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyectoas.Fragments.CommentFragment;
import com.example.proyectoas.Fragments.DescriptFragment;
import com.example.proyectoas.Fragments.TuristFragment;
import com.example.proyectoas.databinding.ActivityDetallesBinding;

public class Detalles extends AppCompatActivity {

    Integer mId;
    ActivityDetallesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetallesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        mId = (Integer) bundle.get("idlug");
        System.out.println(mId);

        replaceFragment(new DescriptFragment(mId));
        binding.bottomnav2.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.descripcion:
                    replaceFragment(new DescriptFragment(mId));
                    break;
                case R.id.comentarios:
                    replaceFragment(new CommentFragment(mId));
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layoutdetalles, fragment);
        fragmentTransaction.commit();
    }

}
