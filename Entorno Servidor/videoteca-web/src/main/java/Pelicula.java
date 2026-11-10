/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author daw2
 */
public class Pelicula {

    private int id;
    private String nombre;
    private double duracion;

    public Pelicula(int id, String nombre, double duracion) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDuracion() {
        return duracion;
    }

}
