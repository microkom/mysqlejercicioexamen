/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlejercicioexamen;

import java.io.File;

/**
 *
 * @author DAW
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Proveedor prov = new Proveedor();
        
        prov.getDBaseData();
        
        File folderName = new File("Proveedores");
        prov.createFolder(folderName);
    }
    
}
