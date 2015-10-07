/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsbd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emanuel
 */
public class Arquivo {

    FileReader arq;
    BufferedReader lerarq;

    public String lerArquivo(String caminho) {
        String arquivo = new String();
        String vetor[];
        String vetorTAB[] = null;
        ArrayList<String> tabelas = new ArrayList<>();
        try {
            arq = new FileReader(caminho);
            lerarq = new BufferedReader(arq);
            String linha = lerarq.readLine();

            while (linha != null) {
                arquivo += (linha);
                arquivo += " ";

                linha = lerarq.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        arquivo = arquivo.replaceAll("\\(", "");
        arquivo = arquivo.replaceAll("\\)", "");
        arquivo = arquivo.replaceAll(",", "");
        arquivo = arquivo.replaceAll("varchar", "varchar ");
        vetor = arquivo.split(";");

        for (int i = 0; i < vetor.length-1; i++) {
            String aux[];
            vetorTAB = vetor[i].split(" ");
            vetor[i].trim();
            gravarMetadados(vetorTAB);
//
//            for (int j = 0; j < vetorTAB.length; j++) {
//                System.out.println(" " + vetorTAB[j]);
//            }
            vetorTAB=null;
        }

        return arquivo;
    }

    public boolean gravarMetadados(String[] meta) {
        String table="";
        for (int i = 2; i < meta.length; i++) {
             
            table+=meta[i];
            table+=" ";
            
        }
        try {
            FileWriter fw=new FileWriter("Meta.txt",true);
            fw.write(table);
            fw.write("\n");
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        //System.out.println(table);
        return true;
    }

}
