/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pünto1;

import becker.robots.*;
import java.awt.Color;
/**
 *
 * @author JOHAN DIAZ
 */
public class Estante {
    private Thing estante_fisico;
    private Caja[] cajas;
    private int x_coords;
    private int y_coords;
    private boolean transporte;

    Estante(Tienda tienda,int x,int y,int n_cajas,int n_productosPorCaja,int id) {
        this.x_coords=x;
        this.y_coords=y;
        this.estante_fisico = new Thing(tienda.bodega,y,x);
        this.cajas = new Caja[n_cajas];
        for(int i=0;i<n_cajas;i++){
            this.cajas[i]=new Caja(n_productosPorCaja);
        }
        
        this.estante_fisico.setColor(Color.ORANGE);
        this.estante_fisico.getIcon().setLabel(String.valueOf(id));
    }

    

    public Thing getEstante_fisico() {
        return estante_fisico;
    }

    public void setEstante_fisico(Thing estante_fisico) {
        this.estante_fisico = estante_fisico;
    }

    public Caja[] getCajas() {
        return cajas;
    }

    public void setCajas(Caja[] cajas) {
        this.cajas = cajas;
    }

    public int getX_coords() {
        return x_coords;
    }

    public int getY_coords() {
        return y_coords;
    }
    
    public boolean addProducto(Producto producto){
        for(Caja caja: this.cajas){
            
                
            for(int i=0;i<caja.getProductos().length;i++){
                if(caja.getProductos()[i]==null){
                    caja.getProductos()[i]=producto;
                    System.out.println("Agregando "+producto.toString());
                    return true;
                }
            }
        }
        return false;
    }

    public void setTransporte(boolean transporte) {
        this.transporte = transporte;
    }
    
    
    public boolean enTransporte() {
        return transporte;
    }
}
