package com.fuentesbuenosvinosguillermo.mariobros.PantallaInicio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fuentesbuenosvinosguillermo.mariobros.MainActivity;
import com.fuentesbuenosvinosguillermo.mariobros.R;

/**
 * SplashActivity es la actividad inicial que se muestra al abrir la aplicación.
 * Se utiliza para mostrar una pantalla de presentación (splash screen) durante unos segundos
 * antes de iniciar la actividad principal de la aplicación (MainActivity).
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Activa el modo Edge-to-Edge para una experiencia inmersiva
        setContentView(R.layout.activity_splash); // Establece el diseño para la actividad de presentación

        /*
         * Se utiliza un Handler para mostrar la pantalla durante un periodo de tiempo
         * específico antes de iniciar la actividad principal.
         *
         * Sintaxis antigua:
         * new Handler().postDelayed(new Runnable() {
         *     @Override
         *     public void run() {
         *         // Acción a realizar después del retraso
         *     }
         * });
         *
         * Sintaxis moderna (utiliza expresiones lambda):
         * new Handler().postDelayed(() -> {
         *     Intent intent = new Intent(SplashActivity.this, MainActivity.class);
         *     startActivity(intent);
         *     finish();
         * }, 3000); // Retraso de 3000 milisegundos (3 segundos)
         *
         * El método new Handler().postDelayed se utiliza para programar la ejecución de
         * una acción después de un retraso. La sintaxis moderna hace que el código sea más
         * conciso y fácil de leer al encapsular la lógica en una función de callback.
         */
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent); // Inicia la actividad principal
            finish(); // Finaliza la actividad de presentación para que no se pueda volver a ella
        }, 3000);

        /*
         * Configura la aplicación de márgenes de las ventanas para que la vista principal
         * se adapte a los márgenes del sistema, como la barra de estado y la barra de navegación.
         */
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets; // Devuelve los insets aplicados
        });
    }
}
