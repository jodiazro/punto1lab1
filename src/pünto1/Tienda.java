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
public class Tienda {
    City bodega;
    Estante[] estantes;
    Robot[] robots;
    boolean[] robotOcupado;
    Thing empleado;
    Thing envio;
    private String nombre;
    
    Tienda(int n_estantes,int n_max_robots,String name){
        int n_pasillos=n_max_robots/2;
        bodega = new City(n_max_robots+4,n_estantes/n_pasillos+5);
        //System.out.println(1+n_estantes/n_pasillos);
        empleado=new Thing(bodega, n_max_robots+2,3);
        this.empleado.setColor(Color.DARK_GRAY);
        this.nombre=name;
        envio=new Thing(bodega,0,2);
        this.envio.setColor(Color.DARK_GRAY);
        
        robotOcupado=new boolean[n_max_robots];
        estantes=new Estante[n_estantes];
        robots=new Robot[n_max_robots];
        
        for(boolean ocupado: robotOcupado) ocupado=false;
        
        
        for(int i=1;i<=2*n_pasillos;i++){
            Wall pared=new Wall(bodega,i,1,Direction.WEST);
            Wall pared1=new Wall(bodega,i,3+n_estantes/n_pasillos,Direction.EAST);
        }
        for(int i=1;i<=2;i++){
            Wall pared=new Wall(bodega,1,i,Direction.NORTH);
            Wall pared1=new Wall(bodega,2*n_pasillos,i,Direction.SOUTH);
        }
        for(int i=4;i<=3+n_estantes/n_pasillos;i++){
            Wall pared=new Wall(bodega,1,i,Direction.NORTH);
            Wall pared1=new Wall(bodega,2*n_pasillos,i,Direction.SOUTH);
        }
       
        for(int i=0,k=0;i<n_pasillos;i++){
            for(int j=0;j<n_estantes/n_pasillos;j++,k++){
                estantes[k]=new Estante(this,n_estantes/n_pasillos+3-j,2*i+1,3,7,k);
            }
        }
        
        for(int i=0;i<n_max_robots;i++){
            this.robots[i]=new Robot(this.bodega,i+1,1,Direction.EAST);
            this.robots[i].setLabel(String.valueOf(i));
        }
        
    }
    
    public void importarProductos(Producto[] productos){
        Estante[] estantesDisponibles=new Estante[productos.length];
        
        for(int j=0;j<productos.length;j++){
            estantesDisponibles[j]=getEstanteVacio();
            this.importarProducto(productos[j],1,j,estantesDisponibles[j].getX_coords(),estantesDisponibles[j].getY_coords());
        }  
        for(int i=2;i<13;i++){
            for(int j=0;j<productos.length;j++){
                this.importarProducto(productos[j],i,j,estantesDisponibles[j].getX_coords(),estantesDisponibles[j].getY_coords());
            }        
        }
    }
    
