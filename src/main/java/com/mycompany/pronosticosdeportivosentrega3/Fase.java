/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pronosticosdeportivosentrega3;

import java.util.ArrayList;

/**
 *
 * @author camii
 */
public class Fase {
    private ArrayList<Ronda> rondas;
    private int idFase;
    
    //Constructor/es:
    public Fase(int idFase){
        rondas = new ArrayList<>();
        this.idFase = idFase;
    }
    
    //Getters y setters:
    public int getIdFase(){
        return idFase;
    }

    public ArrayList<Ronda> getRondas() {
        return rondas;
    }
    
    
    //Otros metodos:
    public Ronda buscarRondaPorId(int id_ronda){
        Ronda ronda = null;
        int z=0;
        boolean rondaEncontrada = false;
        while(z<rondas.size() & !rondaEncontrada){
            if(rondas.get(z).getIdRonda()==id_ronda){
                ronda = rondas.get(z);
            }
            z++;
        }
        
        return ronda;
    }
    
    public Ronda rondaPorId(int idRonda){
        int z = 0; boolean rondaEncontrada = false;
        Ronda rondaReturn = null;
        while(z<this.rondas.size() & !rondaEncontrada){
            if(rondas.get(z).getIdRonda() == idRonda){
                rondaReturn = rondas.get(z);
                rondaEncontrada = true;
            }
            z++;
        }
        return rondaReturn;
    }
    
    public void agregarRonda(Ronda r){
        rondas.add(r);
    }
    
    
    
    public int puntosPorJugador(String nom){
        int puntos = 0; int puntosExtra = 10;
        for(Ronda ronda : rondas){
            puntos+=ronda.puntajePorPersona(nom).get(0);
            if(ronda.puntajePorPersona(nom).get(1)==0){
                puntosExtra = 0;
            }
        }
        puntos+=puntosExtra;
        return puntos;
    }    
}
