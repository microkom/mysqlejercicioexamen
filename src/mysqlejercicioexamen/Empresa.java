/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlejercicioexamen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author german
 */
public class Empresa {

    ArrayList<Producto> productList = new ArrayList<Producto>();
    ArrayList<Pedidos> pedidosList = new ArrayList<Pedidos>();

    public void createFolderProveedor(File fileRoot) {

        File folderName;
        Conexion login = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;

        try {
            con = login.conectar();

            stmt = con.prepareStatement("SELECT idProveedor from proveedores");
            rs = stmt.executeQuery();
            String idProveedorString = "";
            int idProveedor = 0;
            while (rs.next()) {
                //se envia la ruta del directorio y el nombre de la carpeta a crear
                idProveedor = rs.getInt(1);
                idProveedorString = Integer.toString(idProveedor);
                folderName = new File(fileRoot, idProveedorString);
                createFolder(folderName);

                stmt2 = con.prepareStatement(""
                        + "SELECT p.NumPedido "
                        + "FROM pedidos p, lineaspedido l, productos pr, proveedores prov "
                        + "WHERE p.NumPedido=l.NumPedido "
                        + "AND l.Producto=pr.IdProducto "
                        + "AND pr.Proveedor=prov.IdProveedor "
                        + "AND prov.IdProveedor=?");
                stmt2.setInt(1, idProveedor);
                rs2 = stmt2.executeQuery();

                Producto product;

                File fileRoot2 = new File(fileRoot, idProveedorString);

                File fileName;

                while (rs2.next()) {
                    int numPedido = rs2.getInt("NumPedido");
                    String numPedidoString = Integer.toString(numPedido);

                    stmt3 = con.prepareStatement(
                            "SELECT p.NumPedido, c.Nombre, p.Fecha, l.Cantidad, l.Precio, l.Descuento "
                            + ",l.Cantidad*l.Precio*(SELECT IF(l.Descuento<=0, l.Descuento+1,(l.Descuento-1)*-1))as 'importe' "
                            + "FROM pedidos p, clientes c, lineaspedido l, productos pr, proveedores prov "
                            + "WHERE p.Cliente=c.IdCliente "
                            + "AND p.NumPedido=l.NumPedido "
                            + "AND l.Producto=pr.IdProducto "
                            + "AND pr.Proveedor=prov.IdProveedor "
                            + "AND l.numPedido=?");

                    stmt3.setInt(1, numPedido);
                    rs3 = stmt2.executeQuery();
                    Pedidos pedido;
                    while (rs3.next()) {
                        String nombreCliente = rs3.getString("nombre");
                        String fecha = rs3.getString("fecha");
                        int cantidad = rs3.getInt("cantidad");
                        double precio = rs3.getDouble("precio");
                        double descuento = rs3.getDouble("descuento");
                        double importe = rs3.getDouble("importe");

                        pedido = new Pedidos(numPedido, nombreCliente, fecha, cantidad, precio, descuento, importe);
                        pedidosList.add(pedido);
                    }
                    fileName = new File(fileRoot2, numPedidoString + ".txt");

                    String text = "";
                    for (Pedidos obj : pedidosList) {
                        text += String.format("%8d %-30s %-8s %3d %4.2f %2.2f %4.2f",
                                obj.getNumPedido(),
                                obj.getNombreCliente(),
                                obj.getFecha(),
                                obj.getCantidad(),
                                obj.getPrecio(),
                                obj.getDescuento(),
                                obj.getImporte());
                    }
                    writeFile(fileName, text);

                }

            }
        } catch (SQLException ex) {
            System.out.println("Método createFolderProveedor: " + ex.getMessage());
        } finally {
            login.desconectar(con);
        }
    }

    public void writeFile(File fileName, String texto) {

//necesario para crear un objeto del mismo tipo       
        FileWriter fileToWrite = null;
        BufferedWriter bufferWillWrite = null;

        try {
            //creacion de estructura de escritura
            fileToWrite = new FileWriter(fileName, true); //true: permite agregar info sin borrar el archivo

            bufferWillWrite = new BufferedWriter(fileToWrite);
            try {
                bufferWillWrite.write(texto + "\n");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (bufferWillWrite != null) {
                        bufferWillWrite.close();
                    }
                    if (fileToWrite != null) {
                        fileToWrite.close();
                    }
                } catch (Exception er) {
                    System.out.println(er.getMessage());
                }
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public void createFolder(File folderName) {
        //Creates a folder

        if (folderName.exists() && folderName.isDirectory()) {
            // Directory erase
            //wipeFolderContentsTwoLevels(folderName);
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
                currentFile.delete();
            }
        } catch (SecurityException se) {
            System.out.println(se.getMessage());
        }
    }

    public static void wipeFolderContentsTwoLevels(File dir) {
        //Deletes a folder content

        try {
            String[] entries = dir.list();
            for (String s : entries) {
                File levelOneFile = new File(dir.getPath(), s);
                String[] entries2 = levelOneFile.list();
                for (String s2 : entries2) {
                    File currentFile2 = new File(levelOneFile.getPath(), s2);
                    currentFile2.delete();
                }
                levelOneFile.delete();
            }
            dir.delete();

        } catch (SecurityException se) {
            System.out.println(se.getMessage());
        }
    }
}
