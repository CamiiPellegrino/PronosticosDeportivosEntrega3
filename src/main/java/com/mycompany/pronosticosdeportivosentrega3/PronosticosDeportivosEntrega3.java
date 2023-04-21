package com.mycompany.pronosticosdeportivosentrega3;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
import java.util.ArrayList;

public class PronosticosDeportivosEntrega3 {

    public static void main(String[] args){
        ArrayList<Fase> listaFases = new ArrayList<>();
        ArrayList<Equipo> listaEquipos = new ArrayList<>();
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        Modelo modelo = new Modelo();
        int idFilaProns = 0;
        
        //Pronosticos
        ArrayList<String> filaPron = modelo.siguientePronostico(idFilaProns);
        while(!filaPron.isEmpty()){
            //buscar persona:
            int i=0; boolean encontrado = false;//variables usadas en cada buscador de este while
            Persona personaAct = new Persona(filaPron.get(3));
            while(i<listaPersonas.size() & !encontrado){
                if(filaPron.get(3).equals(listaPersonas.get(i).getNombre())){
                    encontrado = true;
                    personaAct = listaPersonas.get(i);
                }
                i++;
            }
            if(!encontrado){
                listaPersonas.add(personaAct);
            }
            //resultado enum: 
            ResultadoEnum re = null;
            for(int e=0; e<=2; e++){
                if(filaPron.get(e).equals("X")){
                    switch (e){
                        case 0 -> re = ResultadoEnum.GANADOR_EQ1;
                        case 1 -> re = ResultadoEnum.EMPATE;
                        case 2 -> re = ResultadoEnum.GANADOR_EQ2;
                        default -> {}
                    }
                }
            }
            if(re != null){
                //buscamos fase: 
                i=0; encontrado = false;
                ArrayList<String> datosPartido = modelo.resultadoPorId(Integer.parseInt(filaPron.get(5)));
                Fase faseAct = new Fase(Integer.parseInt(datosPartido.get(5)));
                while(i<listaFases.size() & !encontrado){
                    if(listaFases.get(i).getIdFase() == Integer.parseInt(datosPartido.get(5))){
                        encontrado = true;
                        faseAct = listaFases.get(i);
                    }
                    i++;
                }
                if(!encontrado){
                    listaFases.add(faseAct);
                }
                Ronda rondaAct = faseAct.buscarRondaPorId(Integer.parseInt(datosPartido.get(4)));
                Equipo eq1 = equipoPorNombre(datosPartido.get(0), listaEquipos);
                Equipo eq2 = equipoPorNombre(datosPartido.get(3), listaEquipos);
                Partido partidoAct = new Partido(eq1, eq2, 
                    Integer.parseInt(datosPartido.get(1)), 
                    Integer.parseInt(datosPartido.get(2))
                );
                rondaAct.agregarPartido(partidoAct);
                Pronostico nuevoPronostico = new Pronostico(
                    partidoAct, re, 
                    Integer.parseInt(filaPron.get(5)), 
                    personaAct);
                rondaAct.agregarPronostico(nuevoPronostico);

            }
            idFilaProns = modelo.getUltmoIdPronosticoLeido();
            filaPron = modelo.siguientePronostico(idFilaProns);
        }
        
        System.out.println("*** Resultados de los pronosticos ***");
        for(Persona p : listaPersonas){
            int puntosTotales = 0;
            for(Fase f : listaFases){
                puntosTotales += f.puntosPorJugador(p.getNombre());
            }
            System.out.println(p.getNombre()+": "+puntosTotales);
        }
    }

    public static Equipo equipoPorNombre(String nombre, ArrayList<Equipo>array){
        int z=0; boolean equipoEncontrado = false;
        Equipo retorno = null;
        while(z<array.size() & !equipoEncontrado){
            if(array.get(z).getNombre().equals(nombre)){
                retorno = array.get(z);
            }
            z++;
        }
        if(!equipoEncontrado){
            retorno = new Equipo(nombre);
            array.add(retorno);
        }
        return retorno;
    }
}

