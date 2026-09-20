package umag.entidad;

public class NodoCircularDoble<E> {
    private E dato;
    private NodoCircularDoble<E> siguiente;
    private NodoCircularDoble<E> anterior;

    public NodoCircularDoble(E dato) {
        this.dato = dato;
        this.siguiente = this;
        this.anterior = this;
    }

    public E getDato() {
        return dato;
    }

    public NodoCircularDoble<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCircularDoble<E> siguiente) {
        this.siguiente = siguiente;
    }

    public NodoCircularDoble<E> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoCircularDoble<E> anterior) {
        this.anterior = anterior;
    }
}
