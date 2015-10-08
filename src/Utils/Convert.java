/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

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
         byte[] data=i.getBytes();
         return data;
        
    }
    
}
