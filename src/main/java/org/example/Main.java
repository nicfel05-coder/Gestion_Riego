package org.example;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final ListaCircular lista = new ListaCircular();

    public static void main(String[] args) {
        boolean salir = false;

        System.out.println("=================================================================");
        System.out.println(" LISTA CIRCULAR - Turnos rotativos de riego en una finca");
        System.out.println("=================================================================");

        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Elija una opcion: ");

            switch (opcion) {
                case 1 -> opcionInsertar();
                case 2 -> opcionEliminar();
                case 3 -> lista.mostrar();
                case 4 -> opcionSiguiente();
                case 5 -> opcionActual();
                case 6 -> opcionBuscar();
                case 7 -> opcionRecorrer();
                case 8 -> opcionRecorridoAleatorio();
                case 9 -> opcionEstado();
                case 10-> lista.eliminarAleatorio();
                case 0 -> {
                    salir = true;
                    System.out.println("Saliendo del programa. Hasta pronto.");
                }
                default -> System.out.println("Opcion invalida, intente de nuevo.");
            }
        }
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("1. Insertar zona");
        System.out.println("2. Eliminar zona por id");
        System.out.println("3. Mostrar lista");
        System.out.println("4. Avanzar al siguiente");
        System.out.println("5. Ver elemento actual");
        System.out.println("6. Buscar zona por id");
        System.out.println("7. Recorrer N vueltas");
        System.out.println("8. Recorrido al azar ");
        System.out.println("9. Ver estado");
        System.out.println("10. Eliminar zona al azar");
        System.out.println("0. Salir");
        System.out.println("----------------------------------------");
    }

    private static void opcionInsertar() {
        int id = leerEntero("Id de la zona: ");
        if (lista.buscar(id) != null) {
            System.out.println("  -> Ya existe una zona con ese id. Operacion cancelada.");
            return;
        }
        System.out.print("Nombre de la zona: ");
        String nombre = sc.nextLine();
        int duracion = leerEntero("Duracion de riego (minutos): ");
        lista.insertar(id, nombre, duracion);
    }

    private static void opcionEliminar() {
        int id = leerEntero("Id de la zona a eliminar: ");
        lista.eliminar(id);
    }

    private static void opcionSiguiente() {
        Nodo nodo = lista.siguiente();
        if (nodo == null) {
            System.out.println("  -> La lista esta vacia.");
        } else {
            System.out.println("  -> Nodo actual ahora: " + nodo);
        }
    }

    private static void opcionActual() {
        Nodo nodo = lista.obtenerActual();
        System.out.println(nodo == null
                ? "  -> La lista esta vacia, no hay elemento actual."
                : "  -> Elemento actual: " + nodo);
    }

    private static void opcionBuscar() {
        int id = leerEntero("Id a buscar: ");
        Nodo nodo = lista.buscar(id);
        System.out.println(nodo == null
                ? "  -> No se encontro ninguna zona con id=" + id
                : "  -> Encontrado: " + nodo);
    }

    private static void opcionRecorrer() {
        int vueltas = leerEntero("Cuantas vueltas desea recorrer? ");
        lista.recorrer(vueltas);
    }

    private static void opcionRecorridoAleatorio() {
        int vueltas = leerEntero("Cuantas vueltas aleatorias desea dar? ");
        lista.recorridoAleatorio(vueltas);
    }

    private static void opcionEstado() {
        System.out.println("  Tamano actual: " + lista.getTamano());
        System.out.println("  Vacia? " + lista.estaVacia());
        System.out.println("  Cabeza: " + lista.getCabeza());
        System.out.println("  Actual: " + lista.obtenerActual());
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("  -> Ingrese un numero valido.");
            }
        }
    }

}
