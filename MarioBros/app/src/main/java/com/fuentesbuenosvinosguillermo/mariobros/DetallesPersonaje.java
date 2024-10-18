package com.fuentesbuenosvinosguillermo.mariobros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * La clase DetallesPersonaje muestra los detalles de un personaje seleccionado
 * en la actividad principal (MainActivity).
 */
public class DetallesPersonaje extends AppCompatActivity {

    // Botón para volver a la actividad anterior
    Button btnVolver;

    /**
     * Método que se llama cuando se crea la actividad DetallesPersonaje.
     *
     * @param savedInstanceState Estado guardado de la actividad anterior.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Configura el modo Edge to Edge en la pantalla
        setContentView(R.layout.activity_detalles_personaje);

        // Recupera los datos del Intent enviado desde la actividad principal
        String nombre = getIntent().getStringExtra("nombre");
        String descripcion = getIntent().getStringExtra("descripcion");
        String habilidades = getIntent().getStringExtra("habilidades");
        String caracteristicas = getIntent().getStringExtra("caracteristicas");
        int imagen = getIntent().getIntExtra("imagen", R.drawable.luigi); // Imagen por defecto si no se envía otra

        // Asigna los datos a los componentes de la interfaz
        TextView textViewDescripcion = findViewById(R.id.descripcion);
        TextView textViewNombre = findViewById(R.id.nombre);
        TextView textViewHabilidades = findViewById(R.id.habilidades);
        TextView textViewCaracteristicas = findViewById(R.id.caracteristicas);
        ImageView imageView = findViewById(R.id.imagen);

        if (textViewDescripcion != null) {
            textViewDescripcion.setText(descripcion);
        }
        if (textViewHabilidades != null) {
            textViewHabilidades.setText(habilidades);
        }
        if (textViewCaracteristicas != null) {
            textViewCaracteristicas.setText(caracteristicas);
        }
        if (textViewNombre != null) {
            textViewNombre.setText(nombre);
        }
        if (imageView != null) {
            imageView.setImageResource(imagen);
        }

        // Configura el botón para volver a la actividad anterior
        btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverAtras(); // Llama al método para volver atrás
            }
        });

        // Ajuste de los márgenes para adaptarse a los bordes de la pantalla (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Método para finalizar la actividad actual y volver a la anterior.
     * Envía un resultado de "OK" a la actividad que inició esta.
     */
    public void volverAtras() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish(); // Finaliza la actividad actual
    }
}
