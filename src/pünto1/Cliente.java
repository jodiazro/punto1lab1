/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pünto1;

/**
 *
 * @author JOHAN DIAZ
 */
public class Cliente {
    private String nombre;
    private long cc;

    public Cliente(String nombre, long cc) {
        this.nombre = nombre;
        this.cc = cc;
    }

    public String getNombre() {
        return nombre;
    }

    public long getCc() {
        return cc;
    }

    @Override
    public String toString() {
        return nombre + ", C.C. " + cc;
    }
    
    
}
