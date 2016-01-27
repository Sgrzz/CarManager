package sgrzz.alexandre.carmanager.model;

/**
 * Created by alexa_000 on 05/01/2016.
 */
public class Duracao {
    private int quantidade;
    private TipoDuracao tipo;

    public Duracao(int quantidade, TipoDuracao tipo){
        this.quantidade=quantidade;
        this.tipo=tipo;
    }

    public TipoDuracao getTipo()
    {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

}
