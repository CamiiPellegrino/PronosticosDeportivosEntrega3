package com.mycompany.pronosticosdeportivosentrega3;

public class Pronostico {
    private Partido partido;
    private ResultadoEnum resultadoPronostico;
    private Persona participante;
    
    //Constructor/es:
    public Pronostico(Partido partido, ResultadoEnum resultadoPronostico, Persona participante)
    {
        this.partido = partido;
        this.resultadoPronostico = resultadoPronostico;
        this.participante = participante;
    }

    //Getters y setters: 
    public void setNomParticipante(String participante) {
         this.participante.setNombre(participante);
    }

    public Persona getParticipante() {
        return participante;
    }

    public int calcularPuntos(){
        int puntos = 0;
        if(this.partido.resultadoPartido() == this.resultadoPronostico){
            puntos = 1;
        }
        return puntos;
    }       
}
