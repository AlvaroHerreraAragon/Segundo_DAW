/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alvar
 */
public class Videojuego {
    
    private int id;
    private String titulo;
    private String plataforma;
    private String genero;
    private Double horas;
    private Double valoracion;

    public Videojuego(int id, String titulo, String plataforma, String genero, Double horas, Double valoracion) {
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.genero = genero;
        this.horas = horas;
        this.valoracion = valoracion;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public Double getHoras() {
        return horas;
    }

    public Double getValoracion() {
        return valoracion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setHoras(Double horas) {
        this.horas = horas;
    }

    public void setValoracion(Double valoracion) {
        this.valoracion = valoracion;
    }
    

    @Override
    public String toString() {
        return "Videojuego{" + "id=" + id + ", titulo=" + titulo + ", plataforma=" + plataforma + ", genero=" + genero + ", horas=" + horas + ", valoracion=" + valoracion + '}';
    }
    
}
