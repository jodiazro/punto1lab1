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
public class Caja {
    private Producto[] productos;

    public Caja(int n_productos) {
        this.productos = new Producto[n_productos];
    }
    
    public boolean addProducto(Producto producto){
        for(int i=0;i<productos.length;i++){
            if(productos[i]==null){
                productos[i]=producto;
                return true;
            }
        }
        return false;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }
    
    
}
