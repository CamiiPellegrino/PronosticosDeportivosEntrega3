/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.pronosticosdeportivosentrega3;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author camii
 */
public class PartidoTest {
    
    public PartidoTest() {
    }


    @Test
    public void testResultadoPartido() {
        System.out.println("resultadoPartido");
        Equipo eq1 = new Equipo("brasil");
        Equipo eq2 = new Equipo("peru");

        Partido instance = new Partido(eq1, eq2, 1, 0);
        ResultadoEnum expResult = ResultadoEnum.GANADOR_EQ1;
        ResultadoEnum result = instance.resultadoPartido();
        System.out.println(expResult==result);
        assertEquals(expResult, result);
    }
    
}
