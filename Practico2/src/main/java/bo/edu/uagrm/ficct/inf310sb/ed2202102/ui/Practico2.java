/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.ui;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.DiGrafo;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionHayCiclo;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.Grafo;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.MatrizDeCaminos;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.OrdenamientoTopologico;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados.AlgoritmoDeDijkstra;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados.AlgoritmoDeFloyd;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados.AlgoritmoDeKruskal;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados.AlgoritmoDePrim;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados.DFSGrafoPesadoNoDirigido;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados.DiGrafoPesado;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados.GrafoPesado;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Andres
 */
public class Practico2 {
    public static void main(String[] args) throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste, ExcepcionHayCiclo {
        int opcion;
        boolean salir = false;
        Scanner in = new Scanner(System.in); 
        
        while (! salir) {            
            System.out.println("--------------Menu Principal--------------------");
            System.out.println("1 Resultados de todos los metodos del Practico");
            System.out.println("2 Menu GrafoNodirigido");
            System.out.println("3 Menu Digrafo");
            System.out.println("4 Menu GrafoPesadoNodirigido");
            System.out.println("5 Menu DigrafoPesado");
            System.out.println("6 Salir");
            try {
                //System.out.println("Elija una opcion: ");
                opcion = in.nextInt();

                switch(opcion) {
                    case 1:
                            resultados();
                            break;
                    case 2: 
                            menuGrafoNodirigido();
                            break;
                    case 3:
                            menuDiGrafo();
                            break;
                    case 4:
                            menuGrafoNodirigidoPesado();
                            break;
                    case 5:
                            menuDiGrafoPesado();
                            break;
                    case 6: salir = true;
                            break;

                    default: System.out.println("Opcion invalida Digite nuevamente");           
                }
            }catch(InputMismatchException e) {
                System.out.println("Debe introducir un numero");
                in.next();
            }
            System.out.println("");
        }
        System.out.println("Fin del menu");        
    }
    
