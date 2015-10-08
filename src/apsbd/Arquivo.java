/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsbd;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    public void inicializaArquivo(String nome) {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(nome + ".txt"));

//            byte teste = 2*(byte) 0000;
            dos.writeShort(0);
            dos.writeShort(0);
            dos.writeShort(2048);

            int i = 0;
            while (i < 1021) {
                dos.writeShort(0);
                i++;
            }
            dos.close();

//        byte teste = (byte) 0000; // 10101010 em binário  
//        byte teste2 = (byte) 0000; // 10101010 em binário  
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerByte_a_Byte() {
        File f = new File("testando.txt");
        byte[] b = new byte[1024];
        int i = 0;
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(f);
//            i = fi.read(b);
            while ((i = fi.read(b)) > -1) {
                for (int j = 0; j < i; j+=2) {
                    String msgDecode  = new String(b, "UTF-8");
                    System.out.println("O byte #" + j + " é = " + b[j] + b[j+1]);
                }
            }
            fi.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
     public void ler4Bytes() {
        File f = new File("testando.txt");
        byte[] b = new byte[4];
        int i = 0;
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(f);
//            i = fi.read(b);
            
            while (fi.read(b) > -1){
                
            System.out.println("byte: " + b[1] + b[0] + b[3] + b[2]);
            }
            
//            while ((i = fi.read(b)) > -1) {
//                for (int j = 0; j < i; j+=4) {
//                    String msgDecode  = new String(b, "UTF-8");
//                    System.out.println("O byte #" + j + " é = " + b[j] + b[j+1] + b[j+2] + b[j+3]);
//                }
//            }
            fi.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

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

        for (int i = 0; i < vetor.length - 1; i++) {
            String aux[];
            vetorTAB = vetor[i].split(" ");
            vetor[i].trim();
            gravarMetadados(vetorTAB);
//
//            for (int j = 0; j < vetorTAB.length; j++) {
//                System.out.println(" " + vetorTAB[j]);
//            }
            vetorTAB = null;
        }

        return arquivo;
    }

    public boolean gravarMetadados(String[] meta) {
        String table = "";
        for (int i = 2; i < meta.length; i++) {

            table += meta[i];
            table += " ";

        }
        try {
            FileWriter fw = new FileWriter("Meta.txt", true);
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
