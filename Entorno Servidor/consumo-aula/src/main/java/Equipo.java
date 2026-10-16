/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author daw2
 */
public class Equipo {

    private String codigo;
    private String nombre;
    private int potenciaVatios;

    public Equipo(String codigo, String nombre, int potenciaVatios) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.potenciaVatios = potenciaVatios;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPotenciaVatios() {
        return potenciaVatios;
    }

}
