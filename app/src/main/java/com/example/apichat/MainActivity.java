package com.example.apichat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static NavController navControllerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navControllerMain = Navigation.findNavController(this, R.id.fragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navControllerMain.navigateUp() || super.onSupportNavigateUp();
    }
}