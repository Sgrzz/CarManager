package sgrzz.alexandre.carmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import sgrzz.alexandre.carmanager.model.Database;
import sgrzz.alexandre.carmanager.model.ResultListener;
import sgrzz.alexandre.carmanager.model.Veiculo;
import sgrzz.alexandre.carmanager.model.VeiculoOperacaoManager;
import sgrzz.alexandre.carmanager.model.WebService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VeiculoOperacaoManager.INSTANCIA.setContext(this);
        ListView lista = (ListView) findViewById(R.id.listView);



        ArrayAdapter<Veiculo> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,VeiculoOperacaoManager.INSTANCIA.getAllVeiculos());

        lista.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.action_sync){
            WebService.API.createUser("me", "me", new ResultListener() {
                @Override
                public void resultado(boolean res) {
                    Toast.makeText(MainActivity.this, "Estaria a sincronizar", Toast.LENGTH_SHORT).show();
                }
            });




        }

        return super.onOptionsItemSelected(item);
    }
}
