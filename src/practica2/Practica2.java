/*
 Bases de datos tienda peluchito
 */
package practica2;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

public class Practica2 {

    
    public static void main(String[] args) {
        String user2="alexandra";  //se inicializa con el usuario
        String url2="jdbc:mysql://db4free.net/contabilidad_pl";
        String password2="noravalencia";
        String user="root";  //se inicializa con el usuario
        String url="jdbc:mysql://localhost/contabilidad_pl";
        String password="noravalencia";
        ResultSet resultado;
        /////////////////////////////////////////////////////
        int op=0;
        Scanner scanf= new Scanner(System.in);
        int a=1,cont=0;
        String producto; // nombre del producto
        int cantidad;    // cantidad del producto
        int precio;    // precio por unidad del producto
        int venta=0;
        int auxi=0;
        double total_venta=0;
        double ganancia=0;
        Pattern patron = Pattern.compile("[^A-Za-z ]");
        Matcher cadena;
        Pattern patron1 = Pattern.compile("[^0-9 ]");
        Matcher cadena1 ;
        Pattern patron2 = Pattern.compile("[^0-9 ]");
        Matcher cadena2 ;
        String data1,data2;
        String data;
        /////////////////////////////////////////////////////
        try{
            System.out.println("Conectando a base de datos ...");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url2,user2,password2);
            Statement estado= con.createStatement();
            
             do{System.out.println("\n");
                System.out.println("Ingrese la operacion que desea realizar");
                System.out.println("1. Agregar producto");
                System.out.println("2. Buscar producto");
                System.out.println("3. Eliminar producto");
                System.out.println("4. Mostrar inventario");
                System.out.println("5. Realizar venta");
                System.out.println("6. Mostrar ganancias totales");
                System.out.println("7. Salir");
                System.out.print("Opcion: ");
                op=scanf.nextInt();
                switch(op){
                    case 1:
                        while(a==1){

                            if(cont==100){
                                System.out.println("No es posible ingresar mas productos");
                                a=2;
                                cont=0;
                            }else{
                                System.out.print("Nombre del producto:    ");
                                producto=scanf.next();
                                cadena = patron.matcher(producto);
                                while(cadena.find()){
                                    System.out.println("Palabra invalida...");
                                    System.out.print("Nombre del producto:    ");
                                    producto=scanf.next();
                                    cadena = patron.matcher(producto);
                                }
                                System.out.print("Cantidad de producto que ingresa:    ");
                                data1=scanf.next();
                                cadena1 = patron1.matcher(data1);
                                while(cadena1.find()){
                                    System.out.println("Palabra invalida...");
                                    System.out.print("Cantidad de producto que ingresa:    ");
                                    data1=scanf.next();
                                    cadena1 = patron1.matcher(data1);
                                }
                                cantidad=Integer.parseInt(data1);

                                System.out.print("Valor individual del producto:    ");
                                data2=scanf.next();
                                cadena2 = patron2.matcher(data2);
                                while(cadena2.find()){
                                    System.out.println("Palabra invalida...");
                                    System.out.print("Valor individual del producto:    ");
                                    data2=scanf.next();
                                    cadena2 = patron2.matcher(data2);
                                }
                                precio=Integer.parseInt(data2);
                                estado.executeUpdate("INSERT INTO `productos`(`nameProducto`, `cantidad`, `precio`, `venta`, `totalVenta`) VALUES ('"+producto+"','"+cantidad+"','"+precio+"',NULL,NULL);"); 
                                cont++;
                                System.out.println("Si desea ingresar mas productos digite 1 de lo contrario 2");
                                a=scanf.nextInt();
                            }

                        }
                        break;
                    case 2:
                        a=1;
                        System.out.print("Nombre del producto: ");
                        data=scanf.next();
                        cadena = patron.matcher(data);
                         while(cadena.find()){
                            System.out.println("Palabra invalida...");
                            System.out.print("Nombre del producto:    ");
                            data=scanf.next();
                            cadena = patron.matcher(data);
                        }
                        resultado=estado.executeQuery("SELECT * FROM `productos` WHERE `nameProducto` LIKE '"+data+"' ");
                        if(!resultado.first()){
                            System.out.println("El producto no existe ");
                        }else{
                            resultado=estado.executeQuery("SELECT * FROM `productos` WHERE `nameProducto` LIKE '"+data+"' ");
                            System.out.println("PRODUCTO"+"\t"+"CANTIDAD"+"\t"+"PRECIO");
                            while(resultado.next()){
                                System.out.println(resultado.getString("nameProducto")+"\t\t"+resultado.getInt("cantidad")+"\t\t"+resultado.getInt("precio"));
                            }
                        }
                        break;
                    case 3:
                        a=1;
                        System.out.print("Nombre del producto: ");
                        data=scanf.next();
                        cadena = patron.matcher(data);
                         while(cadena.find()){
                            System.out.println("Palabra invalida...");
                            System.out.print("Nombre del producto:    ");
                            data=scanf.next();
                            cadena = patron.matcher(data);
                        }
                        resultado=estado.executeQuery("SELECT * FROM `productos` WHERE `nameProducto` LIKE '"+data+"' ");
                        if(!resultado.first()){
                            System.out.println("El producto no existe ");
                        }else{
                            estado.executeUpdate("DELETE FROM `productos` WHERE `nameProducto`= '"+data+"'");
                            System.out.println("El producto se ha eliminado correctamente...");
                        }
                        
                        break;
                    case 4:
                        a=1;
                        resultado=estado.executeQuery("SELECT * FROM `productos`");
                        if(!resultado.first()){
                            System.out.println("No hay productos registrados... ");
                        }else{
                             resultado=estado.executeQuery("SELECT * FROM `productos`"); // carga todos los contactos 
                            System.out.println("PRODUCTO"+"\t"+"CANTIDAD"+"\t"+"PRECIO");
                            while(resultado.next()){
                             System.out.println(resultado.getString("nameProducto")+"\t\t"+resultado.getString("cantidad")+"\t\t"+resultado.getString("precio"));
                            }
                        }
                       
                        break;
                    case 5:
                        a=1;
                        System.out.print("Nombre del producto: ");
                        data=scanf.next();
                        cadena = patron.matcher(data);
                        while(cadena.find()){
                            System.out.println("Palabra invalida...");
                            System.out.print("Nombre del producto:    ");
                            data=scanf.next();
                            cadena = patron.matcher(data);
                        }
                        resultado=estado.executeQuery("SELECT * FROM `productos` WHERE `nameProducto` LIKE '"+data+"' ");
                        if(!resultado.first()){
                            System.out.println("El producto no existe ");
                        }else{
                            
                            System.out.println("Cantidad de "+data+" que se va a vender: ");
                            auxi=scanf.nextInt();
                            //resultado=estado.executeQuery("SELECT * FROM `productos` WHERE `nameProducto` LIKE '"+data+"' ");
                            cantidad=resultado.getInt("cantidad");
                           // System.out.println(cantidad);
                            if(cantidad==0){
                                System.out.println("Este producto esta agotado");  
                            }else
                            if(cantidad<auxi){
                                System.out.println("No hay las suficientes unidades");
                            }else{
                                venta=resultado.getInt("venta");
                                precio=resultado.getInt("precio"); 
                                venta=venta+auxi;
                                estado.executeUpdate("UPDATE `productos` SET `venta`='"+venta+"' WHERE`nameProducto`='"+data+"'  "); 
                                cantidad=cantidad-auxi;
                                total_venta=venta*precio;
                                estado.executeUpdate("UPDATE `productos` SET `cantidad`='"+cantidad+"' WHERE`nameProducto`='"+data+"' ");
                                estado.executeUpdate("UPDATE `productos` SET `totalVenta`='"+total_venta+"' WHERE`nameProducto`='"+data+"' ");
                                System.out.println("Valor de la venta: "+(auxi*precio));
                                System.out.println("Venta realizada excitosamente .....");
                                
                                }
                            
                        }
                        break;
                    case 6:
                         a=1;
                        resultado=estado.executeQuery("SELECT * FROM `productos`");
                        if(!resultado.first()){
                            System.out.println("No hay productos registrados... ");
                        }else{
                            ganancia=0;
                             resultado=estado.executeQuery("SELECT * FROM `productos`"); // carga todos los contactos 
                            System.out.println("PRODUCTO"+"\t"+"CANTIDAD"+"\t"+"PRECIO"+"\t\t"+"VENTA"+"\t\t"+"TOTAL DE VENTA");
                            while(resultado.next()){
                             System.out.println(resultado.getString("nameProducto")+"\t\t"+resultado.getString("cantidad")+"\t\t"+resultado.getString("precio")+"\t\t"+resultado.getString("venta")+"\t\t"+resultado.getString("totalVenta"));
                             ganancia=resultado.getInt("totalVenta")+ganancia;
                             
                            }
                            System.out.println("Las ganancias totales son: "+ganancia);
                        }
                        break;    
                }
              }while(op!=7);
            
            System.out.println("conexion exitosa...");
        }catch(SQLException ex){
           System.out.println("Error de mysql");
       }catch(Exception e){
            System.out.println("Se a encotrado un error de tipo:"+e.getMessage()); // nos damos cuenta de el error
       }
    }
    
}
