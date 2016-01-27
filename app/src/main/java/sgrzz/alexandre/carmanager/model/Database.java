package sgrzz.alexandre.carmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexa_000 on 12/01/2016.
 */
public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME="carManager.db";
    private static final int DB_VERSION =1;



    private static class VeiculosTable {
        public static final String TABLE_NAME="veiculos";
        public static final String CREATE_COMMAND = "CREATE TABLE "+TABLE_NAME+" (_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, "+
                "nome VARCHAR NOT NULL, modelo VARCHAR NOT NULL, marca VARCHAR NOT NULL, matricula VARCHAR NOT NULL, dataMatricula DATE NOT NULL);";
        public static final String DELETE_COMMAND ="DROP TABLE IF EXISTS "+TABLE_NAME+";" ;
        public static final String ID = "_id";
        public static final String NOME = "nome";
        public static final String MODELO = "modelo";
        public static final String MARCA = "marca";
        public static final String MATRICULA = "matricula";
        public static final String DATA_MATRICULA = "dataMatricula";

        public static final String[] ALL = {ID,NOME,MODELO,MARCA,MATRICULA,DATA_MATRICULA};

        public static ContentValues getContentValues(Veiculo v){
            ContentValues resultado = new ContentValues();

            resultado.put(NOME,v.getNome());
            resultado.put(MODELO,v.getModelo());
            resultado.put(MARCA,v.getMarca());
            resultado.put(MATRICULA,v.getMatricula());
            resultado.put(DATA_MATRICULA,v.getDataMatricula().toString());

            return resultado;
        }

        public static Veiculo getVeiculoFromCursor(Cursor c){
            Veiculo resultado=new Veiculo(
                    c.getLong(c.getColumnIndex(ID)),
                    c.getString(c.getColumnIndex(NOME)),
                    c.getString(c.getColumnIndex(MODELO)),
                    c.getString(c.getColumnIndex(MARCA)),
                    c.getString(c.getColumnIndex(MATRICULA)),
                    getData(c, c.getColumnIndex(DATA_MATRICULA)));
            return resultado;
        }

        private static Data getData(Cursor c, int columnIndex) {
            Data resultado;
            String ValorDB=c.getString(columnIndex);
            resultado=Data.parseData(ValorDB);
            return resultado;
        }

        private static List<Veiculo> getVeiculosFromCursor(Cursor c){
            LinkedList<Veiculo> resultado=new LinkedList<>();
            if (c.moveToFirst()){
                do {
                    Veiculo v=getVeiculoFromCursor(c);
                    resultado.add(v);
                } while (c.moveToNext());

            }
            return resultado;
        }

    }

    private static class OperacoesTable {
        public static final String TABLE_NAME="operacoes";
        public static final String CREATE_COMMAND="CREATE "+TABLE_NAME+" (_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, idVeiculo INTEGER," +
                "nome VARCHAR, preco FLOAT, data VARCHAR, kilometragem INTEGER," +
                " frequencia VARCHAR,kilometragemRepeticao INTEGER, duracaoRepeticao VARCHAR, repetida BOOlEAN);";

        public static final String DELETE_COMMAND ="DROP TABLE IF EXISTS "+TABLE_NAME+";" ;
        public static final String ID="id";
        public static final String IDVEICULO="idVeiculo";
        public static final String NOME="nome";
        public static final String PRECO="preco";
        public static final String DATA="data";
        public static final String KILOMETRAGEM="kilometragem";
        public static final String FREQUENCIA="frequencia";
        public static final String KILOMETRAGEMREPETICAO="kilometragemRepeticao";
        public static final String DURACAOREPETICAO="duracaoRepeticao";
        public static final String REPETIDA="repetida";
        public static final String[] ALL = {ID,IDVEICULO,NOME,PRECO,DATA,KILOMETRAGEM,FREQUENCIA,KILOMETRAGEMREPETICAO,DURACAOREPETICAO,REPETIDA};

        public static ContentValues getContentValues(Operacao o){
            ContentValues resultado = new ContentValues();
            resultado.put(IDVEICULO,o.getIdVeiculo());
            resultado.put(NOME,o.getNome());
            resultado.put(PRECO, o.getPreco());
            resultado.put(DATA,o.getData().toString());
            resultado.put(KILOMETRAGEM,o.getKilometragem());
            resultado.put(FREQUENCIA,o.getFrequencia().toString());
            resultado.put(KILOMETRAGEMREPETICAO,o.getKilometragemRepeticao());
            resultado.put(DURACAOREPETICAO,o.getDuracaoRepeticao().toString());
            resultado.put(REPETIDA,o.isRepetida());
            return resultado;
        }

        public static Operacao getOperacaoFromCursor(Cursor c){
            Operacao resultado=new Operacao(
                    c.getLong(c.getColumnIndex(ID)),
                    c.getLong(c.getColumnIndex(IDVEICULO)),
                    c.getString(c.getColumnIndex(NOME)),
                    c.getDouble(c.getColumnIndex(PRECO)),
                    getData(c,c.getColumnIndex(DATA)),
                    c.getLong(c.getColumnIndex(KILOMETRAGEM)),
                    getTipoFrequencia(c,c.getColumnIndex(FREQUENCIA)),
                    c.getLong(c.getColumnIndex(KILOMETRAGEMREPETICAO)),
                    null,
                    getRepetida(c,c.getColumnIndex(REPETIDA)));

            return resultado;
        }

        private static Data getData(Cursor c, int columnIndex) {
            Data resultado;
            String ValorDB=c.getString(columnIndex);
            resultado=Data.parseData(ValorDB);
            return resultado;
        }
        private static TipoFrequencia getTipoFrequencia(Cursor c, int columnIndex) {
            TipoFrequencia resultado;
            resultado = TipoFrequencia.valueOf(c.getString(columnIndex));
            return resultado;
        }

        private static boolean getRepetida(Cursor c, int columnIndex){
            Boolean resultado;
            resultado = Boolean.getBoolean(c.getString(columnIndex));

            return resultado;
        }

        private static Duracao getDuracao(Cursor c,int columnIndex){
            Duracao resultado = null;

            return resultado;
        }

        private static List<Operacao> getOperacoesFromCursor(Cursor c){
            LinkedList<Operacao> resultado=new LinkedList<>();
            if (c.moveToFirst()){
                do {
                    Operacao o=getOperacaoFromCursor(c);
                    resultado.add(o);
                } while (c.moveToNext());

            }
            return resultado;
        }

    }

    public Database(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VeiculosTable.CREATE_COMMAND);
        db.execSQL(OperacoesTable.CREATE_COMMAND);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(VeiculosTable.DELETE_COMMAND);
        db.execSQL(OperacoesTable.DELETE_COMMAND);
        onCreate(db);
    }


    //funções de veiculo

    public long insertVeiculo (Veiculo v){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = VeiculosTable.getContentValues(v);
        long id = db.insert(VeiculosTable.TABLE_NAME,null,cv);
        v.setId(id);
        db.close();
        return id;
    }

    public int updateVeiculo(Veiculo v){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = VeiculosTable.getContentValues(v);
        int afetadas= db.update(VeiculosTable.TABLE_NAME, cv, VeiculosTable.ID + "=?", new String[]{Long.toString(v.getId())});


        db.close();
        return afetadas;
    }

    public int deleteVeiculo(long id){
        SQLiteDatabase db = getWritableDatabase();
        int afetadas = db.delete(VeiculosTable.TABLE_NAME, VeiculosTable.ID + "=?", new String[]{Long.toString(id)});
        db.close();
        return afetadas;
    }

    public List<Veiculo> getAllVeiculos(){
        SQLiteDatabase db = getWritableDatabase();
        //SELECT * FROM veiculos
        Cursor c = db.query(VeiculosTable.TABLE_NAME, VeiculosTable.ALL, null, null, null, null, null);
        List<Veiculo> resultado = VeiculosTable.getVeiculosFromCursor(c);
        c.close();
        db.close();
        return resultado;
    }

    public Veiculo getVeiculoById(long id){
        SQLiteDatabase db = getReadableDatabase();
        //SELECT * FROM veiculos WHERE _id=«id»
        Cursor c = db.query(VeiculosTable.TABLE_NAME,VeiculosTable.ALL,VeiculosTable.ID+"=?",new String[]{Long.toString(id)},null,null,null);
        Veiculo resultado = c.moveToFirst()? VeiculosTable.getVeiculoFromCursor(c) : null;
        c.close();
        db.close();
        return resultado;

    }

    //funções de operação

    public long insertOperacao (Operacao o){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = OperacoesTable.getContentValues(o);
        long id = db.insert(OperacoesTable.TABLE_NAME,null,cv);
        o.setId(id);
        db.close();
        return id;
    }

    public int updateOperacao(Operacao o){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = OperacoesTable.getContentValues(o);
        int afetadas= db.update(OperacoesTable.TABLE_NAME, cv, OperacoesTable.ID + "=?", new String[]{Long.toString(o.getId())});
        db.close();
        return afetadas;
    }

    public int deleteOperacao(long id){
        SQLiteDatabase db = getWritableDatabase();
        int afetadas = db.delete(OperacoesTable.TABLE_NAME, OperacoesTable.ID + "=?", new String[]{Long.toString(id)});
        db.close();
        return afetadas;
    }

    public List<Operacao> getAllOperacoes(){
        SQLiteDatabase db = getWritableDatabase();
        //SELECT * FROM Operacoes
        Cursor c = db.query(OperacoesTable.TABLE_NAME, OperacoesTable.ALL, null, null, null, null, null);
        List<Operacao> resultado = OperacoesTable.getOperacoesFromCursor(c);
        c.close();
        db.close();
        return resultado;
    }

    public Operacao getOperacaoById(long id){
        SQLiteDatabase db = getReadableDatabase();
        //SELECT * FROM Operacoes WHERE _id=«id»
        Cursor c = db.query(OperacoesTable.TABLE_NAME,OperacoesTable.ALL,OperacoesTable.ID+"=?",new String[]{Long.toString(id)},null,null,null);
        Operacao resultado = c.moveToFirst()? OperacoesTable.getOperacaoFromCursor(c) : null;
        c.close();
        db.close();
        return resultado;

    }
}
