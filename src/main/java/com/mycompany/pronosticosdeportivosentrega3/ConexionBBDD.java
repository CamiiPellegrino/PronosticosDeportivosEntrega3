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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camii
 */
public class ConexionBBDD {
    private String usr;
    private String pwd;
    private String url;
    
    public ConexionBBDD(){
        
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("config.json"));
            String linea = br.readLine();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> map = objectMapper.readValue(linea, new TypeReference<Map<String,String>>(){});
            this.url = map.get("url");
            this.usr = map.get("user");
            this.pwd = map.get("password");

        } catch (FileNotFoundException ex) {
            throw new RuntimeException("error al conectar con BBDD", ex);
        } catch (IOException ex) {
            throw new RuntimeException("error al conectar con BBDD", ex);
        }
    }
    public Connection conectar() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cn = DriverManager.getConnection(url, usr, pwd);
        return cn;
    }
}
