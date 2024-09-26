package com.example.simplebdd;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.simplebdd.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void crearDb(View view){
        DbHelper dbHelper = new DbHelper((MainActivity.this));
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null){
            Toast.makeText(this, "db creada", Toast.LENGTH_SHORT).show();
        }else{
                Toast.makeText(this, "db NO creada", Toast.LENGTH_SHORT).show();
        }
    }



    public void nuevoRegistro(View view) throws InterruptedException {
        Intent intent = new Intent(this,InputActivity.class);
        startActivity(intent);
    }

    public void verRegistros(View view){
        Intent intent = new Intent(this, OutputActivity.class);
        startActivity(intent);
    }
}