/*
//Resultados
ArrayList<String> filaRtdos = modelo.resultadoPorId(idFilaRtdos);
while(!filaRtdos.isEmpty()){
    int i=0; boolean encontrado = false; //variables usadas en cada buscador de este while
    Fase faseAct = new Fase(Integer.parseInt(filaRtdos.get(5)));
    while(i<listaFases.size() & !encontrado){

        if(Integer.parseInt(filaRtdos.get(5)) == (listaFases.get(i).getIdFase())){
            encontrado = true;
            faseAct = listaFases.get(i);
        }
        i++;
    }
    if(!encontrado){
        listaFases.add(faseAct);
    }
    Ronda rondaAct = faseAct.buscarRondaPorId(Integer.parseInt(filaRtdos.get(4)));
    if(rondaAct == null){
        rondaAct = new Ronda(Integer.parseInt(filaRtdos.get(4)));
        faseAct.agregarRonda(rondaAct);
    }
    Equipo eq1 = equipoPorNombre(filaRtdos.get(0), listaEquipos);
    Equipo eq2 = equipoPorNombre(filaRtdos.get(3), listaEquipos);
    Partido partidoAct = new Partido(eq1, eq2, 
            Integer.parseInt(filaRtdos.get(1)), 
            Integer.parseInt(filaRtdos.get(2))
    );
    rondaAct.agregarPartido(partidoAct);

    idFilaRtdos++;
    filaRtdos = modelo.resultadoPorId(idFilaRtdos);
}

//Pronosticos
ArrayList<String> filaPron = modelo.pronosticoPorId(idFilaProns);
while(!filaPron.isEmpty()){
    //buscar persona:
    int i=0; boolean encontrado = false;//variables usadas en cada buscador de este while
    Persona personaAct = new Persona(filaPron.get(7));
    while(i<listaPersonas.size() & !encontrado){
        if(filaPron.get(7).equals(listaPersonas.get(i).getNombre())){
            encontrado = true;
            personaAct = listaPersonas.get(i);
        }
        i++;
    }
    if(!encontrado){
        listaPersonas.add(personaAct);
    }
    //resultado enum: 
    ResultadoEnum re = null;
    for(int e=1; e<=3; e++){
        if(filaPron.get(e).equals("X")){
            switch (e){
                case 1 -> re = ResultadoEnum.GANADOR_EQ1;
                case 2 -> re = ResultadoEnum.EMPATE;
                case 3 -> re = ResultadoEnum.GANADOR_EQ2;
                default -> {}
            }
        }
    }
    if(re != null){
        i=0; encontrado = false; 
        Fase faseAct = null;
        while(i<listaFases.size() & !encontrado){
            if(Integer.parseInt(filaPron.get(6)) == (listaFases.get(i).getIdFase())){
                encontrado = true; faseAct = listaFases.get(i);
                Ronda rondaAct = faseAct.buscarRondaPorId(Integer.parseInt(filaPron.get(5)));
                Partido partidoAct = rondaAct.buscarPartido(filaPron.get(0), filaPron.get(4));
                Pronostico nuevoPronostico = new Pronostico(
                        partidoAct, re, 
                        Integer.parseInt(filaPron.get(5)), 
                        personaAct);
                rondaAct.agregarPronostico(nuevoPronostico);
            }
            i++;
        }
        if(faseAct==null){
            System.out.println("Error! no se encontró la fase"); //describir mas claramente el error
        }
    }
    idFilaProns++;
    filaPron = modelo.pronosticoPorId(idFilaProns);
}
for(Persona p : listaPersonas){
    int puntosTotales = 0;
    for(Fase f : listaFases){
        puntosTotales += f.puntosPorJugador(p.getNombre());
    }
    System.out.println(p.getNombre()+": "+puntosTotales);
}*/
