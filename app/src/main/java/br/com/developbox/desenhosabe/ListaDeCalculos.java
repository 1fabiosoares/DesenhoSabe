package br.com.developbox.desenhosabe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaDeCalculos extends AppCompatActivity {
    ListView listaDeCalculosListView;
    List<Map<String, String>> listaDeCalculosData;

    String calculo[];
    String descricao[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_calculos);
        calculo = new String[]{
                getString(R.string.equivalentResistance),
                getString(R.string.equivalentResistance),
                getString(R.string.ohmLaw),
                getString(R.string.ohmLaw),
                getString(R.string.ohmLaw),
                getString(R.string.ohmLaw),
                getString(R.string.jouleEffect)
        };
        descricao = new String[]{
                getString(R.string.resistors2),
                getString(R.string.resistors3),
                getString(R.string.tension),
                getString(R.string.current),
                getString(R.string.resistance),
                getString(R.string.power),
                getString(R.string.jouleEffectDescription)
        };

        listaDeCalculosListView = (ListView) findViewById(R.id.listaDeCalculosListView);
        listaDeCalculosData = new ArrayList<Map<String, String>>();
        for(int i = 0; i < calculo.length; i++){
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("Linha 1", calculo[i]);
            datum.put("Linha 2", descricao[i]);
            listaDeCalculosData.add(datum);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, listaDeCalculosData,
                android.R.layout.simple_list_item_2,
                new String[] {"Linha 1", "Linha 2"},
                new int[] {android.R.id.text1, android.R.id.text2 });
        listaDeCalculosListView.setAdapter(adapter);

        listaDeCalculosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = null;
                switch(i){
                    case 0:
                        it = new Intent(getBaseContext(), CalculoResistencia2.class);
                        break;
                    case 1:
                        it = new Intent(getBaseContext(), CalculoResistencia3.class);
                        break;
                    case 2:
                        it = new Intent(getBaseContext(), CalculoOhmTensao.class);
                        break;
                    case 3:
                        it = new Intent(getBaseContext(), CalculoOhmCorrente.class);
                        break;
                    case 4:
                        // @TODO Calculo Lei de Ohm Resistencia
                        break;
                    case 5:
                        // @TODO Potencia geral 1
                        break;
                    case 6:
                        // @TODO Calculo Joule
                        break;
                }
                if(it != null){
                    startActivity(it);
                }else{

                    Toast.makeText(ListaDeCalculos.this, calculo[i]+" "+descricao[i]+" vai ser implantado no futudo. ;)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static Intent shareResult(String formula,String[] variables, String[] values, String result){
        String text = formula+"\n";
        for (int i = 0; i < variables.length; i++){
            try{
                text += variables[i]+"="+values[i]+"\n";
            }catch(ArrayIndexOutOfBoundsException e){
                Log.e("SHARING", e.getMessage());
            }
        }
        text += "\n"+result;
        Intent it = new Intent(Intent.ACTION_SEND);
        it.putExtra(Intent.EXTRA_TEXT, text);
        it.setType("text/plain");

        return it;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getSupportActionBar().setSubtitle(getString(R.string.app_submane));
        getSupportActionBar().setShowHideAnimationEnabled(true);

        return super.onCreateOptionsMenu(menu);
    }
}
