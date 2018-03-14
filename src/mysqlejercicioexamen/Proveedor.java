/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlejercicioexamen;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DAW
 */
public class Proveedor {

    ArrayList<Producto> productList = new ArrayList<Producto>();

    public void getDBaseData() {

        Conexion login = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Producto product = null;
        try {
            con = login.conectar();

            stmt = con.prepareStatement("SELECT p.NumPedido, c.Nombre, p.Fecha, l.Cantidad, l.Precio, (l.Descuento*100)as 'Descuento' "
                    + "FROM clientes c, pedidos p, lineaspedido l WHERE c.IdCliente=p.Cliente AND p.NumPedido=l.NumPedido");
            rs = stmt.executeQuery();
            
            
            while (rs.next()){
                int numPedido = rs.getInt("NumPedido");
                String nombreC = rs.getString("Nombre");
                String fecha = rs.getString("Fecha");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");
                double descuento = rs.getDouble("Descuento");
                
                product = new Producto(numPedido, nombreC, fecha, cantidad, precio, descuento);
                
                productList.add(product);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            login.desconectar(con);
        }
    }
    public void createFile(File fileName){
        
    }
    public void createFolder(File folderName){
        if (folderName.exists() && folderName.isDirectory()) {
            // Directory erase
            wipeFolderContents(folderName);
        }
        try {
            folderName.mkdir();
        } catch (SecurityException se) {
            System.out.println(se.getMessage());
        }
    }
    public void wipeFolderContents(File dir) {
        //Deletes a folder content

        try {
            String[] entries = dir.list();
            for (String s : entries) {
                File currentFile = new File(dir.getPath(), s);
                String[] entries2 = currentFile.list();
                for (String s2 : entries2 ){
                    File currentFile2 = new File(currentFile.getPath(), s2);
                    currentFile2.delete();
                }currentFile.delete();
            }
            dir.delete();//borra el directorio
            
        } catch (SecurityException se) {
            System.out.println(se.getMessage());
        }
    }

}
