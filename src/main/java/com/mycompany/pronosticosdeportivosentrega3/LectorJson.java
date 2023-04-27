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
import java.util.Map;

/**
 *
 * @author camii
 */
public class LectorJson {
    private String ruta;
    public LectorJson(String ruta){
        this.ruta = ruta;
    }
    
    public String obtenerDatoDeRuta(String dato){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(ruta));
            String linea = br.readLine();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> map = objectMapper.readValue(linea, new TypeReference<Map<String,String>>(){});
            return map.get(dato);
            
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("error al leer datos de "+ruta, ex);
        } catch (IOException ex) {
            throw new RuntimeException("error al leer datos de "+ruta, ex);
        }
    }
}
