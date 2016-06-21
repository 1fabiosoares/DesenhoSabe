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

public class CalculoOhmCorrente extends AppCompatActivity {

    TextView ohmCurResultTextView;
    EditText ohmCurVEditText;
    EditText ohmCurREditText;
    Button ohmCurCalculateButton;
    TextView ohmCurFormulatextView;

    double v, r;
    String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_ohm_corrente);

        ohmCurResultTextView = (TextView) findViewById(R.id.ohmCurResultTextView);
        ohmCurFormulatextView = (TextView) findViewById(R.id.ohmCurFormulatextView);

        ohmCurVEditText = (EditText) findViewById(R.id.ohmCurVEditText);
        ohmCurREditText = (EditText) findViewById(R.id.ohmCurREditText);

        ohmCurCalculateButton = (Button) findViewById(R.id.ohmCurCalculateButton);

        ohmCurCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v = Double.parseDouble(ohmCurVEditText.getText().toString());
                r = Double.parseDouble(ohmCurREditText.getText().toString());

                result = String.format("%.3f A", calculate(v, r));

                ohmCurResultTextView.setText(result);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.calculo_resistencia_2_menu, menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.ohmLaw));
        getSupportActionBar().setSubtitle(getString(R.string.current));

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
                        ohmCurFormulatextView.getText().toString(),
                        new String[] {"V", "R"},
                        new String[] {String.format("%.2f A", v), String.format("%.2f Ω", r)},
                        result
                ));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public double calculate(double v, double r){
        return v/r;
    }
}
