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

    /**
     *
     */
    public Automata() {
    }

    /**
     *
     * @param Q
     * @param alfabeto
     * @param q0
     * @param F
     * @param S
     */
    public Automata(int Q, String alfabeto, int q0, ArrayList<Integer> F, int[][] S) {
        this.Q = Q;
        this.alfabeto = alfabeto;
        this.q0 = q0;
        this.F = F;
        this.S = S;
        
    }

    /**
     *
     * @return
     */
    public int getQ() {
        return Q;
    }

    /**
     *
     * @param Q
     */
    public void setQ(int Q) {
        this.Q = Q;
    }

    /**
     *
     * @return
     */
    public String getAlfabeto() {
        return alfabeto;
    }

    /**
     *
     * @param alfabeto
     */
    public void setAlfabeto(String alfabeto) {
        this.alfabeto = alfabeto;
    }

    /**
     *
     * @return
     */
    public int getQ0() {
        return q0;
    }

    /**
     *
     * @param q0
     */
    public void setQ0(int q0) {
        this.q0 = q0;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF() {
        return F;
    }
    
    /**
     *
     * @param i
     * @return
     */
    public int getF(int i) {
        return F.get(i);
    }

    /**
     *
     * @param F
     */
    public void setF(ArrayList<Integer> F) {
        this.F = F;
    }
    
    /**
     *
     * @return
     */
    public int[][] getS(){
        return S;
    }

    /**
     *
     * @param q
     * @param s
     * @return
     */
    public int getS(int q, int s) {
        return S[q][s];
    }

    /**
     *
     * @param S
     */
    public void setS(int[][] S) {
        this.S = S;
    }
    
    
    
    
    /**
     * 
     * Evaluar/verificar si la cadena "w" es valida
     * @param w
     * @param initValue
     * @return 
     * @params w cadena ingresada por el usuario
     * @params int initValue es el punto incial
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
     *
     * @param w
     * @return
     * @throws IndexOutOfBoundsException
     */
    public char nextChar(String w) throws IndexOutOfBoundsException {
        symb++;
        return w.charAt(symb);

    }
    
    /**
     *
     * @param i
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
     *
     * @param q
     * @return
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
