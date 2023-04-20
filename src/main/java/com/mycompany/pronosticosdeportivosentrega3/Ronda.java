package com.mycompany.pronosticosdeportivosentrega3;


import java.util.ArrayList;

public class Ronda {
    private ArrayList<Pronostico> pronosticos = new ArrayList<>();
    private ArrayList<Partido> partidos = new ArrayList<>();
    private int idRonda;

    //CONSTRUCTOR
    public Ronda(int idRonda){
        this.idRonda=idRonda;
    }
    //Getters y setters
    public int getIdRonda(){
        return idRonda;
    }
    public ArrayList<Partido> getPartidos(){
        return partidos;
    }
    //Otros metodos: 
    public void agregarPronostico(Pronostico p){
        pronosticos.add(p);
    }
    public void agregarPartido(Partido p){
        partidos.add(p);
    }
    public Partido buscarPartido(String nomEq1, String nomEq2){
        int z=0; boolean partidoEncontrado = false;
        Partido partido = null;
        while(z<partidos.size() & !partidoEncontrado){

            if(partidos.get(z).getEquipo1().getNombre().equals(nomEq1) & partidos.get(z).getEquipo2().getNombre().equals(nomEq2)){
                partido = partidos.get(z);
                partidoEncontrado = true;
            }
            z++;
        }
        return partido;
    }

    public ArrayList<Integer> puntajePorPersona(String nom){
        int puntos = 0; int puntosExtraPorRonda = 5; //si vale 0 no gana ptos extra por ronda adivinada completamente
                                                    //hacer dinamico al 5
        for(Pronostico p : pronosticos){ 
            if(p.getParticipante().getNombre().equals(nom)){
                puntos += p.calcularPuntos();
                if(p.calcularPuntos()==0){
                    puntosExtraPorRonda = 0;
                }
            }
        }
        puntos+=puntosExtraPorRonda;
        ArrayList<Integer> retorno = new ArrayList<>();
        retorno.add(puntos); 
        retorno.add(puntosExtraPorRonda);

        return retorno;
    }
}