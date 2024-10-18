package com.fuentesbuenosvinosguillermo.mariobros.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fuentesbuenosvinosguillermo.mariobros.R;

/**
 * La clase ViewHolder forma parte del patrón de diseño del RecyclerView y se utiliza
 * para mejorar la eficiencia de la visualización de listas en Android.
 * Un ViewHolder almacena las referencias de las vistas para cada elemento de la lista.
 * Esta clase hereda de RecyclerView.ViewHolder, lo que le proporciona las funcionalidades necesarias.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    // Variables que almacenan las referencias a los elementos de la vista (ImageView y TextView)
    ImageView imageView;
    TextView nameView;

    /**
     * Constructor de la clase ViewHolder. Aquí se inicializan las vistas del elemento
     * y se gestiona la interacción del usuario, como los clics en cada ítem del RecyclerView.
     *
     * @param itemView Vista del elemento actual en el RecyclerView.
     * @param listener Interfaz que gestionará los clics en los ítems. Es pasada desde el Adapter.
     */
    public ViewHolder(@NonNull View itemView, final Adapter.onItemClickListener listener) {
        super(itemView);

        // Inicialización de las vistas dentro del ítem (item_view_recycler)
        imageView = itemView.findViewById(R.id.imageView);  // Referencia al ImageView del ítem
        nameView = itemView.findViewById(R.id.name);        // Referencia al TextView del ítem

        /**
         * El siguiente bloque configura el clic en cada ítem del RecyclerView. Aunque el Adapter es el que gestiona
         * la lógica principal del RecyclerView, el ViewHolder es el encargado de registrar los clics individuales
         * y de interactuar con el Adapter para determinar qué ítem ha sido seleccionado.
         */
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtiene la posición del ítem en la lista que fue clicado
                int position = getAdapterPosition();

                // Verifica si el listener no es nulo y si la posición es válida
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    // Llama al método onItemClick del listener, pasando la posición del ítem clicado
                    listener.onItemClick(position);
                }
            }
        });
    }
}
