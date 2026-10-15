
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author daw2
 */
public class Producto {
    
    public String nombre;
    public boolean soloDesayuno;
    List<String> acompanamientos = new ArrayList<>();

    public Producto(String nombre, boolean soloDesayuno) {
        this.nombre = nombre;
        this.soloDesayuno = soloDesayuno;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isSoloDesayuno() {
        return soloDesayuno;
    }

    public List<String> getAcompanamientos() {
        return acompanamientos;
    }
    
    
    
}
