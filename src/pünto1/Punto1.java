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
public class Punto1{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tienda amazon= new Tienda(20,10,"Amazon Colombia");
        Cliente juan= new Cliente("Juan Díaz",1380237491);
        Producto[] lista1 = new Producto[10];
        lista1[0]=new Producto("Crema de afeitar", 1890, 1.49);
        
        lista1[1]=new Producto("Play Station 4, edición especial", 1245, 399.0);
        lista1[2]=new Producto("Xbox one, edicion especial", 1847, 399.0);
        lista1[3]=new Producto("Dron profesional", 1890, 3000.0);
        lista1[4]=new Producto("Perfume Victory", 3452, 15.99);
        lista1[5]=new Producto("Computador Acer", 13456, 599.0);
        lista1[6]=new Producto("Crema de afeitar", 1890, 3000.0);
        lista1[7]=new Producto("Crema de afeitar", 1890, 3000.0);
        lista1[8]=new Producto("Crema de afeitar", 1890, 3000.0);
        lista1[9]=new Producto("Crema de afeitar", 1890, 3000.0);
        
        amazon.importarProductos(lista1);
        
        for(Estante estante: amazon.estantes){
            for(Caja caja: estante.getCajas()){
                for(Producto inventario: caja.getProductos()){
                    if(inventario!=null){
                        System.out.println(inventario.toString());
                    }
                    
                }
            }
        }
        Producto[] lista2 = new Producto[3];
        lista2[0]=new Producto("Computador Acer", 13456, 599.0);
        lista2[1]=new Producto("Crema de afeitar", 1890, 3000.0);
        lista2[2]=new Producto("Crema de afeitar", 1890, 3000.0);
        amazon.venderProductos(lista2,juan);
        
        for(Estante estante: amazon.estantes){
            for(Caja caja: estante.getCajas()){
                for(Producto inventario: caja.getProductos()){
                    if(inventario!=null){
                        System.out.println(inventario.toString());
                    }
                    
                }
            }
        }
        
        
        
    }

    
    
}
