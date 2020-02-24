/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afd.controller;

import afd.model.Automata;
import afd.utils.Utils;
import afd.view.MainView;
import afd.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author hugoluna
 */
public class MainController {

    private MainView mainView;
    private FileReader fileReader = null;
    private Utils utils;
    private ArrayList<String> data;
    private Automata automata;

    public MainController(MainView mainView) {
        this.mainView = mainView;

        this.mainView.setVisible(true);
        initVars();
        events();
    }

    public void initVars() {
        utils = new Utils();

    }

    public void events() {

        mainView.optionNewDFA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    openFile();
                } catch (NullPointerException nullP) {
                    System.err.println("Error: " + nullP.getMessage());
                }

            }
        });

        mainView.optionQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainView.btnEvaluate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                readFile();
                String value = mainView.textWord.getText();

                if (!value.isEmpty() || value.trim().length() > 0) {

                    actionEval(value);

                }
            }
        });

    }

    public void openFile() {
        utils = new Utils();
        fileReader = utils.obtenerRutaArchivo(mainView);
        readFile();
    }

    public void readFile() {

        data = utils.leerArchivo(fileReader);

        int sec[][] = utils.convertStringToSec(data.get(0), data.get(1));
        ArrayList<Integer> statusFinals = utils.getFinalStatus(data.get(3), mainView);

        int Q = sec.length;
        setDataInView(Q, data.get(0), statusFinals);
        automata = new Automata(Q, data.get(0), Integer.parseInt(data.get(2)), statusFinals, sec);

    }

    public void actionEval(String value){
        
        try{
            
            System.out.println("q0: " + automata.getQ0());
                    System.out.println("alfabeto: " + automata.getAlfabeto());
                    System.out.println("Secuencia");

                    String contentValue = "";
                    for (int i = 0; i < automata.getS().length; i++) {
                        for (int j = 0; j < automata.getS()[0].length; j++) {

                            contentValue += "Valor en q" + i + ":" + j + " = " + automata.getS(i, j) + "\n";

                        }

                    }

                    System.out.println("Estados finales:");
                    for (int i = 0; i < automata.getF().size(); i++) {
                        System.out.println(automata.getF(i));

                    }

                    int responseEv = automata.evaluate(value, automata.getQ0());
                    if (automata.validateStatus(responseEv)) {

                        mainView.textResult.setText(contentValue + "\n\nq" + responseEv + " ϵ F ∵ es aceptada");

                    } else {
                        mainView.textResult.setText(contentValue + "\n\nq" + responseEv + " ∉ F ∵ no es aceptada");

                    }
            
            
        }catch(NumberFormatException numberFormatException){
            System.err.println("Error con los numero ingresados: "+numberFormatException.getMessage());
        }
        
    }
    
    /**
     * Colocar los daros en la vista
     *
     * @param Q
     * @param alpha
     * @param F
     */
    public void setDataInView(int Q, String alpha, ArrayList<Integer> F) {

        mainView.lblAlphabet.setText("{ " + alpha + " }");

        String qS = "";
        String fS = "";

        for (int i = 0; i < Q; i++) {

            if (i < (Q - 1)) {

                qS += "q" + i + ", ";

            } else {
                qS += "q" + i + "";
            }

        }

        mainView.lblStates.setText("{ " + qS + " }");

        for (int i = 0; i < F.size(); i++) {
            if (i < (F.size() - 1)) {

                fS += "q" + F.get(i) + ", ";

            } else {
                fS += "q" + F.get(i) + "";
            }

        }

        mainView.lblFinalStates.setText("{" + fS + "}");

    }

}
