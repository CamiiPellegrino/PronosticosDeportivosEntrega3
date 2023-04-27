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
    private String puntosFasePerfecta;
    
    //Constructor/es:
    public Fase(int idFase){
        rondas = new ArrayList<>();
        this.idFase = idFase;
        LectorJson lector = new LectorJson("config.json");
        this.puntosFasePerfecta = lector.obtenerDatoDeRuta("puntosPorFase");
    }
    
    //Getters y setters:
    public int getIdFase(){
        return idFase;
    }

    public ArrayList<Ronda> getRondas() {
        return rondas;
    }

    public String getPuntosFasePerfecta() {
        return puntosFasePerfecta;
    }
    
    //Otros metodos:
    public Ronda buscarRondaPorId(int id_ronda){
        Ronda ronda = rondas.stream()
        .filter(r-> r.getIdRonda()==id_ronda)
        .findFirst()
        .orElse(null);
        
        if(ronda == null){
            ronda = new Ronda(id_ronda);
            this.agregarRonda(ronda);
        }
        return ronda;
    }
    
    public void agregarRonda(Ronda r){
        rondas.add(r);
    }
    
    public int puntosPorJugador(String nom){
        int puntos = 0; int puntosFase = Integer.parseInt(this.puntosFasePerfecta);
        for(Ronda ronda : rondas){
            puntos+=ronda.puntajePorPersona(nom).get(0);
            if(ronda.puntajePorPersona(nom).get(1)==0){
                puntosFase = 0;
            }
        }
        puntos+=puntosFase;
        return puntos;
    }       
}
