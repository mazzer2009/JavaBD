package apsbd;

/**
 *
 * @author Leonardo P. Baiser <lpbaiser@gmail.com>
 * @version 1.0
 * @since 
 */
public class Cabecalho {
    
    private int qtdeRegistros;
    private int qtdeRegistrosExcluidos;
    private byte[] deslocamentoStByte;

    public int getQtdeRegistros() {
        return qtdeRegistros;
    }

    public void setQtdeRegistros(int qtdeRegistros) {
        this.qtdeRegistros = qtdeRegistros;
    }

    public int getQtdeRegistrosExcluidos() {
        return qtdeRegistrosExcluidos;
    }

    public void setQtdeRegistrosExcluidos(int qtdeRegistrosExcluidos) {
        this.qtdeRegistrosExcluidos = qtdeRegistrosExcluidos;
    }

    public byte[] getDeslocamentoStByte() {
        return deslocamentoStByte;
    }

    public void setDeslocamentoStByte(byte[] deslocamentoStByte) {
        this.deslocamentoStByte = deslocamentoStByte;
    }

    
    

}
