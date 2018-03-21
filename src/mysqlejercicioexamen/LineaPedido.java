/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlejercicioexamen;

/**
 *
 * @author german
 */
public class LineaPedido {
    private int numLinea;
    private int numPedido;
    private double precio;
    private int cantidad;
    private double descuento;

    public LineaPedido(int numLinea, int numPedido, double precio, int cantidad, double descuento) {
        this.numLinea = numLinea;
        this.numPedido = numPedido;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descuento = descuento;
    }

    public int getNumLinea() {
        return numLinea;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getDescuento() {
        return descuento;
    }
    
    
}
