/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsbd;

/**
 *
 * @author emanuel
 */
public class APSBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Arquivo arq=new Arquivo();
       // System.out.println(arq.lerArquivo("teste.sql"));
       arq.lerArquivo("teste.sql");
    }
    
}
