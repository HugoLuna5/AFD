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

                try{
                    openFile();
                }catch(NullPointerException nullP){
                    System.err.println("Error: "+nullP.getMessage());
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
                    
                    System.out.println("q0: "+automata.getQ0());
                    System.out.println("alfabeto: "+automata.getAlfabeto());
                    System.out.println("Secuencia");
                    
                    
                    String contentValue = "";
                    for (int i = 0; i < automata.getS().length; i++) {
                        for (int j = 0; j < automata.getS()[0].length; j++) {
                            
                            contentValue += "Valor en q"+i+":"+j+" = "+automata.getS(i, j)+"\n";
                            
                        }
                        
                    }
                    
                    System.out.println("Estados finales");
                    for (int i = 0; i < automata.getF().length; i++) {
                        System.out.println(automata.getF(i));
                        
                    }

                    if (validateStatus(automata.evaluate(value))) {
                        
                        mainView.textResult.setText(contentValue+"\n\nCadena aceptada");
                        
                    } else {
                        mainView.textResult.setText(contentValue+"\n\nCadena rechazada");
                    }

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
        int statusFinals[] = utils.getFinalStatus(data.get(3));

        int Q = sec.length;
        setDataInView(Q, data.get(0), statusFinals);
        automata = new Automata(Q, data.get(0), Integer.parseInt(data.get(2)), statusFinals, sec);

    }

    public boolean validateStatus(int q) {

        int F[] = automata.getF();
        boolean aux = false;
        for (int i = 0; i < F.length; i++) {
            if (F[i] == q) {
                aux = true;
            }
        }

        return aux;

    }
    
    
    
    public void setDataInView(int Q, String alpha, int F[]){
       
        mainView.lblAlphabet.setText("{ "+alpha+" }");
        
        
        String qS = "";
        String fS = "";
        
        for (int i = 0; i < Q; i++) {
            
            if (i < (Q-1)) {
                
                qS += "q"+i+", ";
                
            }else{
                qS += "q"+i+"";
            }
            
        }
        
        mainView.lblStates.setText("{ "+qS+" }");
        
        
        for (int i = 0; i < F.length; i++) {
            if (i < (F.length-1)) {
                
                fS += "q"+F[i]+", ";
                
            }else{
                fS += "q"+F[i]+"";
            }
            
        }
        
        mainView.lblFinalStates.setText("{"+fS+"}");
        
        
    }

}
