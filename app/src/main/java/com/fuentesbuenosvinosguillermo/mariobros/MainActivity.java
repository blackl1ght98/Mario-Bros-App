package com.fuentesbuenosvinosguillermo.mariobros;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fuentesbuenosvinosguillermo.mariobros.RecyclerView.Adapter;
import com.fuentesbuenosvinosguillermo.mariobros.RecyclerView.info_personajes;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase principal de la aplicación, que contiene la funcionalidad del RecyclerView
 * y el menú lateral (DrawerLayout).
 */
public class MainActivity extends AppCompatActivity {

    //Con esta variable nos permite manejar y configurar el menu lateral
    private DrawerLayout drawerLayout;

    //Esta variable trabaja en conjunto con la anterior para las acciones del usuario
    private ActionBarDrawerToggle toggle;

    //Varaible que verifica si se ha presionado el botón
    private boolean isEnabled;

    // Lista de personajes global
    private List<info_personajes> personajes;

    /**
     * Método que se llama cuando se crea la actividad principal.
     *
     * @param savedInstanceState Objeto que contiene el estado de la actividad anterior.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuración del RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Creación de la lista de personajes
        personajes = new ArrayList<>();
        personajes.add(new info_personajes("Mario", "Heroe del Reino Champiñon", "Salta alto", "Heroe del Reino Champiñon", R.drawable.mario));
        personajes.add(new info_personajes("Luigi", "Hermano de Mario", "Transformacion", "Heroe del Reino Champiñon", R.drawable.luigi));
        personajes.add(new info_personajes("Peach", "Reina del Reino Champiñon", "Desconocida", "Reina del Reino Champiñon", R.drawable.peach));
        personajes.add(new info_personajes("Toad", "Amigo de Mario", "Salta alto", "Heroe del Reino Champiñon", R.drawable.toad));

        // Asignación del Adapter
        Adapter adapter = new Adapter(getApplicationContext(), personajes);
        recyclerView.setAdapter(adapter);

        // Comprobación para evitar que el RecyclerView se inicie antes de tiempo
        if (recyclerView.getAdapter().getItemCount() > 0) {
            // Mostrar Snackbar al cargar el RecyclerView
            Snackbar.make(findViewById(R.id.main), "Bienvenidos al mundo de Mario", Snackbar.LENGTH_LONG).show();

            // Acción al hacer clic en un personaje
            adapter.setOnItemClickListener(position -> {
                Toast.makeText(MainActivity.this, "Detalles cargados para: " + personajes.get(position).getNombre(), Toast.LENGTH_SHORT).show();

                // Iniciar la pantalla de detalles del personaje seleccionado
                Intent intent = new Intent(MainActivity.this, DetallesPersonaje.class);
                intent.putExtra("nombre", personajes.get(position).getNombre());
                intent.putExtra("descripcion", personajes.get(position).getDescripcion());
                intent.putExtra("habilidades", personajes.get(position).getHabilidades());
                intent.putExtra("caracteristicas", personajes.get(position).getCaracteristicas());
                intent.putExtra("imagen", personajes.get(position).getImagen());
                startActivity(intent);
            });
        } else {
            Log.e("MainActivity", "El RecyclerView está vacío, no se puede establecer el listener.");
        }

        // Configuración de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configuración del DrawerLayout (menú lateral)
        drawerLayout = findViewById(R.id.main);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configuración del NavigationView
        NavigationView navigationView = findViewById(R.id.navigation_view);
        addPersonajesToMenu(navigationView.getMenu());

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // Acción para Home
            } else if (id == R.id.nav_settings) {
                Intent intent = new Intent(this, Ajustes.class);
                startActivity(intent);
            } else if (id >= 0 && id < personajes.size()) {
                String nombrePersonajeSeleccionado = personajes.get(id).getNombre();
                Toast.makeText(MainActivity.this, "Personaje seleccionado: " + nombrePersonajeSeleccionado, Toast.LENGTH_SHORT).show();

                info_personajes personajeSeleccionado = personajes.get(id);

                // Intent para abrir la pantalla de detalles del personaje seleccionado
                Intent intent = new Intent(MainActivity.this, DetallesPersonaje.class);
                intent.putExtra("nombre", personajeSeleccionado.getNombre());
                intent.putExtra("descripcion", personajeSeleccionado.getDescripcion());
                intent.putExtra("habilidades", personajeSeleccionado.getHabilidades());
                intent.putExtra("caracteristicas", personajeSeleccionado.getCaracteristicas());
                intent.putExtra("imagen", personajeSeleccionado.getImagen());
                startActivity(intent);
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Manejo del botón de retroceso
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    isEnabled = false;
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    /**
     * Método para añadir personajes de manera dinámica al menú lateral.
     *
     * @param menu El objeto del menú donde se añadirán los personajes.
     */
    private void addPersonajesToMenu(Menu menu) {
        MenuItem menuPersonajes = menu.findItem(R.id.personajes_menu);

        if (menuPersonajes != null) {
            SubMenu subMenu = menuPersonajes.getSubMenu();
            if (subMenu != null) {
                for (int i = 0; i < personajes.size(); i++) {
                    String nombrePersonaje = personajes.get(i).getNombre();
                    int iconoId = R.drawable.icono_selector;

                    // Asignación dinámica de iconos en función del personaje
                    if (nombrePersonaje.equals("Mario")) {
                        iconoId = R.drawable.mario;
                    } else if (nombrePersonaje.equals("Luigi")) {
                        iconoId = R.drawable.peach;
                    } else if (nombrePersonaje.equals("Peach")) {
                        iconoId = R.drawable.luigi;
                    } else if (nombrePersonaje.equals("Toad")) {
                        iconoId = R.drawable.toad;
                    }

                    // Añadir el personaje al SubMenu con su icono personalizado
                    subMenu.add(0, i, Menu.NONE, nombrePersonaje).setIcon(iconoId);
                }
            } else {
                Log.e("MainActivity", "El MenuItem no tiene un SubMenu asociado.");
            }
        } else {
            Log.e("MainActivity", "El MenuItem personajes_menu es nulo.");
        }
    }

    /**
     * Método para inflar el menú de opciones.
     *
     * @param menu El menú donde se inflarán las opciones.
     * @return Devuelve true si el menú se ha inflado correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Método que maneja las selecciones de los elementos del menú de la parte derecha de la pantalla.
     *
     * @param item El ítem seleccionado en el menú.
     * @return Devuelve true si el ítem ha sido manejado correctamente.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            new AlertDialog.Builder(this).setTitle("Acerca de...")
                    .setMessage("Aplicación desarrollada por Guillermo Fuentes Buenosvinos. Versión 1.0.")
                    .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> Log.d("Mensaje", "Accion ejecutada con exito"))
                    .show();
        } else if (id == R.id.settings) {
            Intent intent = new Intent(this, Ajustes.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
