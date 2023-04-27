package com.mycompany.pronosticosdeportivosentrega3;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
import java.util.ArrayList;

public class PronosticosDeportivosEntrega3 {
/*  Estructura de la base de datos:
 *  Nombre: 'pronosticos_deportivos'
 *  Cantidad de tablas: 2.
 *  TABLA 1
 *      nombre: resultados
 *      columnas: | equipo1 | golesEquipo1 | golesEquipo2 | equipo2 | ronda | fase | id_resultados |;
 *      
 *  TABLA 2
 *      nombre: pronosticos
 *      columnas: | gana1 | empate | gana2 | persona | id_pronosticos | FK_resultado |;
 */
    
    public static void main(String[] args){
        ArrayList<Fase> listaFases = new ArrayList<>();
        ArrayList<Equipo> listaEquipos = new ArrayList<>();
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        Modelo modelo = new Modelo("config.json");
        int idFilaProns = 0;
        
        //Lectura de pronosticos
        ArrayList<String> filaPron = modelo.siguientePronostico(idFilaProns);
                    //si hay una fila con id=1 y despues salta al id=6, el metodo no encuentra nada en el 2, 3, 
                    //pero sigue buscando hasta antes de llegar al final de la tabla
        while(!filaPron.isEmpty()){
            //#1 buscar a la persona:
            String nom = new String(filaPron.get(3));
            Persona personaAct = listaPersonas.stream()
            .filter(pers-> nom.equals(pers.getNombre()))
            .findFirst()
            .orElse(null);
            if(personaAct == null){
                personaAct = new Persona(filaPron.get(3));
                listaPersonas.add(personaAct);
            }

            //#2 buscar el resultado enum: 
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
                //#3 buscar la fase: 
                ArrayList<String> datosPartido = modelo.resultadoPorId(Integer.parseInt(filaPron.get(5)));
                Fase faseAct = listaFases.stream()
                .filter(fase-> fase.getIdFase()==Integer.parseInt(datosPartido.get(5)))
                .findFirst()
                .orElse(null);
                if(faseAct == null){
                    faseAct = new Fase(Integer.parseInt(datosPartido.get(5)));
                    listaFases.add(faseAct); //si no la encuentra, crearla
                }

                //#4 estos metodos, si no encuentran lo q buscan, devuelven un nuevo objeto del tipo (y lo agregan al array): 
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
                    personaAct);
                rondaAct.agregarPronostico(nuevoPronostico);
            }
            idFilaProns = modelo.getUltmoIdPronosticoLeido(); 
            filaPron = modelo.siguientePronostico(idFilaProns);
                    //lo iguala al ultimo id leido por el
                    //metodo modelo.siguientePronostico(idFilaProns)
        }
        
        LectorJson lector = new LectorJson("config.json");
        String ptosPorFase = lector.obtenerDatoDeRuta("puntosPorFase");
        String ptosPorRonda = lector.obtenerDatoDeRuta("puntosPorRonda");
        
        if(ptosPorFase != null && ptosPorRonda != null){
            System.out.println("*** Resultados de los pronosticos ***");
            for(Persona p : listaPersonas){
                int puntosTotales = 0;
                System.out.println(p.getNombre());
                for(Fase f : listaFases){
                    puntosTotales += f.puntosPorJugador(p.getNombre());
                }
                System.out.println(p.getNombre()+": "+puntosTotales);
            }
        }else {
            System.out.println("Error! Faltan datos en el archivo 'config.json'.");
        }
    }
    
    public static Equipo equipoPorNombre(String nombre, ArrayList<Equipo>array){
        int z=0; boolean equipoEncontrado = false;
        Equipo retorno = null;
        while(z<array.size() && !equipoEncontrado){
            if(array.get(z).getNombre().equals(nombre)){
                retorno = array.get(z);
                equipoEncontrado = true;
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
