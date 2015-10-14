/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsbd;

import Utils.Convert;
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
 * @author Emanuel & Leonardo
 * @version 1.0
 */
public class Arquivo {

    private FileReader fileRead;
    private BufferedReader buffArquivo;
    private Tabela table;
    private Campo campo;

    public void initializeFile(String nome) {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(nome + ".txt"));

            short a = 0;
            short b = 8;
            dos.writeByte(a);
            dos.writeByte(a);
            dos.writeByte(a);
            dos.writeByte(a);
            dos.writeByte(a);
            dos.writeByte(b);

            int i = 0;
            while (i < 2042) {
                dos.writeByte(0);
                i++;
            }
            dos.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readByte(FileInputStream fi) {
//        File f = new File(nomeArq);
        byte[] b = new byte[4];
        int i = 0;
//        FileInputStream fi = null;
        try {
//            fi = new FileInputStream(f);
//            fi.read(b);
            while ((i = fi.read(b)) > -1) {
//            System.out.println("i=" + i);
                for (int j = 0; j < 4; j += 1) {
                    System.out.print(b[j]);
//                System.out.println("" + b[0] + b[1] + b[2] + b[3] + b[4] + b[5] + b[6] + b[7]);
//                    String msgDecode  = new String(b, "UTF-8");
                }
//                i++;
            }
            fi.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Cabecalho getCabecalho(String nomeArq) {
        Cabecalho c = new Cabecalho();
        Convert convert = new Convert();
        File f = new File(nomeArq + ".txt");
        byte[] b = new byte[4];
        int i = 0;
        try {

            FileInputStream fi = new FileInputStream(f);
            while (i < 3) {
                fi.read(b);
                String aux = "";
                for (int j = 0; j < 4; j += 1) {
                    aux += b[j];
//                    System.out.print(b[j]);
                }
                if (i == 0) {
                    int aux2 = convert.convert2Int(b);
                    c.setQtdeRegistros(aux2);

                } else if (i == 1) {
                    c.setDeslocamentoStByte(b);
                } else {
                    int aux2 = convert.convert2Int(b);
                    c.setQtdeRegistrosExcluidos(aux2);
                }
//                System.out.println("");
                i++;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return c;
    }

    public String readQueryCreate(String nomeArq) {
        String arquivo = "";
        String vetor[];
        String vetorTAB[] = null;
        ArrayList<String> tabelas = new ArrayList<>();
        try {
            fileRead = new FileReader(nomeArq);
            buffArquivo = new BufferedReader(fileRead);
            String linha = buffArquivo.readLine();

            while (linha != null) {
                arquivo += (linha);
                arquivo += " ";

                linha = buffArquivo.readLine();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
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
            persistMetadata(vetorTAB);
//
//            for (int j = 0; j < vetorTAB.length; j++) {
//                System.out.println(" " + vetorTAB[j]);
//            }
            vetorTAB = null;
        }

        return arquivo;
    }

    public boolean persistMetadata(String[] meta) {
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

    public void readQueryInsert(String nomeArq) {
        String arquivo = "";
        String vetor[];
        String vetorTAB[] = null;
        ArrayList<String> tabelas = new ArrayList<>();
        try {
            fileRead = new FileReader(nomeArq);
            buffArquivo = new BufferedReader(fileRead);
            String linha = buffArquivo.readLine();

            while (linha != null) {
                arquivo += (linha);
                arquivo += " ";

                System.out.println(linha);
                linha = buffArquivo.readLine();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public Tabela getTabela(String nome) {
        table = new Tabela();
        campo = new Campo();
        ArrayList<Campo> campos = new ArrayList<>();
        try {
            fileRead = new FileReader("Meta.txt");
            buffArquivo = new BufferedReader(fileRead);
            String linha = null;

            linha = buffArquivo.readLine();

            while (linha != null) {
                String splitLinha[] = linha.split(" ");
                if (splitLinha[0].equals(nome)) {
                    table.setNome(nome);
                    for (int i = 1; i < splitLinha.length; i++) {
                        campo = new Campo();
                        if (splitLinha[i].equals("integer")) {
                            campo.setNome(splitLinha[(i - 1)]);
                            campo.setTamanho(4);
                            table.setCampo(campo);
                            campos.add(campo);
                        } else if (splitLinha[i].equals("varchar")) {
                            campo.setNome(splitLinha[(i - 1)]);
                            int tam = Integer.valueOf(splitLinha[i + 1]);
                            campo.setTamanho(tam);
                            campo.setTipo("varchar");
                            table.setCampo(campo);
                        } else if (splitLinha[i].equals("boolean")) {
                            campo.setNome(splitLinha[(i - 1)]);
                            campo.setTamanho(1);
                            campo.setTipo("boolean");
                            table.setCampo(campo);
                        } else if (splitLinha[i].equals("char")) {
                            campo.setNome(splitLinha[i - 1]);
                            campo.setTamanho(1);
                            campo.setTipo("char");
                            table.setCampo(campo);
                        }
                    }
                }
                linha = buffArquivo.readLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return table;
    }

}
