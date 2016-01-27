package sgrzz.alexandre.carmanager.model;

import android.content.Context;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexa_000 on 07/01/2016.
 */
public class VeiculoOperacaoManager {
    public static VeiculoOperacaoManager INSTANCIA = new VeiculoOperacaoManager();

    private Database database;

    private VeiculoOperacaoManager(){
    }

    public void setContext(Context ctx){
        database = new Database(ctx);
    }

    public void adicionarOperacao(long id, long idVeiculo, String nome, double preco, Data data, long kilometragem, TipoFrequencia frequencia, long kilometragemRepeticao, Duracao duracaoRepeticao, boolean repetida){
        database.insertOperacao( new Operacao(id,idVeiculo,nome,preco,data,kilometragem,frequencia,kilometragemRepeticao,duracaoRepeticao,repetida));

    }


    public Operacao getOperacao(long id){
        for (Operacao operacao : database.getAllOperacoes()) {
            if (operacao.getId()==id)
                return operacao;
        }

        return null;
    }

    public void editarOperacao(Operacao operacao){

        for (Operacao op : database.getAllOperacoes()) {
            if (op.getId()==operacao.getId()){
                database.updateOperacao(operacao);
            }
        }
    }

    public void apagarOperacao(long id){
        for (Operacao operacao : database.getAllOperacoes()) {
            if (operacao.getId()==id)
            database.deleteOperacao(id);
        }

    }

    public void inserirVeiculo(long id, String nome, String marca, String modelo, String matricula, Data dataMatricula){
        database.insertVeiculo(new Veiculo(id,nome,marca,modelo,matricula,dataMatricula));
    }

    public Veiculo getVeiculo(long id){
        for (Veiculo veiculo : database.getAllVeiculos()) {
            if (veiculo.getId()==id)
                return veiculo;
        }

        return null;
    }

    public void apagarVeiculo(long id){
        for (Veiculo veiculo : database.getAllVeiculos()) {
            if (veiculo.getId()==id)
            database.deleteVeiculo(id);
        }
    }

    public void editarVeiculo(Veiculo veiculo){

        for (Veiculo v : database.getAllVeiculos()) {
            if (v.getId()==veiculo.getId()){
                database.updateVeiculo(veiculo);
            }
        }
    }

    public List getAllOperacoes(){
        return database.getAllOperacoes();
    }

    public List<Veiculo> getAllVeiculos(){
        return Collections.unmodifiableList(database.getAllVeiculos());

    }


}
