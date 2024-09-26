package com.example.simplebdd;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.simplebdd.db.DbContactos;

public class InputActivity extends AppCompatActivity {
    EditText txtNombre;
    EditText txtTelefono;
    EditText txtCorreoElectronico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this, "in OnCreate", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input);

        // Habilitar el botón de "volver" en la barra superior
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Sobrescribir onOptionsItemSelected para manejar el botón "volver"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Al hacer clic en el botón "volver", regresa a la actividad anterior
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void escribirBdd(View view){
        DbContactos dbContactos = new DbContactos(InputActivity.this);
        long id = dbContactos.insertarContacto(txtNombre.getText().toString(),txtTelefono.getText().toString(),txtCorreoElectronico.getText().toString());

        if (id > 0){
            Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
            limpiar();
        }else{
            Toast.makeText(this, "ERROR GUARDANDO REGISTRO", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiar(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }


}