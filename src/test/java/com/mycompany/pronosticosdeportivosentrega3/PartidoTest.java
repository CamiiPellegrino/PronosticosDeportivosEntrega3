/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.pronosticosdeportivosentrega3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        Partido instance = new Partido(new Equipo("uno"), new Equipo("dos"),1,2);
        ResultadoEnum expResult = ResultadoEnum.GANADOR_EQ2;
        ResultadoEnum result = instance.resultadoPartido();
        assertSame(expResult, result);
    }

    
}
