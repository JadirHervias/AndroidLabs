package com.tecsup.apptest;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("    >>> El activity se ha creado y está detenido - onCreate()");
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    @Override
    public void onStart() {
        super.onStart();
        System.out.println("    >>> El activity está pausado - onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("    >>> El activity está en marcha - onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("    >>> El activity está en pausado de nuevo - onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("    >>> El activity está detenido de nuevo - onStop()");
    }

    @Override
    public void onDestroy() {
        System.out.println("    >>> El activity ya no existe - onDestroy()");
        super.onDestroy();
    }

}
