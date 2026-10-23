/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author daw2
 */
public class ResultadoCopia {

    private Plan plan;
    private double almacenamientoGb;
    private double minutosDiarios;
    private double totalMensual;

    public ResultadoCopia(Plan plan, double almacenamientoGb, double minutosDiarios, double totalMensual) {
        this.plan = plan;
        this.almacenamientoGb = almacenamientoGb;
        this.minutosDiarios = minutosDiarios;
        this.totalMensual = totalMensual;
    }

    public Plan getPlan() {
        return plan;
    }

    public double getAlmacenamientoGb() {
        return almacenamientoGb;
    }

    public double getMinutosDiarios() {
        return minutosDiarios;
    }

    public double getTotalMensual() {
        return totalMensual;
    }

}
