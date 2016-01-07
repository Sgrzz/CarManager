package sgrzz.alexandre.carmanager.model;

/**
 * Created by alexa_000 on 05/01/2016.
 */
public class Veiculo {
    private long id;
    private String nome;
    private String marca;
    private String modelo;
    private String matricula;
    private Data dataMatricula;

    public Veiculo(long id, String nome, String marca, String modelo, String matricula, Data dataMatricula) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.dataMatricula = dataMatricula;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public Data getDataMatricula() {
        return dataMatricula;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setDataMatricula(Data dataMatricula) {
        this.dataMatricula = dataMatricula;
    }
}
