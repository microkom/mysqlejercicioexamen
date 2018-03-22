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
public class Pedidos {
    private int numPedido;
    private String nombreCliente;
    private String fecha;
    private int cantidad;
    private double precio;
    private double descuento;
    private double importe;

    public Pedidos(int numPedido, String nombreCliente, String fecha, int cantidad, double precio, double descuento, double importe) {
        this.numPedido = numPedido;
        this.nombreCliente = nombreCliente;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.importe = importe;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getImporte() {
        return importe;
    }
    
}
