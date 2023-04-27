/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pronosticosdeportivosentrega3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author camii
 */
public class ConexionBBDD {
    private String usr;
    private String pwd;
    private String url;
    
    public ConexionBBDD(String ruta){
        LectorJson lector = new LectorJson(ruta);
        this.usr = lector.obtenerDatoDeRuta("user");
        this.pwd = lector.obtenerDatoDeRuta("password");
        this.url = lector.obtenerDatoDeRuta("url");
    }
    
    
    public Connection conectar() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cn = DriverManager.getConnection(url, usr, pwd);
        return cn;
    }
}
