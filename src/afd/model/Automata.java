/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afd.model;

/**
 *
 * @author hugoluna
 */
public class Automata {
    
    private int Q;
    private String alfabeto;
    private int q0;
    private int F[];
    private int S[][];
    private int symb = -1;


    public Automata() {
    }

    public Automata(int Q, String alfabeto, int q0, int[] F, int[][] S) {
        this.Q = Q;
        this.alfabeto = alfabeto;
        this.q0 = q0;
        this.F = F;
        this.S = S;
        
    }

    public int getQ() {
        return Q;
    }

    public void setQ(int Q) {
        this.Q = Q;
    }

    public String getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(String alfabeto) {
        this.alfabeto = alfabeto;
    }

    public int getQ0() {
        return q0;
    }

    public void setQ0(int q0) {
        this.q0 = q0;
    }

    public int[] getF() {
        return F;
    }
    
    public int getF(int i) {
        return F[i];
    }

    public void setF(int[] F) {
        this.F = F;
    }
    
    public int[][] getS(){
        return S;
    }

    public int getS(int q, int s) {
        return S[q][s];
    }

    public void setS(int[][] S) {
        this.S = S;
    }
    
    
    
    
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
    
    
    
    
    public char nextChar(String w) throws IndexOutOfBoundsException {
        symb++;
        return w.charAt(symb);

    }
    
    
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
    
    
    
    
}
