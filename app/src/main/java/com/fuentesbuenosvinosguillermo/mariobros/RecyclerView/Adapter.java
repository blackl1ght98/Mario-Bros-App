package com.fuentesbuenosvinosguillermo.mariobros.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fuentesbuenosvinosguillermo.mariobros.R;

import java.util.List;

/**
 * La clase Adapter es un adaptador que conecta los datos de la lista de personajes (info_personajes)
 * con el RecyclerView, gestionando la creación y el vinculado de los ViewHolders.
 */
public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    // Contexto de la actividad que usa el RecyclerView
    private Context context;

    // Lista de objetos info_personajes que se mostrarán en el RecyclerView
    private List<info_personajes> items;

    // Variable listener para gestionar los eventos de clic en los ítems
    private onItemClickListener listener;

    /**
     * Interfaz para manejar los eventos de clic en los ítems del RecyclerView.
     */
    public interface onItemClickListener {
        void onItemClick(int position); // Método que se llama cuando se hace clic en un ítem
    }

    /**
     * Establece el listener para los eventos de clic en los ítems.
     *
     * @param listener El listener que se establecerá para manejar los clics en los ítems.
     */
    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Constructor de la clase Adapter.
     * Inicializa el contexto y la lista de ítems.
     *
     * @param context Contexto de la aplicación.
     * @param items   Lista de personajes que se mostrarán en el RecyclerView.
     */
    public Adapter(Context context, List<info_personajes> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * Crea y devuelve un nuevo ViewHolder que contiene un ítem específico del RecyclerView.
     *
     * @param parent   El ViewGroup al que se añadirá la vista.
     * @param viewType El tipo de vista que se va a crear (no utilizado aquí).
     * @return Un nuevo ViewHolder que contiene la vista inflada.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
         * La clase LayoutInflater se utiliza para convertir el diseño XML (item_view_recycler) en un objeto de vista Java.
         * LayoutInflater.from(context) obtiene una instancia de LayoutInflater utilizando el contexto de la aplicación.
         * .inflate(R.layout.item_view_recycler, parent, false)
         * - Aquí inflamos el archivo de diseño, convirtiéndolo en un objeto de vista.
         * - El primer parámetro es el recurso de diseño que se va a inflar (en este caso, item_view_recycler).
         * - El segundo parámetro (parent) es el ViewGroup al que se agregará la vista inflada, aunque no se agrega de inmediato.
         * - El tercer parámetro (false) indica que no se debe añadir la vista inflada al ViewGroup padre en este momento.
         * Finalmente, pasamos el listener para manejar los clics en los elementos.
         */
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_recycler, parent, false), listener);
    }

    /**
     * Vincula los datos del ítem en la posición dada a la vista del ViewHolder correspondiente.
     *
     * @param holder   El ViewHolder que contiene la vista a la que se vincularán los datos.
     * @param position La posición del ítem en la lista de datos.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obtenemos el nombre del personaje en la posición actual y lo asignamos al TextView en el ViewHolder
        holder.nameView.setText(items.get(position).getNombre());

        // Obtenemos la imagen del personaje en la posición actual y la asignamos al ImageView en el ViewHolder
        holder.imageView.setImageResource(items.get(position).getImagen());
    }

    /**
     * Devuelve el número total de ítems en la lista de datos.
     *
     * @return El tamaño de la lista 'items', que representa la cantidad de elementos a mostrar en el RecyclerView.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }
}
