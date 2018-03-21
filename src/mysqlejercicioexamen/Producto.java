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
    
    private int idProducto;
    private int idProveedor;
    private String nombreProducto;

    public Producto(int idProducto,  int idProveedor, String nombreProducto) {
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
        this.nombreProducto = nombreProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

 

    public int getIdProveedor() {
        return idProveedor;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    

    
}