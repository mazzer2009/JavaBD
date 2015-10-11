/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emanuel
 */
public class Convert {

    public byte[] convert2Byte(int i) {
        byte[] data = new byte[4];
        data[0] = (byte) (i & 0xFF);
        data[1] = (byte) ((i >> 8) & 0xFF);
        data[2] = (byte) ((i >> 16) & 0xFF);
        data[3] = (byte) ((i >> 24) & 0xFF);

        return data;

    }

    public byte[] convert2Byte(char i) {
        byte[] data = new byte[1];
        data[0] = (byte) i;
        return data;
    }

    public byte[] convert2Byte(boolean i) {
        byte[] data = new byte[1];
        if (i) {
            data[0] = (byte) 1;
        } else {
            data[0] = (byte) 0;

        }
        return data;

    }

    public byte[] convert2Byte(String i) {
        byte[] data = i.getBytes();
        return data;
    }

    public int convert2Int(byte[] bytes) {
        int a1 = (int) bytes[3];
        int a2 = ((int) bytes[2]) << 4;
        int a3 = ((int) bytes[1]) << 8;
        int a4 = ((int) bytes[0]) << 12;

        int numero = a1 + a2 + a4 + a4;

        return numero;
    }

    public String convert2String(byte[] bytes) {
        String dado = null;  
        try {
            dado = new String (bytes, "ISO-8859-1");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return dado;
    }

    public boolean convert2Boolean(byte[] bytes) {
        int a1 = (int) bytes[3];
        int a2 = ((int) bytes[2]) << 4;
        int a = a1 + a2;
        if (a == 1) {
            return true;
        } else {
            return false;
        }
    }

}
