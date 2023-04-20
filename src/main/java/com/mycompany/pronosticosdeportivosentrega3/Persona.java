package com.mycompany.pronosticosdeportivosentrega3;

import java.util.ArrayList;

public class Persona
{
    private String nombre;
    private int puntos;
    private ArrayList<Pronostico> listaPronosticos;

    public Persona(String nombre){
        this.nombre = nombre;
        listaPronosticos = new ArrayList<>();
    }
    
    public void setPronosticoPersona(ArrayList<Pronostico> pronos){
        this.listaPronosticos = pronos;
    }

    public void setNombre(String nombrePersona){
        this.nombre = nombrePersona;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public int getPuntos(){
        return puntos;
    }

    public void agregarPuntos(int punto){
        this.puntos += punto;
    }

}
