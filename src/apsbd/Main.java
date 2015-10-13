/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsbd;

import Utils.Convert;
import java.util.ArrayList;

/**
 *
 * @author emanuel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Arquivo arq=new Arquivo();
       // System.out.println(arq.lerArquivo("teste.sql"));
//       arq.lerArquivo("teste.sql");
       arq.initializeFile("testando");
//       arq.lerByte_a_Byte();
//       arq.writeint(2048,true);
       
       //teste metodo buscaTabela
//       Tabela t = arq.buscaTabela("Cliente");
//        System.out.println("nome" + t.getNome());
//        ArrayList<Campo> campos = t.getCampo();
//        for (Campo campo : campos) {
//            System.out.println(campo.getNome()+" " + campo.getTipo()+" " + campo.getTamanho());
//        }
       
//       Teste dos métodos convert
//       Convert c = new Convert();
//       byte[] b = c.convert2Byte(2045);//ERRADO
//        for (int i = 0; i < b.length; i++) {
//            System.out.println(""+b[i]);
//        }

       
       Cabecalho c = arq.getCabecalho("testando");
       Convert co = new Convert();
        System.out.println("Cabe: " + c.getQtdeRegistros() + " " + c.getQtdeRegistrosExcluidos() + " " + co.convert2String(c.getDeslocamentoStByte()));
        for (int i = 0; i < 4; i++) {
            System.out.println("Bytes: " + c.getDeslocamentoStByte()[i]);
        }
        
       
    }
    
}
