/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afd.model;

import java.util.ArrayList;

/**
 *
 * @author hugoluna
 */
public class Automata {
    
    private int Q;
    private String alfabeto;
    private int q0;
    private ArrayList<Integer> F;
    private int S[][];
    private int symb = -1;

    
    public Automata() {
    }

    /**
     * constructor donde se asignan las variables para un nuevo objeto
     * @param Q establecer los estados
     * @param alfabeto establecer el alfabeto
     * @param q0 establecer el estado incial
     * @param F establecer los estados finales
     * @param S establcer el valor de la secuencia
     */
    public Automata(int Q, String alfabeto, int q0, ArrayList<Integer> F, int[][] S) {
        this.Q = Q;
        this.alfabeto = alfabeto;
        this.q0 = q0;
        this.F = F;
        this.S = S;
        
    }

    /**
     * salida de la variable Q
     * @return deveolver el valor de Q
     */
    public int getQ() {
        return Q;
    }

    /**
     * entrada de la variable Q
     * @param Q establecer el valor de Q
     */
    public void setQ(int Q) {
        this.Q = Q;
    }

    /**
     * salida de la variable Alfabeto
     * @return obtener la variable que guarda a el alfabeto
     */
    public String getAlfabeto() {
        return alfabeto;
    }

    /**
     * entrada de la variable Alfabeto
     * @param alfabeto establecer el valor del alfabeto
     */
    public void setAlfabeto(String alfabeto) {
        this.alfabeto = alfabeto;
    }

    /**
     * salida de la variable Q0
     * @return obtener el estado inicial
     */
    public int getQ0() {
        return q0;
    }

    /**
     *  Enttrada de la variable Q0
     * @param q0 establecer el estado inicial
     */
    public void setQ0(int q0) {
        this.q0 = q0;
    }

    /**
     * salida de la ArrayList F
     * @return retornar un ArrayList de los estados finales
     */
    public ArrayList<Integer> getF() {
        return F;
    }
    
    /**
     * salida de la variable F
     * @param i posicion del estado final
     * @return devolver el estado final correspondiente a la posicion
     */
    public int getF(int i) {
        return F.get(i);
    }

    /**
     *entrada de la variable F
     * @param F establecer el valor de F
     */
    public void setF(ArrayList<Integer> F) {
        this.F = F;
    }
    
    /**
     *salida de la variable S
     * @return obtener el arreglo Secuencia
     */
    public int[][] getS(){
        return S;
    }

    /**
     *salida de la variable S con la declaracion del tamaño de la matriz
     * @param q posicion del estado
     * @param s posicion del simbolo
     * @return devolver el estado de esa posicion
     */
    public int getS(int q, int s) {
        return S[q][s];
    }

    /**
     *entrada de la matriz  S
     * @param S establecer el arreglo secuencia
     */
    public void setS(int[][] S) {
        this.S = S;
    }
    
    
    
    
    /**
     * 
     * Evaluar/verificar si la cadena "w" es valida
     * @param w es la cadena ingresada por el usuario
     * @param initValue es el estado inicial
     * @return  retornar el estado final para verificar si es valida
     * 
     **/
    public int evaluate(String w, int initValue) {
        int q = initValue ;
        char s;
        int is;


        try {
            s = nextChar(w);

            while (true) {
                is = alfabeto.indexOf(s);
                
                if (is == -1) error(102);

                q = S[q][is];

                if (q == -1) error(101);

                s = nextChar(w);


            }
        } catch (IndexOutOfBoundsException e) {

        }


        return q;


    }
    
    /**
     * Metodo que pasa al siguiente caracter de la cadena
     * @param w cadena introducina por el usuario
     * @return retornar el el caracter en x posicion
     * @throws IndexOutOfBoundsException manejador de excepciones
     */
    public char nextChar(String w) throws IndexOutOfBoundsException {
        symb++;
        return w.charAt(symb);

    }
    
    /**
     * Metodo Que genera la excepcion de error
     * @param i posicion/tipo de error para decidir cual se ejecutara
     */
    public void error(int i) {
        switch (i) {
            case 101:
                System.err.println("Error");
                break;
            case 102:
                System.err.println("Error");
                break;
        }
    }
    
    /**
     * Metodo donde hace la validacion de los estados
     * @param q estado final del recorrido
     * @return retornar su el estado existe en los estados finales
     */
    public boolean validateStatus(int q) {

        System.out.println("Q final: "+q);
      
        boolean aux = false;
        for (int i = 0; i < F.size(); i++) {
            if (F.get(i) == q) {
                aux = true;
            }
        }

        return aux;

    }
    
    
}
