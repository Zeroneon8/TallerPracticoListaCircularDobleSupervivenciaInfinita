package umag.especificacion;

public interface TadListaCircularDoble<E> {
    void insertarFinal(E dato);
    void eliminar(E dato);
    boolean estaVacia();
    int cantidad();
    void pasarSiguiente();
    void pasarAnterior();
    E obtenerActual();
    String mostrar();
}
