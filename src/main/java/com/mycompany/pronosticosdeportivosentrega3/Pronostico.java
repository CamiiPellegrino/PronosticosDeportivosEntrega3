package com.mycompany.pronosticosdeportivosentrega3;

public class Pronostico {
    private Partido partido;
    private ResultadoEnum resultadoPronostico;
    private int idRonda;
    private Persona participante;
    
    //Constructor/es:
    public Pronostico(Partido partido, ResultadoEnum resultadoPronostico, int idRonda, Persona participante)
    {
        this.partido = partido;
        this.resultadoPronostico = resultadoPronostico;
        this.idRonda = idRonda;
        this.participante = participante;
    }
    public Pronostico(Partido partido, int idRonda){
        this.partido = partido;
        this.idRonda = idRonda;
    }
    
    //Getters y setters: 
    public Partido getPartido() {
        return partido;
    }

    public int getIdRonda() {
        return idRonda;
    }

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
