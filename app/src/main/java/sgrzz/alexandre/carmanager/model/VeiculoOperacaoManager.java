package sgrzz.alexandre.carmanager.model;

import java.util.LinkedList;

/**
 * Created by alexa_000 on 07/01/2016.
 */
public class VeiculoOperacaoManager {
    public static VeiculoOperacaoManager INSTANCIA = new VeiculoOperacaoManager();

    private LinkedList<Veiculo> listaVeiculos;
    private long veiculoId;

    private LinkedList<Operacao> listaOperacao;
    private long operacaoId;

    public void adicionarOperacao(long id, long idVeiculo, String nome, double preco, Data data, long kilometragem, TipoFrequencia frequencia, long kilometragemRepeticao, Duracao duracaoRepeticao, boolean repetida){
        listaOperacao.add ( new Operacao(id,idVeiculo,nome,preco,data,kilometragem,frequencia,kilometragemRepeticao,duracaoRepeticao,repetida));

    }


    public Operacao getOperacao(long id){
        for (Operacao operacao : listaOperacao) {
            if (operacao.getId()==id)
                return operacao;
        }

        return null;
    }

    public void editarOperacao(Operacao operacao){

        for (Operacao op : listaOperacao) {
            if (op.getId()==operacao.getId()){
                op=operacao;
            }
        }
    }

    public void apagarOperacao(long id){
        for (Operacao operacao : listaOperacao) {
            if (operacao.getId()==id)
            listaOperacao.remove(operacao);
        }

    }

    public void addVeiculo(long id, String nome, String marca, String modelo, String matricula, Data dataMatricula){
        listaVeiculos.add(new Veiculo(id,nome,marca,modelo,matricula,dataMatricula));
    }

    public void removeVeiculo(long id){
        for (Veiculo veiculo : listaVeiculos) {
            if (veiculo.getId()==id)
            listaVeiculos.remove(veiculo);
        }
    }



}
