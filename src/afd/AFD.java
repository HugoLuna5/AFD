/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afd;

import afd.controller.MainController;
import afd.view.MainView;
import afd.view.MainView;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mdlaf.MaterialLookAndFeel;

/**
 *
 * @author hugoluna
 */
public class AFD {
    
    /**
     *
     */
    public void initMaterialDesign() {

        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     */
    public AFD() {
        initMaterialDesign();
        new MainController(new MainView());
    }

    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new AFD();
    }

    
}
