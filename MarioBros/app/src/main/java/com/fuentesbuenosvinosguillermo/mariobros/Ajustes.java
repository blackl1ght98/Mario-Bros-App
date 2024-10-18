package com.fuentesbuenosvinosguillermo.mariobros;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

/**
 * La clase Ajustes gestiona la configuración de la aplicación, como el cambio de idioma
 * entre inglés y español.
 */
public class Ajustes extends AppCompatActivity {

    // Switch para cambiar entre los idiomas inglés y español
    Switch languageSwitch;

    /**
     * Método que se llama cuando se crea la actividad Ajustes.
     *
     * @param savedInstanceState Estado guardado de la actividad anterior.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Habilita el modo Edge to Edge en la pantalla
        setContentView(R.layout.activity_ajustes);

        // Inicializa el switch del idioma
        languageSwitch = findViewById(R.id.idioma);

        // Cargar la preferencia de idioma almacenada
        SharedPreferences prefs = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String language = prefs.getString("Spanish", Locale.getDefault().getLanguage());

        // Configura el estado inicial del Switch según la preferencia de idioma
        if (language.equals("es")) {
            languageSwitch.setChecked(false); // Español
        } else {
            languageSwitch.setChecked(true); // Inglés
        }

        // Configura el comportamiento del Switch para cambiar el idioma cuando se selecciona
        languageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setLocale("en"); // Cambiar a inglés
                } else {
                    setLocale("es"); // Cambiar a español
                }
            }
        });

        // Ajusta los márgenes para manejar los bordes de la pantalla (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Método para cambiar el idioma de la aplicación.
     *
     * @param lang Código de idioma ("es" para español, "en" para inglés).
     */
    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale); // Establece el idioma predeterminado
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        config.setLocale(locale); // Aplica la nueva configuración de idioma
        resources.updateConfiguration(config, dm);

        // Guarda la preferencia de idioma en SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences("AppSettings", MODE_PRIVATE).edit();
        editor.putString("Spanish", lang);
        editor.apply();

        // Reinicia la actividad para aplicar los cambios de idioma
        recreate();
    }
}
