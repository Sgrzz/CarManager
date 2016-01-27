package sgrzz.alexandre.carmanager.model;

/**
 * Created by alexa_000 on 14/01/2016.
 */
public class SendException extends RuntimeException {
    private int codigo;
    private String mensagem;
    public SendException(int codigo, String mensagem) {
        super("send Exception with code "+codigo);
        this.codigo=codigo;
        this.mensagem=mensagem;
    }



    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
