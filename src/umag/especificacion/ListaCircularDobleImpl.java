package umag.especificacion;

import umag.entidad.NodoCircularDoble;

public class ListaCircularDobleImpl<E> implements TadListaCircularDoble<E> {
    private NodoCircularDoble<E> inicio;
    private NodoCircularDoble<E> actual;
    private int cantidad;

    public ListaCircularDobleImpl() {
        this.inicio = null;
        this.actual = null;
        this.cantidad = 0;
    }

    @Override
    public void insertarFinal(E dato) {
        NodoCircularDoble<E> nuevoNodo = new NodoCircularDoble<>(dato);
        if (estaVacia()) {
            inicio = actual = nuevoNodo;
        } else {
            inicio.getAnterior().setSiguiente(nuevoNodo);
            nuevoNodo.setSiguiente(inicio);
            nuevoNodo.setAnterior(inicio.getAnterior());
            inicio.setAnterior(nuevoNodo);
        }
        cantidad++;
    }

    @Override
    public void eliminar(E dato) {
        if (!estaVacia()) {
            if (inicio.getDato().equals(dato) && cantidad == 1) {
                inicio = actual = null;
                cantidad--;
                return;
            } 
            NodoCircularDoble<E> auxActual = inicio;
            do {
                if (auxActual.getDato().equals(dato)) {
                    auxActual.getAnterior().setSiguiente(auxActual.getSiguiente());
                    auxActual.getSiguiente().setAnterior(auxActual.getAnterior());

                    if (auxActual == inicio) {
                        inicio = auxActual.getSiguiente();
                    }

                    if (auxActual == actual) {
                        actual = auxActual.getSiguiente();
                    }

                    cantidad--;

                    return;
                }
                auxActual = auxActual.getSiguiente();
            } while (auxActual != inicio);
        }
    }

    @Override
    public boolean estaVacia() {
        return cantidad == 0;
    }

    @Override
    public int cantidad() {
        return cantidad;
    }

    @Override
    public void pasarSiguiente() {
        if (!estaVacia()) {
            actual = actual.getSiguiente();
        }
    }

    @Override
    public void pasarAnterior() {
        if (!estaVacia()) {
            actual = actual.getAnterior();
        }
    }

    @Override
    public E obtenerActual() {
        if (estaVacia()) {
            throw new IllegalStateException("La lista está vacía");
        }
        return actual.getDato();
    }

    @Override
    public String mostrar() {
        if (estaVacia()) {
            return "La lista está vacía";
        } else {
            String cadena = "";
            NodoCircularDoble<E> auxActual = inicio;
            do {
                cadena += auxActual.getDato().toString() + "\n";
                auxActual = auxActual.getSiguiente();
            } while (auxActual != inicio);
            return cadena;
        }
    }
}
