package com.example.proyectoas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyectoas.Fragments.CloseFragment;
import com.example.proyectoas.Fragments.FavoriteFragment;
import com.example.proyectoas.Fragments.ProfileFragment;
import com.example.proyectoas.Fragments.RestauFragment;
import com.example.proyectoas.Fragments.TuristFragment;
import com.example.proyectoas.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    String uId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("estado", Context.MODE_PRIVATE);
        uId = sharedPreferences.getString("id",null);
        if (uId == null) {
            Intent intent_log = new Intent(getApplicationContext(),Login.class);
            startActivity(intent_log);
        }
        Integer nuId = Integer.parseInt(uId);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new TuristFragment());

        binding.bottomnav.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.turistico:
                    replaceFragment(new TuristFragment());
                    break;
                case R.id.restaurante:
                    replaceFragment(new RestauFragment());
                    break;
                case R.id.favoritos:
                    replaceFragment(new FavoriteFragment(nuId));
                    break;
                case R.id.cerca:
                    replaceFragment(new CloseFragment());
                    break;
                case R.id.perfil:
                    replaceFragment(new ProfileFragment(nuId));
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layouthome, fragment);
        fragmentTransaction.commit();
    }

}
