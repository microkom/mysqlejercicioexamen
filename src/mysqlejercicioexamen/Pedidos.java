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
    private String idCliente;
    private String fecha;

    public Pedidos(int numPedido, String idCliente, String fecha) {
        this.numPedido = numPedido;
        this.idCliente = idCliente;
        this.fecha = fecha;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getFecha() {
        return fecha;
    }
    
    
}
