package br.com.developbox.desenhosabe;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculoEfeitoJoule extends AppCompatActivity{
    TextView efeitoJouleResultTextView;
    EditText efeitoJouleREditText;
    EditText efeitoJouleiEditText;
    Button efeitoJouleCalculateButton;
    TextView efeitoJouleFormulatextView;

    double r, i;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_efeito_joule);

        efeitoJouleResultTextView = (TextView) findViewById(R.id.efeitoJouleResultTextView);
        efeitoJouleFormulatextView = (TextView) findViewById(R.id.efeitoJouleFormulatextView);

        efeitoJouleREditText = (EditText) findViewById(R.id.efeitoJouleREditText);
        efeitoJouleiEditText = (EditText) findViewById(R.id.efeitoJouleiEditText);

        efeitoJouleCalculateButton = (Button) findViewById(R.id.efeitoJouleCalculateButton);

        efeitoJouleCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r = Double.parseDouble(efeitoJouleREditText.getText().toString());
                i = Double.parseDouble(efeitoJouleiEditText.getText().toString());

                result = String.format("%.3f W", calculate(r, i));

                efeitoJouleResultTextView.setText(result);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.calculo_resistencia_2_menu, menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.jouleEffect));
        getSupportActionBar().setSubtitle(getString(R.string.jouleEffectDescription));

        MenuItemCompat.setShowAsAction(menu.findItem(R.id.shareButton), MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.shareButton:
                startActivity(ListaDeCalculos.shareResult(
                        efeitoJouleFormulatextView.getText().toString(),
                        new String[] {"R", "i"},
                        new String[] {String.format("%.2f Ω", r), String.format("%.2f A", i)},
                        result
                ));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public double calculate(double r, double i){
        return (r*(i*i));
    }
}
