package com.fuentesbuenosvinosguillermo.mariobros.RecyclerView;

/**
 * La clase info_personajes representa la información de cada personaje que se mostrará en el RecyclerView.
 * Contiene atributos que describen el nombre, la descripción, las habilidades y características de un personaje,
 * así como su imagen asociada.
 */
public class info_personajes {

    // Atributos que almacenan la información del personaje
    private String nombre;            // Nombre del personaje
    private String descripcion;       // Descripción del personaje
    private String habilidades;       // Habilidades del personaje
    private String caracteristicas;   // Características del personaje
    private int imagen;               // Recurso de imagen asociado al personaje

    /**
     * Constructor de la clase info_personajes.
     * Inicializa todos los atributos del personaje.
     *
     * @param nombre         Nombre del personaje.
     * @param descripcion    Descripción del personaje.
     * @param habilidades    Habilidades del personaje.
     * @param caracteristicas Características del personaje.
     * @param imagen         Recurso de imagen asociado al personaje.
     */
    public info_personajes(String nombre, String descripcion, String habilidades, String caracteristicas, int imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.habilidades = habilidades;
        this.caracteristicas = caracteristicas;
        this.imagen = imagen;
    }

    /**
     * Obtiene el recurso de imagen del personaje.
     *
     * @return Recurso de imagen asociado al personaje.
     */
    public int getImagen() {
        return imagen;
    }

    /**
     * Establece el recurso de imagen del personaje.
     *
     * @param imagen Recurso de imagen a establecer.
     */
    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return Nombre del personaje.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del personaje.
     *
     * @param nombre Nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del personaje.
     *
     * @return Descripción del personaje.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del personaje.
     *
     * @param descripcion Descripción a establecer.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene las habilidades del personaje.
     *
     * @return Habilidades del personaje.
     */
    public String getHabilidades() {
        return habilidades;
    }

    /**
     * Establece las habilidades del personaje.
     *
     * @param habilidades Habilidades a establecer.
     */
    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    /**
     * Obtiene las características del personaje.
     *
     * @return Características del personaje.
     */
    public String getCaracteristicas() {
        return caracteristicas;
    }

    /**
     * Establece las características del personaje.
     *
     * @param caracteristicas Características a establecer.
     */
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
