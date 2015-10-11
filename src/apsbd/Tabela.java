/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsbd;

import java.util.ArrayList;

/**
 *
 * @author emanuel
 */
public class Tabela {
    private String nome;
    private ArrayList<Campo> campos;
    
    public Tabela(){
        campos = new ArrayList<>();
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Campo> getCampo() {
        return campos;
    }

    public void setCampo(ArrayList<Campo> campo) {
        this.campos = campo;
    }
    
    public void setCampo(Campo campo) {
        this.campos.add(campo);
    }
    
    
    
}
