package br.com.developbox.desenhosabe;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculoOhmPotencia extends AppCompatActivity {
    TextView ohmPotResultTextView;
    EditText ohmPotVEditText;
    EditText ohmPotREditText;
    Button ohmPotCalculateButton;
    TextView ohmPotFormulatextView;

    double v, r;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_ohm_potencia);

        ohmPotResultTextView = (TextView) findViewById(R.id.ohmPotResultTextView);
        ohmPotFormulatextView = (TextView) findViewById(R.id.ohmPotFormulatextView);

        ohmPotVEditText = (EditText) findViewById(R.id.ohmPotVEditText);
        ohmPotREditText = (EditText) findViewById(R.id.ohmPotREditText);

        ohmPotCalculateButton = (Button) findViewById(R.id.ohmPotCalculateButton);
        ohmPotCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v = Double.parseDouble(ohmPotVEditText.getText().toString());
                r = Double.parseDouble(ohmPotREditText.getText().toString());

                result = String.format("%.3f W", calculate(v, r));

                ohmPotResultTextView.setText(result);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.calculo_resistencia_2_menu, menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.ohmLaw));
        getSupportActionBar().setSubtitle(getString(R.string.power));

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
                        ohmPotFormulatextView.getText().toString(),
                        new String[] {"V", "R"},
                        new String[] {String.format("%.2f V", v), String.format("%.2f Ω", r)},
                        result
                ));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public double calculate(double v, double r){
        return (v*v)/r;
    }
}
