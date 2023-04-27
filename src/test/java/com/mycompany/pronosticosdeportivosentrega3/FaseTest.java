/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.pronosticosdeportivosentrega3;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author camii
 */
public class FaseTest {
    
    public FaseTest() {
    }
    @Test
    public void testPuntosPorJugador() {
        System.out.println("puntosPorJugador");
        LectorJson lector = new LectorJson("config.json");
        int puntosPorRonda = Integer.parseInt(lector.obtenerDatoDeRuta("puntosPorRonda"));
        int puntosPorFase = Integer.parseInt(lector.obtenerDatoDeRuta("puntosPorFase"));
        
        String nomJugador = "cami";
        Persona jugador = new Persona(nomJugador);
        Partido p1 = new Partido(new Equipo("brasil"), new Equipo("peru"), 3, 1);
        Partido p2 = new Partido(new Equipo("Argentina"), new Equipo("Colombia"), 3, 1);
        Pronostico pron1 = new Pronostico(p1, ResultadoEnum.GANADOR_EQ1, jugador);
        Pronostico pron2 = new Pronostico(p2, ResultadoEnum.GANADOR_EQ1, jugador);
        Ronda r1 = new Ronda(1); Ronda r2 = new Ronda(1);
        Fase fase = new Fase(1);
        
        r1.agregarPartido(p1); r1.agregarPronostico(pron1);
        r2.agregarPartido(p2); r2.agregarPronostico(pron2);
        fase.agregarRonda(r1); fase.agregarRonda(r2);
        int puntaje = fase.puntosPorJugador(nomJugador);
        
        assertEquals(puntaje, 2+(puntosPorRonda*2)+puntosPorFase); 
    }
    
}
