/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pronosticosdeportivosentrega3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camii
 */
public class Modelo {
    private final String select_pronostico_por_ID = "SELECT * FROM `pronosticos` WHERE (id_pronosticos=?)";
    private final String select_resultado_por_ID = "SELECT * FROM `resultados` WHERE (id_resultados=?)";
    private ConexionBBDD conexion = new ConexionBBDD();
    
    public ArrayList<String> pronosticoPorId(int id_pronostico){
        ArrayList<String> retorno = new ArrayList<>();
        try(Connection con = conexion.conectar();
            PreparedStatement ps = con.prepareStatement(select_pronostico_por_ID);){
                ps.setInt(1, id_pronostico);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    for(int i=1; i<=9; i++){
                        if(i<=5 || i==8){
                            retorno.add(rs.getString(i));
                        }else{
                            retorno.add(String.valueOf(rs.getInt(i)));
                        }
                    }
                }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("error en Modelo, pronosticoPorId()", e);
        }
        return retorno;
    }
    public ArrayList<String> resultadoPorId(int id_resultado){
        ArrayList<String> retorno = new ArrayList<>();
        try(Connection con = conexion.conectar();
            PreparedStatement ps = con.prepareStatement(select_resultado_por_ID);){
                ps.setInt(1, id_resultado);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    for(int i=1; i<=7; i++){
                        if(i==1||i==4){
                            retorno.add(rs.getString(i));
                        }else{
                            retorno.add(String.valueOf(rs.getInt(i)));
                        }
                    }
                }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("error en Modelo, resultadoPorId()", e);
        }
        return retorno;
    }
    
    
    
}
