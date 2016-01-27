package sgrzz.alexandre.carmanager.model;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alexa_000 on 14/01/2016.
 */
public class WebService {

    public static final WebService API = new WebService();

    private WebService(){

    }

    public String send(String metodo, String url, String header, String conteudo){
        HttpURLConnection connection=null;
        try {
            URL urlCompleto = new URL("http://www.marcoferreira.org/carmanager/api/v1/"+url);

            connection = (HttpURLConnection) urlCompleto.openConnection();
            connection.setRequestMethod(metodo);
            if (header!=null){
                connection.setRequestProperty("token",header);
            }
            if (conteudo!=null){
                connection.setDoInput(true);
                PrintWriter pw = new PrintWriter(connection.getOutputStream());
                pw.print(conteudo);
                pw.close();
            }
            if (connection.getResponseCode()==200){ //#deu #lel
                String tudo = lerStream(connection.getInputStream());
                return tudo;
            }else{  //#falha
                int codigo=connection.getResponseCode();
                String tudo = lerStream(connection.getErrorStream());
                throw new SendException(codigo,tudo);
            }




        } catch (IOException e) {
            throw new SendException(-1,e.getMessage());
        }
        finally {
            if (connection!=null){
                connection.disconnect();
            }
        }

    }

    public void createUser(final String username,final String password,final ResultListener listener){
        AsyncTask<Void, Void,Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                JSONObject user=new JSONObject();
                try {
                    user.put("username",username);
                    user.put("password",password);
                } catch (JSONException e) {

                }
                try {


                    String resultado = send("POST", "USER", null, user.toString());



                    return true;
                }
                catch (SendException e){
                    Log.e("WEBSERVICE",e.getMensagem());
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                listener.resultado(aBoolean);


            }
        };






    }

    public void createCar(final long id, final String nomeC, final String marca,final String modelo,final String matricula,final String dataMatricula, final String timeStamp,final ResultListener listener ){
        AsyncTask<Void, Void,Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                JSONObject car=new JSONObject();
                try {
                    car.put("id",id);
                    car.put("nome",nomeC);
                    car.put("marca",marca);
                    car.put("modelo",modelo);
                    car.put("matricula",matricula);
                    car.put("dataMatricula",dataMatricula);
                    car.put("timestamp",timeStamp);
                } catch (JSONException e) {

                }
                try {


                    String resultado = send("POST", "CAR", null, car.toString());



                    return true;
                }
                catch (SendException e){
                    Log.e("WEBSERVICE",e.getMensagem());
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                listener.resultado(aBoolean);


            }
        };

    }


    public void updateCar(final long id, final String nomeC, final String marca,final String modelo,final String matricula,final String dataMatricula, final String timeStamp,final ResultListener listener  ){
        AsyncTask<Void, Void,Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                JSONObject actualizarVeiculo=new JSONObject();
                try {
                    actualizarVeiculo.put("id",id);
                    actualizarVeiculo.put("nome",nomeC);
                    actualizarVeiculo.put("marca",marca);
                    actualizarVeiculo.put("modelo",modelo);
                    actualizarVeiculo.put("matricula",matricula);
                    actualizarVeiculo.put("dataMatricula",dataMatricula);
                    actualizarVeiculo.put("timestamp",timeStamp);
                } catch (JSONException e) {

                }
                try {


                    String resultado = send("PUT", "CAR/"+id, null, actualizarVeiculo.toString());



                    return true;
                }
                catch (SendException e){
                    Log.e("WEBSERVICE",e.getMensagem());
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                listener.resultado(aBoolean);


            }
        };

    }

    public void removeCar(final long id, final String timeStamp,final ResultListener listener){
        AsyncTask<Void, Void,Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                JSONObject carDeleted=new JSONObject();
                try {
                    carDeleted.put("id",id);
                    carDeleted.put("timestamp",timeStamp);
                } catch (JSONException e) {

                }
                try {


                    String resultado = send("DELETE", "CAR/"+ id, null, carDeleted.toString());



                    return true;
                }
                catch (SendException e){
                    Log.e("WEBSERVICE",e.getMensagem());
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                listener.resultado(aBoolean);


            }
        };
    }



    public void addOperacao(final long id,final long idVeiculo ,final String nome, final long kilometragem, final String data, final int tipoFrequencia, final long kilometragemProgramada, final int duracaoProgramada, final int tipoDuracao, final double valorPago, final long iconId, final int realizada, final String timeStamp,final ResultListener listener){

        AsyncTask<Void, Void,Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                JSONObject operacao=new JSONObject();
                try {
                    operacao.put("id",id);
                    operacao.put("idVeiculo",idVeiculo);
                    operacao.put("nome",nome);
                    operacao.put("kilometragem",kilometragem);
                    operacao.put("data",data);
                    operacao.put("tipoFrequencia",tipoFrequencia);
                    operacao.put("kilometragemProgramada",kilometragemProgramada);
                    operacao.put("duracaoProgramada",duracaoProgramada);
                    operacao.put("tipoDuracao",tipoDuracao);
                    operacao.put("valorPago",valorPago);
                    operacao.put("iconId",iconId);
                    operacao.put("realizada",realizada);
                    operacao.put("timestamp",timeStamp);

                } catch (JSONException e) {

                }
                try {


                    String resultado = send("POST", "OPERATION/"+idVeiculo, null, operacao.toString());



                    return true;
                }
                catch (SendException e){
                    Log.e("WEBSERVICE",e.getMensagem());
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                listener.resultado(aBoolean);


            }
        };

    }


    public void removeOperation(final long idOperacao, final long idVeiculo, final String timeStamp,final ResultListener listener){
        AsyncTask<Void, Void,Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                JSONObject carDeleted=new JSONObject();
                try {
                    carDeleted.put("idOperacao",idOperacao);
                    carDeleted.put("idVeiculo",idVeiculo);
                    carDeleted.put("timestamp",timeStamp);
                } catch (JSONException e) {

                }
                try {


                    String resultado = send("DELETE", "OPERATION/"+idVeiculo+"/"+idOperacao, null, carDeleted.toString());



                    return true;
                }
                catch (SendException e){
                    Log.e("WEBSERVICE",e.getMensagem());
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                listener.resultado(aBoolean);


            }
        };
    }


    public void updateOperation(final long id,final long idVeiculo ,final String nome, final long kilometragem, final String data, final int tipoFrequencia, final long kilometragemProgramada, final int duracaoProgramada, final int tipoDuracao, final double valorPago, final long iconId, final int realizada, final String timeStamp,final ResultListener listener){

        AsyncTask<Void, Void,Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                JSONObject actualizarOperacao=new JSONObject();
                try {
                    actualizarOperacao.put("id",id);
                    actualizarOperacao.put("idVeiculo",idVeiculo);
                    actualizarOperacao.put("nome",nome);
                    actualizarOperacao.put("kilometragem",kilometragem);
                    actualizarOperacao.put("data",data);
                    actualizarOperacao.put("tipoFrequencia",tipoFrequencia);
                    actualizarOperacao.put("kilometragemProgramada",kilometragemProgramada);
                    actualizarOperacao.put("duracaoProgramada",duracaoProgramada);
                    actualizarOperacao.put("tipoDuracao",tipoDuracao);
                    actualizarOperacao.put("valorPago",valorPago);
                    actualizarOperacao.put("iconId",iconId);
                    actualizarOperacao.put("realizada",realizada);
                    actualizarOperacao.put("timestamp",timeStamp);

                } catch (JSONException e) {

                }
                try {


                    String resultado = send("PUT", "OPERATION/"+idVeiculo+"/"+id, null, actualizarOperacao.toString());



                    return true;
                }
                catch (SendException e){
                    Log.e("WEBSERVICE",e.getMensagem());
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                listener.resultado(aBoolean);


            }
        };

    }


    @NonNull
    private String lerStream(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String s = "";
        String tudo = "";
        while ((s = br.readLine()) != null) {
            tudo = tudo + "\n";

        }
        br.close();
        return tudo;
    }
}
