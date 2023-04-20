/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pronosticosdeportivosentrega3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author camii
 */
public class ConexionBBDD {
    private final String bbdd = "pronosticos_deportivos";
    private final String usr="root";
    private final String pwd="";
    private final String url="jdbc:mysql://localhost:3306/" + bbdd;
    
    public Connection conectar() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cn = DriverManager.getConnection(url, usr, pwd);
        return cn;
    }

}
