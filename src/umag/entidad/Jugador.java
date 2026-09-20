package umag.entidad;

public class Jugador {
    private String nombre;
    private int vidas;

    public Jugador(String nombre, int vidas) {
        this.nombre = nombre;
        this.vidas = vidas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVidas() {
        return vidas;
    }

    // Esto tambien se puede hacer con un setter pasando como argumento getVidas() - 1.
    public void perderVida() {
        if (vidas > 0) {
            vidas--;
        }
    }

    public String toString() {
        return "Jugador: " + nombre + ", Vidas: " + vidas;
    }

    // Sobrescribimos el método equals para comparar jugadores por nombre, ignorando mayúsculas y minúsculas.
    @Override 
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jugador otro = (Jugador) obj;
        return this.nombre.equalsIgnoreCase(otro.nombre);
    }
}
