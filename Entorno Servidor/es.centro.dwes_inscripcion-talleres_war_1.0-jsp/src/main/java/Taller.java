/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author daw2
 */
public class Taller {

    private String nombre;
    private String turno;
    private int plazas;
    private int duracionMinutos;

    public Taller(String nombre, String turno, int plazas, int duracionMinutos) {
        this.nombre = nombre;
        this.turno = turno;
        this.plazas = plazas;
        this.duracionMinutos = duracionMinutos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTurno() {
        return turno;
    }

    public int getPlazas() {
        return plazas;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    

}
