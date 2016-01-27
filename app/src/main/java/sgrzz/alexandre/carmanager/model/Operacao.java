package sgrzz.alexandre.carmanager.model;

/**
 * Created by alexa_000 on 07/01/2016.
 */
public class Operacao {
    private long id;
    private long idVeiculo;
    private String nome;
    private double preco;
    private Data data;
    private long kilometragem;
    private TipoFrequencia frequencia;
    private long kilometragemRepeticao;
    private Duracao duracaoRepeticao;
    private boolean repetida;


    public Operacao(long id, long idVeiculo, String nome, double preco, Data data, long kilometragem, TipoFrequencia frequencia, long kilometragemRepeticao, Duracao duracaoRepeticao, boolean repetida) {
        this.id = id;
        this.idVeiculo = idVeiculo;
        this.nome = nome;
        this.preco = preco;
        this.data = data;
        this.kilometragem = kilometragem;
        this.frequencia = frequencia;
        this.kilometragemRepeticao = kilometragemRepeticao;
        this.duracaoRepeticao = duracaoRepeticao;
        this.repetida = repetida;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public long getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(long kilometragem) {
        this.kilometragem = kilometragem;
    }

    public TipoFrequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(TipoFrequencia frequencia) {
        this.frequencia = frequencia;
    }

    public long getKilometragemRepeticao() {
        return kilometragemRepeticao;
    }

    public void setKilometragemRepeticao(long kilometragemRepeticao) {
        this.kilometragemRepeticao = kilometragemRepeticao;
    }

    public Duracao getDuracaoRepeticao() {
        return duracaoRepeticao;
    }

    public void setDuracaoRepeticao(Duracao duracaoRepeticao) {
        this.duracaoRepeticao = duracaoRepeticao;
    }

    public boolean isRepetida() {
        return repetida;
    }

    public void setRepetida(boolean repetida) {
        this.repetida = repetida;
    }



}
