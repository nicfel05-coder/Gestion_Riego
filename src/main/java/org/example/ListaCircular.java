package org.example;
import java.util.Random;

public class ListaCircular {

    private Nodo cabeza;
    private Nodo actualNodo;
    private int tamaño;
    private final Random random = new Random();

    public ListaCircular() {
        this.cabeza = null;
        this.actualNodo = null;
        this.tamaño = 0;
    }
    public boolean estaVacia() {
        return tamaño == 0;
    }
    public int getTamano() {
        return tamaño;
    }
    public Nodo getCabeza() {
        return cabeza;
    }

    public Nodo insertar(int identificador, String nombre, int duracionRiegoMin) {
        Nodo nuevo = new Nodo(identificador, nombre, duracionRiegoMin);

        if (estaVacia()) {
            nuevo.setSiguiente(nuevo);
            cabeza = nuevo;
            actualNodo = nuevo;
        } else {
            Nodo temporal = cabeza;
            while (temporal.getSiguiente() != cabeza) {
                temporal = temporal.getSiguiente();
            }
            temporal.setSiguiente(nuevo);
            nuevo.setSiguiente(cabeza);
        }
        tamaño++;
        System.out.println("  -> Insertado: " + nuevo);
        return nuevo;
    }

    public boolean eliminar(int identificador) {
        if (estaVacia()) {
            System.out.println("  -> No se puede eliminar: la lista esta vacia.");
            return false;
        }
        Nodo anterior = null;
        Nodo temporal = cabeza;
        int recorridos = 0;

        while (recorridos < tamaño) {
            if (temporal.getIdentificador() == identificador) {
                break;
            }
            anterior = temporal;
            temporal = temporal.getSiguiente();
            recorridos++;
        }
        if (recorridos == tamaño) {
            System.out.println("  -> No se encontro un elemento con id=" + identificador);
            return false;
        }
        if (tamaño == 1) {
            cabeza = null;
            actualNodo = null;
        } else {
            if (temporal == cabeza) {
                Nodo ultimo = cabeza;
                while (ultimo.getSiguiente() != cabeza) {
                    ultimo = ultimo.getSiguiente();
                }
                cabeza = temporal.getSiguiente();
                ultimo.setSiguiente(cabeza);
            } else {
                anterior.setSiguiente(temporal.getSiguiente());
            }
            if (actualNodo == temporal) {
                actualNodo = temporal.getSiguiente();
            }
        }
        tamaño--;
        System.out.println("  -> Eliminado: " + temporal);
        return true;
    }

    public Nodo buscar(int identificador) {
        if (estaVacia()) {
            return null;
        }
        Nodo temporal = cabeza;
        for (int i = 0; i < tamaño; i++) {
            if (temporal.getIdentificador() == identificador) {
                return temporal;
            }
            temporal = temporal.getSiguiente();
        }
        return null;
    }

    public Nodo obtenerActual() {
        return actualNodo;
    }

    public void fijarActual(Nodo nodo) {
        this.actualNodo = nodo;
    }

    public Nodo siguiente() {
        if (estaVacia()) {
            return null;
        }
        actualNodo = actualNodo.getSiguiente();
        return actualNodo;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("  (lista circular vacia)");
            return;
        }
        StringBuilder sb = new StringBuilder("  ");
        Nodo temporal = cabeza;
        for (int i = 0; i < tamaño; i++) {
            sb.append(temporal);
            if (i < tamaño - 1) {
                sb.append("  ->  ");
            }
            temporal = temporal.getSiguiente();
        }
        sb.append("  -> (vuelve a la cabeza)");
        System.out.println(sb);
    }

    public void recorrer(int nVueltas) {
        if (estaVacia()) {
            System.out.println("  -> No se puede recorrer: la lista esta vacia.");
            return;
        }

        Nodo inicio = actualNodo;
        int totalPasos = tamaño * nVueltas;

        System.out.println("  Punto de partida: " + inicio);
        for (int paso = 1; paso <= totalPasos; paso++) {
            Nodo nodo = siguiente();
            String marca = (nodo == inicio) ? " <-- vuelta completada" : "";
            System.out.printf("  Paso %3d: %s%s%n", paso, nodo, marca);
        }
    }
    public boolean eliminarAleatorio() {
        if (estaVacia()) {
            System.out.println("  -> No se puede eliminar: la lista esta vacia.");
            return false;
        }

        int posicionAleatoria = random.nextInt(tamaño);

        Nodo temporal = cabeza;
        for (int i = 0; i < posicionAleatoria; i++) {
            temporal = temporal.getSiguiente();
        }

        int idElegido = temporal.getIdentificador();
        System.out.println("  -> Se elegio al azar para eliminar: " + temporal);
        return eliminar(idElegido);
    }
    public void recorridoAleatorio(int vueltas) {
        if (estaVacia()) {
            System.out.println("  -> No se puede recorrer: la lista esta vacia.");
            return;
        }
        if (vueltas <= 0) {
            System.out.println("  -> El numero de vueltas debe ser mayor a 0.");
            return;
        }

        Nodo[] nodos = new Nodo[tamaño];
        Nodo temporal = cabeza;
        for (int i = 0; i < tamaño; i++) {
            nodos[i] = temporal;
            temporal = temporal.getSiguiente();
        }

        for (int v = 1; v <= vueltas; v++) {

            for (int i = nodos.length - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                Nodo aux = nodos[i];
                nodos[i] = nodos[j];
                nodos[j] = aux;
            }

            System.out.println("  Vuelta " + v + " de " + vueltas
                    + " (sin repetir, " + tamaño + " nodo(s)):");
            for (int i = 0; i < nodos.length; i++) {
                actualNodo = nodos[i];
                System.out.printf("    Paso %3d: %s%n", i + 1, actualNodo);
            }
        }
    }
}

