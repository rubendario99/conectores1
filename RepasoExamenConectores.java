/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repasoexamenconectores;

import java.sql.SQLException;

/**
 *
 * @author ruchi
 */
public class RepasoExamenConectores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        Ejercicios prueba = new Ejercicios();
        prueba.ejercicio1("longitud","7,2",true);
    }
}
