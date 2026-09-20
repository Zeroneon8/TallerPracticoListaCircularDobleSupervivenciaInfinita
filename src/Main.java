import umag.especificacion.TadListaCircularDoble;
import umag.implementacion.ListaCircularDobleImpl;
import umag.entidad.Jugador;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TadListaCircularDoble<Jugador> listaJugadores = new ListaCircularDobleImpl<>();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n--- SUPERVIVENCIA INFINITA ---");
            System.out.println("1. Anadir jugador");
            System.out.println("2. Mostrar lista");
            System.out.println("3. Activar ruleta del castigo");
            System.out.println("0. Salir");
            opcion = leerEntero(scanner, "Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    insertarJugador(scanner, listaJugadores);
                    break;
                case 2:
                    System.out.println(listaJugadores.mostrar());
                    break;
                case 3:
                    activarRuleta(scanner, listaJugadores);
                    break;
                case 0:
                    System.out.println("Fin del juego.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    public static String ruletaDelCastigo(int s, TadListaCircularDoble<Jugador> lista) {
        if (lista.estaVacia()) {
            throw new IllegalStateException("No hay jugadores en la lista");
        }

        if (s < 0) {
            throw new IllegalArgumentException("El numero de saltos no puede ser negativo");
        }

        int saltosReales = s % lista.cantidad();

        for (int i = 0; i < saltosReales; i++) {
            lista.pasarSiguiente();
        }

        Jugador jugadorAfectado = lista.obtenerActual();
        String nombreJugadorAfectado = jugadorAfectado.getNombre();
        jugadorAfectado.perderVida();

        if (jugadorAfectado.getVidas() == 0) {
            lista.eliminar(jugadorAfectado);
        }

        return nombreJugadorAfectado;
    }

    private static void insertarJugador(Scanner scanner, TadListaCircularDoble<Jugador> lista) {
        System.out.print("Nombre del jugador: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            return;
        }

        lista.insertarFinal(new Jugador(nombre, 3));
        System.out.println("Jugador insertado.");
    }

    private static void activarRuleta(Scanner scanner, TadListaCircularDoble<Jugador> lista) {
        if (lista.estaVacia()) {
            System.out.println("No se puede activar la ruleta sin jugadores.");
            return;
        }

        int saltos = leerEntero(scanner, "Numero de saltos de la ruleta: ");
        String nombreAfectado = ruletaDelCastigo(saltos, lista);
        System.out.println("La ruleta afecto a: " + nombreAfectado);
        System.out.println("Lista despues de la ruleta:");
        System.out.println(lista.mostrar());
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Debe introducir un numero entero.");
            }
        }
    }
}
