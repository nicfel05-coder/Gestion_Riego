package org.example;

public class Nodo {
    private int identificador;
    private String nombre;
    private int duracionRiegoMin;
    private Nodo siguiente;

    public Nodo(int identificador, String nombre, int duracionRiegoMin) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.duracionRiegoMin = duracionRiegoMin;
        this.siguiente = null;
    }
    public int getIdentificador() {
        return identificador;
    }
    public String getNombre() {
        return nombre;
    }
    public int getDuracionRiegoMin() {
        return duracionRiegoMin;
    }
    public Nodo getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    @Override
    public String toString() {
        return "[" + identificador + "] " + nombre + " (" + duracionRiegoMin + " min)";
    }
}