    private void importarProducto(Producto producto, int paso,int r,int x,int y){
        int xr,yr;
        
        /*for(int i=0;i<this.robotOcupado.length;i++){
            if(!this.robotOcupado[i]){
                r=i;
                this.robotOcupado[i]=true;
                i=this.robotOcupado.length;
            }            
        }
        x=this.getEstanteVacio().getX_coords();
        y=this.getEstanteVacio().getY_coords();*/
        
        xr=1;
        yr=r+1;
        
        System.out.println("x"+x+"y"+y+"xr"+xr+"yr"+yr);
        switch(paso){
            case 1:
                for(int i=0;i<2;i++)this.robots[r].move();
                this.getEstanteVacio().addProducto(producto);
                this.getEstanteVacio().setTransporte(true);
                break;
            case 2:
                if(yr>y){
                this.robots[r].turnLeft();
                for(int i=0;i<yr-y;i++)this.robots[r].move();
                for(int i=0;i<3;i++)this.robots[r].turnLeft();
            
                }else if(yr<y){
                    for(int i=0;i<3;i++)this.robots[r].turnLeft();
                    for(int i=0;i<y-yr;i++)this.robots[r].move();
                    this.robots[r].turnLeft();
                }
                
                break;
            case 3:
                for(int i=0;i<x-xr-2;i++)this.robots[r].move();
                this.robots[r].pickThing();
                
                for(int i=0;i<3;i++)this.robots[r].turnLeft();
                break;
            case 4:
                this.robots[r].move();
                for(int i=0;i<3;i++)this.robots[r].turnLeft();
                break;
            case 5:
                for(int i=0;i<x-xr-2;i++)this.robots[r].move();
                this.robots[r].turnLeft();
                break;
            case 6:
                for(int i=0;i<this.robots.length-y;i++)this.robots[r].move();
        
                this.empleado.setColor(Color.yellow);
                for(int i=0;i<2;i++)this.robots[r].turnLeft();
                this.empleado.setColor(Color.DARK_GRAY);
                break;
            case 7:
                for(int i=0;i<this.robots.length-y;i++)this.robots[r].move();
                for(int i=0;i<3;i++)this.robots[r].turnLeft();
                break;
            case 8:
                for(int i=0;i<x-xr-2;i++)this.robots[r].move();
                this.robots[r].turnLeft();
                break;
            case 9:
                this.robots[r].move();
                this.robots[r].turnLeft();

                this.robots[r].putThing();
                
                break;
            case 10:
                for(int i=0;i<x-xr-2;i++)this.robots[r].move();
                break;
            
            case 11:
                if(yr>y){
                    this.robots[r].turnLeft();
                    for(int i=0;i<yr-y;i++)this.robots[r].move();
                    for(int i=0;i<3;i++)this.robots[r].turnLeft();

                }else if(yr<y){
                    for(int i=0;i<3;i++)this.robots[r].turnLeft();
                    for(int i=0;i<y-yr;i++)this.robots[r].move();
                    this.robots[r].turnLeft();
                }
                break;
                
            case 12:
                for(int i=0;i<2;i++)this.robots[r].move();
                for(int i=0;i<2;i++)this.robots[r].turnLeft();
                break;
                
        
        }
        
      /*
        for(int i=0;i<2;i++)this.robots[r].move();
        this.getEstanteVacio().addProducto(producto);
        this.getEstanteVacio().setTransporte(true);                

        if(yr>y){
        this.robots[r].turnLeft();
        for(int i=0;i<yr-y;i++)this.robots[r].move();
        for(int i=0;i<3;i++)this.robots[r].turnLeft();

        }else if(yr<y){
            for(int i=0;i<3;i++)this.robots[r].turnLeft();
            for(int i=0;i<y-yr;i++)this.robots[r].move();
            this.robots[r].turnLeft();
        }


        for(int i=0;i<x-xr-2;i++)this.robots[r].move();
        this.robots[r].pickThing();

        for(int i=0;i<3;i++)this.robots[r].turnLeft();

        this.robots[r].move();
        for(int i=0;i<3;i++)this.robots[r].turnLeft();

        for(int i=0;i<x-xr-2;i++)this.robots[r].move();
        this.robots[r].turnLeft();

        for(int i=0;i<this.robots.length-y;i++)this.robots[r].move();

        this.empleado.setColor(Color.yellow);
        for(int i=0;i<2;i++)this.robots[r].turnLeft();
        this.empleado.setColor(Color.DARK_GRAY);

        for(int i=0;i<this.robots.length-y;i++)this.robots[r].move();
        for(int i=0;i<3;i++)this.robots[r].turnLeft();

        for(int i=0;i<x-xr-2;i++)this.robots[r].move();
        this.robots[r].turnLeft();

        this.robots[r].move();
        this.robots[r].turnLeft();

        this.robots[r].putThing();


        for(int i=0;i<x-xr-2;i++)this.robots[r].move();

        if(yr>y){
            this.robots[r].turnLeft();
            for(int i=0;i<yr-y;i++)this.robots[r].move();
            for(int i=0;i<3;i++)this.robots[r].turnLeft();

        }else if(yr<y){
            for(int i=0;i<3;i++)this.robots[r].turnLeft();
            for(int i=0;i<y-yr;i++)this.robots[r].move();
            this.robots[r].turnLeft();
        }

        for(int i=0;i<2;i++)this.robots[r].move();
        for(int i=0;i<2;i++)this.robots[r].turnLeft();
             
        
       */
    }
    public void venderProductos(Producto[] productos,Cliente cliente){
        Estante[] estantesConProductos=new Estante[productos.length];
        for(int j=0;j<productos.length;j++){
            estantesConProductos[j]=this.buscarProducto(productos[j]);
            
        }
        for(int i=1;i<13;i++){
            for(int j=0;j<productos.length;j++){
                    this.venderProducto(productos[j],i,j,estantesConProductos[j].getX_coords(),estantesConProductos[j].getY_coords());
            }
        }
        
        System.out.println("\n\t**FACTURA**\n\t"+this.nombre+" corporations");
        for(Producto producto: productos){
            System.out.println("\t - "+producto.toString()+"x1");
        }
        System.out.println("\tCliente:");
        System.out.println("\t"+cliente.toString());
    }
    
    
    private boolean venderProducto(Producto producto, int paso,int r,int x, int y){
        
        int xr,yr;
        
        /*for(int i=0;i<this.robotOcupado.length;i++){
            if(!this.robotOcupado[i]){
                r=i;
                this.robotOcupado[i]=true;
                i=this.robotOcupado.length;
            }            
        }
        */
        xr=1;
        yr=r+1;
        
        
        switch(paso){
            case 1:
                
                

                for(int i=0;i<2;i++)this.robots[r].move();
                break;
            case 2:
                if(yr>y){
                    this.robots[r].turnLeft();
                    for(int i=0;i<yr-y;i++)this.robots[r].move();
                    for(int i=0;i<3;i++)this.robots[r].turnLeft();

                }else if(yr<y){
                    for(int i=0;i<3;i++)this.robots[r].turnLeft();
                    for(int i=0;i<y-yr;i++)this.robots[r].move();
                    this.robots[r].turnLeft();
                }
                break;
            case 3:
                for(int i=0;i<x-xr-2;i++)this.robots[r].move();
        
                this.robots[r].pickThing();

                for(int i=0;i<3;i++)this.robots[r].turnLeft();
                break;
            case 4:
                this.robots[r].move();
                for(int i=0;i<3;i++)this.robots[r].turnLeft();
                break;
            case 5:
                for(int i=0;i<x-xr-2;i++)this.robots[r].move();
        
                for(int i=0;i<3;i++)this.robots[r].turnLeft();
                break;
            case 6:
                for(int i=0;i<y;i++)this.robots[r].move();
        
                this.envio.setColor(Color.green);
                for(int i=0;i<2;i++)this.robots[r].turnLeft();
                this.envio.setColor(Color.DARK_GRAY);
                break;
            case 7:
                for(int i=0;i<y;i++)this.robots[r].move();
        
                this.robots[r].turnLeft();
                break;
        
            case 8:
                for(int i=0;i<x-xr-2;i++)this.robots[r].move();
                this.robots[r].turnLeft();
                break;
            case 9:
                this.robots[r].move();
                this.robots[r].turnLeft();

                this.robots[r].putThing();
                
                break;
            case 10:
                for(int i=0;i<x-xr-2;i++)this.robots[r].move();
                break;
            
            case 11:
                if(yr>y){
                    this.robots[r].turnLeft();
                    for(int i=0;i<yr-y;i++)this.robots[r].move();
                    for(int i=0;i<3;i++)this.robots[r].turnLeft();

                }else if(yr<y){
                    for(int i=0;i<3;i++)this.robots[r].turnLeft();
                    for(int i=0;i<y-yr;i++)this.robots[r].move();
                    this.robots[r].turnLeft();
                }
                break;
                
            case 12:
                for(int i=0;i<2;i++)this.robots[r].move();
                for(int i=0;i<2;i++)this.robots[r].turnLeft();
                break;
        }
        /*
        for(int i=0;i<2;i++)this.robots[r].move();
        
        if(yr>y){
            this.robots[r].turnLeft();
            for(int i=0;i<yr-y;i++)this.robots[r].move();
            for(int i=0;i<3;i++)this.robots[r].turnLeft();
            
        }else if(yr<y){
            for(int i=0;i<3;i++)this.robots[r].turnLeft();
            for(int i=0;i<y-yr;i++)this.robots[r].move();
            this.robots[r].turnLeft();
        }
        
        for(int i=0;i<x-xr-2;i++)this.robots[r].move();
        
        this.robots[r].pickThing();
        
        for(int i=0;i<3;i++)this.robots[r].turnLeft();//
        
        this.robots[r].move();
        for(int i=0;i<3;i++)this.robots[r].turnLeft();
        
        for(int i=0;i<x-xr-2;i++)this.robots[r].move();
        
        for(int i=0;i<3;i++)this.robots[r].turnLeft();
        
        for(int i=0;i<y+1;i++)this.robots[r].move();
        
        this.envio.setColor(Color.green);
        for(int i=0;i<2;i++)this.robots[r].turnLeft();
        this.envio.setColor(Color.DARK_GRAY);
        
        for(int i=0;i<y+1;i++)this.robots[r].move();
        
        this.robots[r].turnLeft();
        
        for(int i=0;i<x-xr-2;i++)this.robots[r].move();
        
        this.robots[r].turnLeft();
        
        this.robots[r].move();
        
        this.robots[r].turnLeft();
        
        this.robots[r].putThing();
        
        for(int i=0;i<x-xr-2;i++)this.robots[r].move();
        
        if(yr>y){
            this.robots[r].turnLeft();
            for(int i=0;i<yr-y;i++)this.robots[r].move();
            for(int i=0;i<3;i++)this.robots[r].turnLeft();

        }else if(yr<y){
            for(int i=0;i<3;i++)this.robots[r].turnLeft();
            for(int i=0;i<y-yr;i++)this.robots[r].move();
            this.robots[r].turnLeft();
        }

        for(int i=0;i<2;i++)this.robots[r].move();
        for(int i=0;i<2;i++)this.robots[r].turnLeft();
        
        System.out.println(this.nombre);
        */
        return true;
        
        
    }
    private Estante buscarProducto(Producto producto){
        
        for(Estante estante: estantes){
            for(Caja caja: estante.getCajas()){
                for(int i=0;i<caja.getProductos().length;i++){
                    if(caja.getProductos()[i]!=null){
                        if(caja.getProductos()[i].equals(producto)){
                            caja.getProductos()[i]=null;
                            return estante;
                        }
                    }
                   
                    
                }
            }
        }
        return null;
    }
    private Estante getEstanteVacio(){
        
        for(Estante estante: estantes){
            for(Caja caja: estante.getCajas()){
                for(Producto espacio: caja.getProductos()){
                    if(espacio==null&&!estante.enTransporte()){
                        return estante;

                    }
                }
            }
        }
        return null;
    }
    
    private boolean isFrontClear(Robot robot){
        int x=robot.getAvenue();
        int y=robot.getStreet();
        Direction dir=robot.getDirection();
        
        if(dir==Direction.EAST){
            for(Robot r_robot: this.robots){
                if(r_robot.getAvenue()==x+1&&r_robot.getStreet()==y){
                    return false;
                }
            }
        }
        
        if(dir==Direction.WEST){
            for(Robot r_robot: this.robots){
                if(r_robot.getAvenue()==x-1&&r_robot.getStreet()==y){
                    return false;
                }
            }
        }
        
        if(dir==Direction.NORTH){
            for(Robot r_robot: this.robots){
                if(r_robot.getAvenue()==x&&r_robot.getStreet()==y-1){
                    return false;
                }
            }
        }
        
        if(dir==Direction.SOUTH){
            for(Robot r_robot: this.robots){
                if(r_robot.getAvenue()==x&&r_robot.getStreet()==y+1){
                    return false;
                }
            }
        }
        
        return true;
    }
}