    public static void resultados() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste, ExcepcionHayCiclo {
        Grafo grafo = new Grafo(10);
        DiGrafo diGrafo = new DiGrafo(6);
        GrafoPesado grafoPesado = new GrafoPesado(11);
        DiGrafoPesado diGrafoPesado = new DiGrafoPesado(5);
        
        grafo.insertarArista(0, 8);
        grafo.insertarArista(1, 4);
        grafo.insertarArista(4, 5);
        grafo.insertarArista(5, 3);
        grafo.insertarArista(5, 2);
        grafo.insertarArista(2, 7);
        grafo.insertarArista(7, 5);
        grafo.insertarArista(9, 8);
        
        diGrafo.insertarArista(0, 1);
        diGrafo.insertarArista(1, 4);
        diGrafo.insertarArista(1, 3);
        diGrafo.insertarArista(1, 2);
        diGrafo.insertarArista(2, 4);
        diGrafo.insertarArista(3, 2);
        diGrafo.insertarArista(1, 5);
        diGrafo.insertarArista(4, 5);
        diGrafo.insertarArista(3, 5);        
        
        grafoPesado.insertarArista(0, 1,4);
        grafoPesado.insertarArista(1, 2,5);
        grafoPesado.insertarArista(1, 3,10);
        grafoPesado.insertarArista(1, 4,8);
        grafoPesado.insertarArista(2, 4,6);
        grafoPesado.insertarArista(2, 6,5);
        grafoPesado.insertarArista(3, 4,7);        
        grafoPesado.insertarArista(3, 5,8);        
        grafoPesado.insertarArista(3, 8,15);
        grafoPesado.insertarArista(4, 6,11);        
        grafoPesado.insertarArista(4, 5,5);        
        grafoPesado.insertarArista(5, 7,4);        
        grafoPesado.insertarArista(5, 8,3);        
        grafoPesado.insertarArista(6, 7,9);        
        grafoPesado.insertarArista(6, 9,7);
        grafoPesado.insertarArista(7, 9,4);        
        grafoPesado.insertarArista(7, 10,6);     
        grafoPesado.insertarArista(8, 7,12);        
        grafoPesado.insertarArista(8, 10,12);
        grafoPesado.insertarArista(9, 10,7);
        
        diGrafoPesado.insertarArista(0, 1,1);
        diGrafoPesado.insertarArista(1, 3,4);
        diGrafoPesado.insertarArista(1, 4,7);
        diGrafoPesado.insertarArista(2, 0,3);
        diGrafoPesado.insertarArista(2, 1,2);
        diGrafoPesado.insertarArista(2, 4,4);
        diGrafoPesado.insertarArista(3, 0,6);   
        diGrafoPesado.insertarArista(3, 4,2);
        diGrafoPesado.insertarArista(4, 3,3);        

        
        System.out.println("Ejercicio 1.- Para un grafo no dirigido implementar los metodos: ");
        System.out.println( "insertarVertice");
        grafo.insertarVertice();
        System.out.println( "insertarArista (0,10)");
        grafo.insertarArista(0, 10);
        System.out.println( "eliminarVertice (10)");
        grafo.eliminarVertice(10);
        System.out.println( "eliminarArista (5,3)");
        grafo.eliminarArista(5, 3);
        System.out.println( "cantidadDeVertices: "  + grafo.cantidaDeVertices() );
        System.out.println( "cantidadDeArista: "  + grafo.cantidadDeAristas() );
        System.out.println( "gradoDeVertice(0): "  + grafo.gradoDeVertice(0) );
        
        System.out.println("Ejercicio 2.- Para un Digrafo implementar los metodos: ");
        System.out.println( "insertarVertice");
        diGrafo.insertarVertice();
        System.out.println( "insertarArista (0,6)");
        diGrafo.insertarArista(0, 6);
        System.out.println( "eliminarVertice (6)");
        diGrafo.eliminarVertice(6);
        System.out.println( "eliminarArista (3,5)");
        diGrafo.eliminarArista(3, 5);
        System.out.println( "cantidadDeVertices: "  + diGrafo.cantidaDeVertices() );
        System.out.println( "cantidadDeArista: "  + diGrafo.cantidadDeAristas());
        System.out.println( "gradoDeEntradaVertice(0): "  + diGrafo.gradoDeEntradaDeVertice(0) );
        System.out.println( "gradoDeSalidaDeVertice(0): "  + diGrafo.gradoDeSalidaDeVertice(0) );
        
        
        System.out.println("Ejercicio 3.- Para Digrafo Con DFS hay Ciclo?: " + diGrafo.hayCicloConDFS());
        System.out.println("Ejercicio 4.- Para Digrafo Con Matriz de caminos hay Ciclo?: " + diGrafo.hayCicloConMatriz());
        System.out.println("");
        
        System.out.println("Ejercicio 5.- Para Digrafo retornar los componentes que hay en las diferentes islas: " + diGrafo.componentesDeIslas());
        System.out.println("Ejercicio 6.- Para  Digrafo Encontrar la Matriz de caminos: ");
        MatrizDeCaminos matriz = new MatrizDeCaminos(diGrafo);
        System.out.println(matriz.mostrar());
        System.out.println("");
        
        System.out.println("Ejercicio 7.- Para Digrafo es debilmente conexo?: " + diGrafo.esDebilmenteConexoConRecorridoDFS());   
        System.out.println("Ejercicio 8.- Para Digrafo es fuertemente conexo?: " + diGrafo.esFuertementeConexoConRecorridoDFS());
        System.out.println("Ejercicio 9.- Para Grafo no dirigido hayciclo?: " + grafo.hayCicloConDFS());
        System.out.println("");
        

        System.out.println("Ejercicio 10.- Para Grafo no dirigido encontrar los componentes que hay en las diferentes islas: " + grafo.componentesDeIslas());
        System.out.println("");
       
        System.out.println("Ejercicio 11.- Para Digrafo Numero de Islas: " + diGrafo.cantDeIslasConRecorridoDFS());
        System.out.println("");
        
        System.out.println("Ejercicio 12.- Para Digrafo implementar el algoritmo de Wharsall" );
        System.out.println("Entre que vertices hay camino?: ");
        System.out.println(matriz.mostrarCaminos());
        
        System.out.println("Ejercicio 13.- Para un Digrafo implementar el algoritmo de FLoyd-Wharsall");
        System.out.println("Caminos y costos minimos del vertice 0 a sus vertices adyacentes: ");
        AlgoritmoDeFloyd floyd = new AlgoritmoDeFloyd(diGrafoPesado);
        
        for (int i = 0; i < diGrafoPesado.cantidaDeVertices(); i++) {
            try {
                System.out.println("Camino del vertice 0 al " + i + ": " + floyd.getCamino(0, i) + " y Costo minimo :" + floyd.getCostoMinimo(0, i));
            } catch (ExcepcionAristaNoExiste e) {
                System.out.println("no hay camino del 0 al " + i);
            }
            
        }
        System.out.println("");
        
        System.out.println("Ejercicio 14.- Para un digrafo retornar cuantos componentes\n" +
                              "fuertemente conexas hay en dicho grafo."); 
        System.out.println("");
        
        System.out.println("Ejercicio 15.- Para un Digrafo Pesado implemntar el algoritmo de Dijkstra");
        System.out.println("Caminos y costos minimos del vertice 0 a sus vertices adyacentes: ");
         AlgoritmoDeDijkstra dijkstra = new AlgoritmoDeDijkstra(diGrafoPesado);
        for (int i = 0; i < diGrafoPesado.cantidaDeVertices(); i++) {
            try {
                dijkstra.encontrarCaminoYcostosMinimos(0, i);
                System.out.println("Camino del vertice 0 al " + i + ": " + dijkstra.getCaminoCostoMinimo(i) + " y Costo minimo :" + dijkstra.getCostoMinimo(i));
            } catch (Exception e) {
                System.out.println("no hay camino del 0 al " + i);
            }
            
        }
        System.out.println("");
        
        System.out.println("Ejercicio 16.- Para un GrafoNodirigido Pesado implemntar el algoritmo de Kruskal\n" + 
                "mostrar el grafo encontrado por el algoritmo: ");
        AlgoritmoDeKruskal kruskal = new AlgoritmoDeKruskal(grafoPesado);
        GrafoPesado grafoAux = kruskal.getGrafoAux();
        System.out.println("Recorrido DFS del grafo encontrado: " + grafoAux.recorridoDFS(0));
        System.out.println("");
        
        System.out.println("Ejercicio 17.- Para un GrafoNodirigido Pesado implementar el algoritmo de prim que muestre cual es el\n" +
                            "grafo encontrado por el algoritmo");
        AlgoritmoDePrim prim = new AlgoritmoDePrim(grafoPesado,0);
        GrafoPesado grafoAux2 = prim.getGrafoAux();
        System.out.println("Recorrido DFS del grafo encontrado: " + grafoAux2.recorridoDFS(0));
        System.out.println("");
        
        System.out.println("Ejercicio18.- Para un Digrafo implementar el algoritmo de ordenamiento topologico. . Debe mostrar\n" +
                            "cual es el orden de los vértices según este algoritmo.");
        OrdenamientoTopologico ordenamiento = new OrdenamientoTopologico(diGrafo);
        try {
            System.out.println("orden de los vertices: " + ordenamiento.ordenar());            
        } catch (Exception e) {
            System.out.println("no se puede ordenar por que existe ciclo");
        }
        System.out.println("");
        System.out.println("Ejercicio 19.- Para un Digrafo pesado implementar el algoritmo de Ford-Fulkerson");
        }         
    
    
    public static void menuGrafoNodirigido() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        int opcion;
        boolean salir = false;
        Scanner in = new Scanner(System.in); 
        Grafo grafo  = null;
        while (!salir) {            
            System.out.println("--------------Menu GrafoNodirigido----------------------------");
            System.out.println("1 Crear Grafo");
            System.out.println("2 Insertar Vertice");
            System.out.println("3 Insertar Arista");
            System.out.println("4 insertar vertices y Aristas Predeterminadas");
            System.out.println("5 Eliminar Vertice");
            System.out.println("6 Eliminar Arista");
            System.out.println("7 Cantidad de Vertices");
            System.out.println("8 Cantidad de Aristas");
            System.out.println("9 grado de Vertice");
            System.out.println("10 Es conexo?");
            System.out.println("11 cantidad de Islas");
            System.out.println("12 Recorrido DFS");
            System.out.println("13 Ejercicio 9.- hay ciclo?");
            System.out.println("14 Ejercicio 10.- Encontrar los componentes que hay en las diferentes islas ");
            System.out.println("15 salir");
            
            
            
            try {
                System.out.println("Elija una opcion: ");
                opcion = in.nextInt();
                
                switch(opcion) {
                    case 1: System.out.println("Introduzda el numero de vertices: ");
                            int nroDeVertices = in.nextInt();
                            grafo = new Grafo(nroDeVertices); 
                            break;
                    case 2: 
                            grafo.insertarVertice();
                            System.out.println("vertice insertado correctamente");
                            break;
                    case 3: 
                            System.out.println("Introduzca el Vertice Origen");
                            Integer verticeOrigen = in.nextInt();
                            System.out.println("Introduzca el Vertice Destino");
                            Integer verticeDestino = in.nextInt();                            
                            grafo.insertarArista(verticeOrigen, verticeDestino);
                            break;
                    case 4: 
                            grafo = new Grafo(10);
                            grafo.insertarArista(0, 8);
                            grafo.insertarArista(1, 4);
                            grafo.insertarArista(4, 5);
                            grafo.insertarArista(5, 3);
                            grafo.insertarArista(5, 2);
                            grafo.insertarArista(2, 7);
                            grafo.insertarArista(7, 5);
                            grafo.insertarArista(9, 8);
                            break;
                    case 5: 
                            System.out.println("Introduzca el vertice a eliminar");
                            int posVerticeAEliminar = in.nextInt();
                            grafo.eliminarVertice(posVerticeAEliminar);
                            break;
                    case 6:
                            System.out.println("Introduzca el Vertice Origen");
                            Integer posVerticeOrigen = in.nextInt();
                            System.out.println("Introduzca el Vertice Destino");
                            Integer posVerticeDestino = in.nextInt();                            
                            grafo.eliminarArista(posVerticeOrigen,posVerticeDestino);
                            break;
                    case 7:
                            System.out.println( "cantidadDeVertices: "  + grafo.cantidaDeVertices() );
                            break;
                    case 8:
                            System.out.println( "cantidadDeAristas: "  + grafo.cantidadDeAristas() );
                            break;  
                    case 9:
                            System.out.println("Introduzca la posicion del vertice");
                            int posVertice = in.nextInt();                        
                            System.out.println( "gradoDeVertice (" + posVertice + ") :" + grafo.gradoDeVertice(posVertice) );
                            break; 
                    case 10:
                            System.out.println(grafo.esConexo());
                            break;
                    case 11:
                            System.out.println("Cantidad de Islas: " + grafo.cantidadDeIslas());
                            break;
                            
                    case 12:
                            System.out.println("Introduzca el vertice de partida");
                            int verticeDePartida = in.nextInt();                             
                            System.out.println("Recorrido DFS: " + grafo.recorridoDFS(verticeDePartida));
                            break;                            
                             
                    case 13: 
                            System.out.println(grafo.hayCicloConDFS());
                            break;       
                    
                    case 14: 
                            System.out.println("Componentes de las diferentes Islas: " + grafo.componentesDeIslas());
                            break;         
                            
                    case 15: salir = true;
                            break;

                    default: System.out.println("Opcion invalida Digite nuevamente");           
                }
            }catch(InputMismatchException e) {
                System.out.println("Debe introducir un numero");
                in.next();
            }
            System.out.println("");
        }
        System.out.println("Fin del menu");        
    }
    
    public static void menuDiGrafo() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        int opcion;
        boolean salir = false;
        Scanner in = new Scanner(System.in); 
        DiGrafo digrafo  = null;
        while (!salir) {            
            System.out.println("--------------Menu Digrafo----------------------------");
            System.out.println("1 Crear Grafo");
            System.out.println("2 Insertar Vertice");
            System.out.println("3 Insertar Arista");
            System.out.println("4 insertar vertices y Aristas Predeterminadas");
            System.out.println("5 Eliminar Vertice");
            System.out.println("6 Eliminar Arista");
            System.out.println("7 Cantidad de Vertices");
            System.out.println("8 Cantidad de Aristas");
            System.out.println("9 grado de Vertice de entrada");
            System.out.println("10 grado de Vertice de salida");
            System.out.println("11 Recorrido DFS");
            System.out.println("12 Ejercicio 3.- Utilizando Recorrido DFS hay ciclo? ");
            System.out.println("13 Ejercicio 4.- Utilizando Matriz de caminos hay Ciclo?");
            System.out.println("14 Ejercicio 5.- Encontrar los componentes que hay en las diferentes islas");
            System.out.println("15 Ejercicio 6.- Encontrar la Matriz de caminos");
            System.out.println("16 Ejercicio 7.- Es debilmente conexo?");
            System.out.println("17 Ejercicio 8.- Es fuertemente conexo?");
            System.out.println("18 Ejercicio 11.- Numero de Islas");
            System.out.println("19 Ejercicio 12.- Implementar el algoritmo de Wharsall. Mostrar entre que vertices hay camino");
            System.out.println("20 Ejercicio 14.- ");
            System.out.println("21 Ejercicio 18.- Ordenamiento topologico mostrar vertices ordenados");
            System.out.println("22 salir");
            
            
            
            try {
                System.out.println("Elija una opcion: ");
                opcion = in.nextInt();
                
                switch(opcion) {
                    case 1: System.out.println("Introduzda el numero de vertices: ");
                            int nroDeVertices = in.nextInt();
                            digrafo = new DiGrafo(nroDeVertices); 
                            break;
                    case 2: 
                            digrafo.insertarVertice();
                            System.out.println("vertice insertado correctamente");
                            break;
                    case 3: 
                            System.out.println("Introduzca el Vertice Origen");
                            Integer verticeOrigen = in.nextInt();
                            System.out.println("Introduzca el Vertice Destino");
                            Integer verticeDestino = in.nextInt();                            
                            digrafo.insertarArista(verticeOrigen, verticeDestino);
                            break;
                    case 4: 
                            digrafo = new DiGrafo(10);
                            digrafo.insertarArista(0, 8);
                            digrafo.insertarArista(1, 4);
                            digrafo.insertarArista(4, 5);
                            digrafo.insertarArista(5, 3);
                            digrafo.insertarArista(5, 2);
                            digrafo.insertarArista(2, 7);
                            digrafo.insertarArista(7, 5);
                            digrafo.insertarArista(9, 8);
                            break;
                    case 5: 
                            System.out.println("Introduzca el vertice a eliminar");
                            int posVerticeAEliminar = in.nextInt();
                            digrafo.eliminarVertice(posVerticeAEliminar);
                            break;
                    case 6:
                            System.out.println("Introduzca el Vertice Origen");
                            Integer posVerticeOrigen = in.nextInt();
                            System.out.println("Introduzca el Vertice Destino");
                            Integer posVerticeDestino = in.nextInt();                            
                            digrafo.eliminarArista(posVerticeOrigen,posVerticeDestino);
                            break;
                    case 7:
                            System.out.println( "cantidadDeVertices: "  + digrafo.cantidaDeVertices() );
                            break;
                    case 8:
                            System.out.println( "cantidadDeAristas: "  + digrafo.cantidadDeAristas() );
                            break;  
                    case 9:
                            System.out.println("Introduzca la posicion del vertice");
                            int posVertice = in.nextInt();                        
                            System.out.println( "grado De Entrada De Vertice (" + posVertice + ") :" + digrafo.gradoDeEntradaDeVertice(posVertice) );
                            break; 
                    case 10:
                            System.out.println("Introduzca la posicion del vertice");
                            int posVertice2 = in.nextInt();                        
                            System.out.println( "grado De Salida De Vertice (" + posVertice2 + ") :" + digrafo.gradoDeSalidaDeVertice(posVertice2) );
                            break;

                    case 11:
                            System.out.println("Introduzca el vertice de partida");
                            int verticeDePartida = in.nextInt();                             
                            System.out.println("Recorrido DFS: " + digrafo.recorridoDFS(verticeDePartida));                            
                            break;
                    case 12:
                            System.out.println(digrafo.hayCicloConDFS());
                            break;                            
                             
                    case 13:
                            System.out.println(digrafo.hayCicloConMatriz());
                            break;       
                    
                    case 14: 
                            System.out.println("Componentes de las diferentes Islas: " + digrafo.componentesDeIslas());
                            break;       
                    case 15:
                            MatrizDeCaminos matriz = new MatrizDeCaminos(digrafo);
                            System.out.println(matriz.mostrar());

                            break; 
                    case 16:
                            System.out.println( digrafo.esDebilmenteConexoConRecorridoDFS());
                            break; 
                    case 17: 
                            System.out.println(digrafo.esFuertementeConexoConRecorridoDFS());
                            break; 
                    case 18: 
                            System.out.println("Cantidad de Islas: " + digrafo.cantidadDeIslas());
                            break; 
                    case 19: 
                            MatrizDeCaminos matriz2 = new MatrizDeCaminos(digrafo);
                            System.out.println(matriz2.mostrarCaminos());
                            break; 
                    case 20:
                            
                            break;   
                    case 21:
                            OrdenamientoTopologico ordenamiento = new OrdenamientoTopologico(digrafo);
                            try {
                                System.out.println("orden de los vertices: " + ordenamiento.ordenar());            
                            } catch (Exception e) {
                                System.out.println("no se puede ordenar por que existe ciclo");
                            }                          
                            break;                              
                            
                    case 22: salir = true;
                            break;

                    default: System.out.println("Opcion invalida Digite nuevamente");           
                }
            }catch(InputMismatchException e) {
                System.out.println("Debe introducir un numero");
                in.next();
            }
            System.out.println("");
        }
        System.out.println("Fin del menu");        
    }

    public static void menuGrafoNodirigidoPesado() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        int opcion;
        boolean salir = false;
        Scanner in = new Scanner(System.in); 
        GrafoPesado grafoPesado  = null;
        while (!salir) {            
            System.out.println("--------------Menu GrafoNodirigido Pesado----------------------------");
            System.out.println("1 Crear Grafo");
            System.out.println("2 Insertar Vertice");
            System.out.println("3 Insertar Arista");
            System.out.println("4 insertar vertices y Aristas Predeterminadas");
            System.out.println("5 Eliminar Vertice");
            System.out.println("6 Eliminar Arista");
            System.out.println("7 Cantidad de Vertices");
            System.out.println("8 Cantidad de Aristas");
            System.out.println("9 grado de Vertice");
            System.out.println("10 Es conexo?");
            System.out.println("11 cantidad de Islas");
            System.out.println("12 Recorrido DFS");
            System.out.println("13 Ejercicio 16.- Algoritmo de Kruskal mostrar el grafo encontrado por el algoritmo");
            System.out.println("14 Ejercicio 17.- Algoritmo de Prim mostrar el grafo encontrado por el algoritmo");
            System.out.println("15 salir");
            
            
            
            try {
                System.out.println("Elija una opcion: ");
                opcion = in.nextInt();
                
                switch(opcion) {
                    case 1: System.out.println("Introduzda el numero de vertices: ");
                            int nroDeVertices = in.nextInt();
                            grafoPesado = new GrafoPesado(nroDeVertices); 
                            break;
                    case 2: 
                            grafoPesado.insertarVertice();
                            System.out.println("vertice insertado correctamente");
                            break;
                    case 3: 
                            System.out.println("Introduzca el Vertice Origen");
                            Integer verticeOrigen = in.nextInt();
                            System.out.println("Introduzca el Vertice Destino");
                            Integer verticeDestino = in.nextInt();   
                            System.out.println("Introduzca el Peso");
                            Integer peso = in.nextInt();                             
                            grafoPesado.insertarArista(verticeOrigen, verticeDestino,peso);
                            break;
                    case 4: 
                            grafoPesado = new GrafoPesado(11);
                            grafoPesado.insertarArista(0, 1, 4);
                            grafoPesado.insertarArista(1, 2,5);
                            grafoPesado.insertarArista(1, 3,10);
                            grafoPesado.insertarArista(1, 4,8);
                            grafoPesado.insertarArista(2, 4,6);
                            grafoPesado.insertarArista(2, 6,5);
                            grafoPesado.insertarArista(3, 4,7);        
                            grafoPesado.insertarArista(3, 5,8);        
                            grafoPesado.insertarArista(3, 8,15);
                            grafoPesado.insertarArista(4, 6,11);        
                            grafoPesado.insertarArista(4, 5,5);        
                            grafoPesado.insertarArista(5, 7,4);        
                            grafoPesado.insertarArista(5, 8,3);        
                            grafoPesado.insertarArista(6, 7,9);        
                            grafoPesado.insertarArista(6, 9,7);
                            grafoPesado.insertarArista(7, 9,4);        
                            grafoPesado.insertarArista(7, 10,6);     
                            grafoPesado.insertarArista(8, 7,12);        
                            grafoPesado.insertarArista(8, 10,12);
                            grafoPesado.insertarArista(9, 10,7);
                            break;
                    case 5: 
                            System.out.println("Introduzca el vertice a eliminar");
                            int posVerticeAEliminar = in.nextInt();
                            grafoPesado.eliminarVertice(posVerticeAEliminar);
                            break;
                    case 6:
                            System.out.println("Introduzca el Vertice Origen");
                            Integer posVerticeOrigen = in.nextInt();
                            System.out.println("Introduzca el Vertice Destino");
                            Integer posVerticeDestino = in.nextInt();                            
                            grafoPesado.eliminarArista(posVerticeOrigen,posVerticeDestino);
                            break;
                    case 7:
                            System.out.println( "cantidadDeVertices: "  + grafoPesado.cantidaDeVertices() );
                            break;
                    case 8:
                            System.out.println( "cantidadDeAristas: "  + grafoPesado.cantidadDeAristas() );
                            break;  
                    case 9:
                            System.out.println("Introduzca la posicion del vertice");
                            int posVertice = in.nextInt();                        
                            System.out.println( "gradoDeVertice (" + posVertice + ") :" + grafoPesado.gradoDeVertice(posVertice) );
                            break; 
                    case 10:
                            System.out.println(grafoPesado.esConexo());
                            break;
                    case 11:
                            System.out.println("Cantidad de Islas: " + grafoPesado.cantidadDeIslas());
                            break;
                            
                    case 12:
                            System.out.println("Introduzca el vertice de partida");
                            int verticeDePartida = in.nextInt();                             
                            System.out.println("Recorrido DFS: " + grafoPesado.recorridoDFS(verticeDePartida));                            
                            break;                            
                             
                    case 13: 
                            AlgoritmoDeKruskal kruskal = new AlgoritmoDeKruskal(grafoPesado);
                            GrafoPesado grafoAux = kruskal.getGrafoAux();
                            System.out.println("Introduzca el vertice de partida para mostrar el grafo");
                            int verticePartida = in.nextInt();                              
                            System.out.println("Recorrido DFS del grafo encontrado: " + grafoAux.recorridoDFS(verticePartida));
                            break;       
                    
                    case 14:
                            System.out.println("Introduzca el vertice de partida");
                            int verticePartida2 = in.nextInt();                             
                            AlgoritmoDePrim prim = new AlgoritmoDePrim(grafoPesado,verticePartida2);
                            GrafoPesado grafoAux2 = prim.getGrafoAux();
                            System.out.println("Recorrido DFS del grafo encontrado: " + grafoAux2.recorridoDFS(verticePartida2));

                            break;         
                            
                    case 15: salir = true;
                            break;

                    default: System.out.println("Opcion invalida Digite nuevamente");           
                }
            }catch(InputMismatchException e) {
                System.out.println("Debe introducir un numero");
                in.next();
            }
            System.out.println("");
        }
        System.out.println("Fin del menu");        
    }
    
    public static void menuDiGrafoPesado() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        int opcion;
        boolean salir = false;
        Scanner in = new Scanner(System.in); 
        DiGrafoPesado digrafoPesado  = null;
        while (!salir) {            
            System.out.println("--------------Menu Digrafo Pesado----------------------------");
            System.out.println("1 Crear Grafo");
            System.out.println("2 Insertar Vertice");
            System.out.println("3 Insertar Arista");
            System.out.println("4 insertar vertices y Aristas Predeterminadas");
            System.out.println("5 Eliminar Vertice");
            System.out.println("6 Eliminar Arista");
            System.out.println("7 Cantidad de Vertices");
            System.out.println("8 Cantidad de Aristas");
            System.out.println("9 grado de Vertice de entrada");
            System.out.println("10 grado de Vertice de salida");
            System.out.println("11 Recorrido DFS");
            System.out.println("12 hay ciclo? ");
            System.out.println("13 Ejercicio 13.- Con Floyd-Wharsall, Mostrar los caminos y costo minimo entre un vertice A y el resto");
            System.out.println("14 Ejercicio 15.- Con Dijkstra, Mostrar los caminos y costo minimo entre un vertice V y el resto");
            System.out.println("15 Ejercicio 19.- Con Ford-Fulkerson mostrar el mayor flujo");
            System.out.println("16 salir");
            
            
            
            try {
                System.out.println("Elija una opcion: ");
                opcion = in.nextInt();
                
                switch(opcion) {
                    case 1: System.out.println("Introduzda el numero de vertices: ");
                            int nroDeVertices = in.nextInt();
                            digrafoPesado = new DiGrafoPesado(nroDeVertices); 
                            break;
                    case 2: 
                            digrafoPesado.insertarVertice();
                            System.out.println("vertice insertado correctamente");
                            break;
                    case 3: 
                            System.out.println("Introduzca el Vertice Origen");
                            Integer verticeOrigen = in.nextInt();
                            System.out.println("Introduzca el Vertice Destino");
                            Integer verticeDestino = in.nextInt(); 
                            System.out.println("Introduzca el Peso");
                            Integer peso = in.nextInt();                             
                            digrafoPesado.insertarArista(verticeOrigen, verticeDestino, peso);
                            break;
                    case 4: 
                            digrafoPesado = new DiGrafoPesado(5);
                            digrafoPesado.insertarArista(0, 1,1);
                            digrafoPesado.insertarArista(1, 3,4);
                            digrafoPesado.insertarArista(1, 4,7);
                            digrafoPesado.insertarArista(2, 0,3);
                            digrafoPesado.insertarArista(2, 1,2);
                            digrafoPesado.insertarArista(2, 4,4);
                            digrafoPesado.insertarArista(3, 0,6);   
                            digrafoPesado.insertarArista(3, 4,2);
                            digrafoPesado.insertarArista(4, 3,3); 

                            break;
                    case 5: 
                            System.out.println("Introduzca el vertice a eliminar");
                            int posVerticeAEliminar = in.nextInt();
                            digrafoPesado.eliminarVertice(posVerticeAEliminar);
                            break;
                    case 6:
                            System.out.println("Introduzca el Vertice Origen");
                            Integer posVerticeOrigen = in.nextInt();
                            System.out.println("Introduzca el Vertice Destino");
                            Integer posVerticeDestino = in.nextInt();                            
                            digrafoPesado.eliminarArista(posVerticeOrigen,posVerticeDestino);
                            break;
                    case 7:
                            System.out.println( "cantidadDeVertices: "  + digrafoPesado.cantidaDeVertices() );
                            break;
                    case 8:
                            System.out.println( "cantidadDeAristas: "  + digrafoPesado.cantidadDeAristas() );
                            break;  
                    case 9:
                            System.out.println("Introduzca la posicion del vertice");
                            int posVertice = in.nextInt();                        
                            System.out.println( "grado De Entrada De Vertice (" + posVertice + ") :" + digrafoPesado.gradoDeEntradaDeVertice(posVertice) );
                            break; 
                    case 10:
                            System.out.println("Introduzca la posicion del vertice");
                            int posVertice2 = in.nextInt();                        
                            System.out.println( "grado De Salida De Vertice (" + posVertice2 + ") :" + digrafoPesado.gradoDeSalidaDeVertice(posVertice2) );
                            break;
                    case 11:
                            System.out.println("Introduzca el vertice de partida");
                            int verticeDePartida = in.nextInt();                             
                            System.out.println("Recorrido DFS: " + digrafoPesado.recorridoDFS(verticeDePartida));                             
                            break;
                    case 12:
                            System.out.println(digrafoPesado.hayCicloConDFS());
                            break;                            
                             
                    case 13:
                            System.out.println("Introduzca el vertice A");
                            int verticeA = in.nextInt();
                            System.out.println("Caminos y costos minimos del vertice " +verticeA + " a sus vertices adyacentes: ");
                            AlgoritmoDeFloyd floyd = new AlgoritmoDeFloyd(digrafoPesado);

                            for (int i = 0; i < digrafoPesado.cantidaDeVertices(); i++) {
                                try {
                                    System.out.println("Camino del vertice " +verticeA + " al " + i + ": " + floyd.getCamino(verticeA, i) + " y Costo minimo :" + floyd.getCostoMinimo(verticeA, i));
                                } catch (ExcepcionAristaNoExiste e) {
                                    System.out.println("no hay camino del " +verticeA + " al " + i);
                                }

                            }
                            break;       
                            
                    case 14: 
                            System.out.println("Introduzca el vertice V");
                            int verticeV = in.nextInt();
                            System.out.println("Caminos y costos minimos del vertice " +verticeV + " a sus vertices adyacentes: ");
                             AlgoritmoDeDijkstra dijkstra = new AlgoritmoDeDijkstra(digrafoPesado);
                            for (int i = 0; i < digrafoPesado.cantidaDeVertices(); i++) {
                                try {
                                    dijkstra.encontrarCaminoYcostosMinimos(verticeV, i);
                                    System.out.println("Camino del vertice " +verticeV + " al " + i + ": " + dijkstra.getCaminoCostoMinimo(i) + " y Costo minimo :" + dijkstra.getCostoMinimo(i));
                                } catch (Exception e) {
                                    System.out.println("no hay camino del " +verticeV + " al " + i);
                                }

                            }
                            break;       
                    case 15:

                            break;                              
                            
                    case 16: salir = true;
                            break;

                    default: System.out.println("Opcion invalida Digite nuevamente");           
                }
            }catch(InputMismatchException e) {
                System.out.println("Debe introducir un numero");
                in.next();
            }
            System.out.println("");
        }
        System.out.println("Fin del menu");        
    } 
    
}
    

