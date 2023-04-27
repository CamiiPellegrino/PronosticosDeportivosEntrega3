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

/**
 *
 * @author camii
 */
public class Modelo {
    private final String select_pronostico_por_ID = "SELECT * FROM `pronosticos` WHERE (id_pronosticos=?)";
    private final String select_resultado_por_ID = "SELECT * FROM `resultados` WHERE (id_resultados=?)";
    private final String select_ultimo_pronostico = "SELECT * FROM pronosticos ORDER BY id_pronosticos DESC LIMIT 1;";
    private ConexionBBDD conexion;
    private int ultimoIdPronosticoLeido;
    
    public Modelo(String ruta) {
        this.conexion = new ConexionBBDD(ruta);  //(ruta al archivo config)
    }
    
    //getters y setters:
    public int getUltmoIdPronosticoLeido(){
        return ultimoIdPronosticoLeido;
    }
    
    //otros metodos: 
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
            throw new RuntimeException("Error! No se ha podido acceder a la base de datos. Compruebe los parametros enviados en el archivo 'config.json'", e);
        }
        return retorno;
    }
    
    public ArrayList<String> siguientePronostico(int idInicial){
        int pronosticoFinal = cantidadDePronosticos();
        boolean pronosticoEncontrado = false;
        ArrayList<String> retorno = new ArrayList<>();
        int id = idInicial;
        do{
            try(Connection con = conexion.conectar();
                PreparedStatement ps = con.prepareStatement(select_pronostico_por_ID);){
                    retorno.clear();
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        for(int i=1; i<=6; i++){
                            if(i<=4){
                                retorno.add(rs.getString(i));
                            }else{
                                retorno.add(String.valueOf(rs.getInt(i)));
                            }
                        }
                        pronosticoEncontrado = !retorno.isEmpty();
                    }

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException("Error! No se ha podido acceder a la base de datos. Compruebe los parametros enviados en el archivo 'config.json'", e);
            }
            id++;
        }while(id<=pronosticoFinal && !pronosticoEncontrado);
        
        ultimoIdPronosticoLeido = id;
        return retorno;
    }
    
    public int cantidadDePronosticos(){
        int idUltimoPronostico = 0;
                try(Connection con = conexion.conectar();
                    PreparedStatement ps = con.prepareStatement(select_ultimo_pronostico);){
                    ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    idUltimoPronostico = rs.getInt(5);
                }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error! No se ha podido acceder a la base de datos. Compruebe los parametros enviados en el archivo 'config.json'", e);
        }

        
        return idUltimoPronostico;
    }
    
}
