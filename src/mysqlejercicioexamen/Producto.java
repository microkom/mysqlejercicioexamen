/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlejercicioexamen;

/**
 *
 * @author DAW
 */
public class Producto {
    
    private int numeroPedido;
    private String nombreCliente;
    private String fecha;
    private int cantidad;
    private double precio;
    private double descuento;
    private double importe;

    public Producto(int numeroPedido, String nombreCliente, String fecha, int cantidad, double precio, double descuento) {
        this.numeroPedido = numeroPedido;
        this.nombreCliente = nombreCliente;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }

    public int getNumeroPedido() {
        return numeroPedido;
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

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    

    
    
}