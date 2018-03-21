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

    public void createFolderProveedor(File fileRoot) {

        File folderName;
        Conexion login = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

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
                
                
                stmt2 = con.prepareStatement("SELECT * from productos where idProducto=?");
                stmt2.setInt(1, idProveedor);
                rs2 = stmt2.executeQuery();

                Producto product;
                String text="";
                File fileRoot2 = new File(fileRoot, idProveedorString );
                
                File fileName ;
                
                
                while (rs2.next()) {
                    int idProducto = rs2.getInt("idProducto");
                    String nombreProducto = rs2.getString("NomProducto");
                    String idProductoString = Integer.toString(idProducto);
                    fileName = new File(fileRoot2,idProductoString+".txt");
                    text += idProducto;
                    writeFile(fileName, text);
                    
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
            fileToWrite = new FileWriter(fileName,true); //true: permite agregar info sin borrar el archivo

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
                }levelOneFile.delete();
            }dir.delete();
            
        } catch (SecurityException se) {
            System.out.println(se.getMessage());
        }
    }
}
