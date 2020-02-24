/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afd.utils;

import afd.controller.MainController;
import afd.view.MainView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author hugoluna
 */
public class Utils {

    private FileReader fileReader = null;
    private ArrayList<String> data;
    private int secuence[][];

    /**
     *
     */
    public Utils() {
        data = new ArrayList<>();
    }

    /**
     *
     * @param main
     * @return
     */
    public FileReader obtenerRutaArchivo(MainView main) {

        try {
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
            file.setFileFilter(filter);
            file.showOpenDialog(main);
            /**
             * abrimos el archivo seleccionado
             */
            File ruta = file.getSelectedFile();
            if (ruta != null) {
                fileReader = new FileReader(ruta);

            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.err.println("Archivo no encontrado");
        }
        return fileReader;
    }

    /**
     *
     * @param fr
     * @return
     */
    public ArrayList<String> leerArchivo(FileReader fr) {
        BufferedReader br = new BufferedReader(fr);
        String datos = null;
        try {
            datos = br.readLine();
            while (datos != null) {
                data.add(datos);
                datos = br.readLine();

            }
            fr.close();
            br.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.err.println("Ha ocurrido un error");
        }

        return data;

    }

    /**
     *
     * @param alpha
     * @param sec
     * @return
     */
    public int[][] convertStringToSec(String alpha, String sec) {
        String resp[] = {};

        resp = sec.split(",");
        try {
            int Q = resp.length;
            int tamAlpha = alpha.length();

            secuence = new int[Q][tamAlpha];

            for (int i = 0; i < Q; i++) {
                for (int j = 0; j < tamAlpha; j++) {

                    String respSec[] = {};
                    respSec = resp[i].split(":");
                    secuence[i][j] = Integer.parseInt(respSec[j]);

                }

            }
        } catch (NullPointerException nullPointerException) {
            System.err.println("Error: " + nullPointerException.getMessage());
        } catch (NumberFormatException numberFormatException) {
            System.err.println("Error con el dato ingresado: " + numberFormatException.getMessage());

        }

        return secuence;

    }

    /**
     *
     * @param finalString
     * @param mainView
     * @return
     */
    public ArrayList<Integer> getFinalStatus(String finalString, MainView mainView) {
        ArrayList<Integer> response = new ArrayList<>();
        String dat[] = {};

        try {
            dat = finalString.split(",");

            for (int i = 0; i < dat.length; i++) {
                String string = dat[i];
                response.add(Integer.parseInt(dat[i]));

            }

        } catch (NullPointerException nullPointerException) {
            System.err.println("Error: " + nullPointerException.getMessage());

            JOptionPane.showMessageDialog(mainView, "Alerta", "Sucedio un error, llego un valor nulo", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(mainView, "Alerta", "Sucedio un error, los datos no son validos", JOptionPane.ERROR_MESSAGE);

            System.err.println("Error con el dato ingresado: " + numberFormatException.getMessage());

        }

        return response;

    }

}
