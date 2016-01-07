package sgrzz.alexandre.carmanager.model;

import java.util.Calendar;

/**
 * Created by alexa_000 on 05/01/2016.
 */
public class Data {
    private Calendar calendar;

    public Data(){
        calendar = Calendar.getInstance();
    }

    public Data(int ano, int mes, int dia){
        this();
        calendar.set(ano, mes - 1, dia);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

    }

    public Data(int ano,int mes){
        this(ano, mes, 1);
    }
    public int getAno(){
        return calendar.get(Calendar.YEAR);
    }
    public int getMes(){
        return calendar.get(Calendar.MONTH)+1;
    }
    public int getDia(){
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    private String comZeros(int numero, int tam){
        String resultado=Integer.toString(numero);
        while (resultado.length()<tam)
            resultado="0"+resultado;
        return resultado;
    }

    @Override
    public String toString() {
        return comZeros(getAno(),4)+"-"+comZeros(getMes(), 2)+"-"+comZeros(getDia(),2);
    }

    public static Data parseData(String s){
        if (s==null)
            return null;
        String separador="";
        if (s.contains("-"))
            separador="-";
        else
            separador="/";
        String []componentes=s.split(separador);
        int dia=1;
        int mes=0;
        int ano=0;
        if (componentes.length<2||componentes.length>3){
            throw new RuntimeException("Invalid date format ("+s+"). \n"+"Valid formats:yyyy/mm, yyyy/mm/dd, yyyy-mm, yyyy-mm-dd");
        }

            ano=Integer.parseInt(componentes[0]);
            mes=Integer.parseInt(componentes[1]);


       if (componentes.length==3){

           dia = Integer.parseInt(componentes[2]);
        }

        return new Data(ano,mes,dia);

    }
    public Data add(Duracao d){
        Data res= new Data(getAno(),getMes(),getDia());
        switch (d.getTipo()){
            case DIAS:res.calendar.add(Calendar.DAY_OF_MONTH,d.getQuantidade());
                break;
            case MESES:res.calendar.add(Calendar.MONTH,d.getQuantidade());
                break;
            case ANOS:res.calendar.add(Calendar.YEAR,d.getQuantidade());
                break;
        }
        return  res;
    }
}
