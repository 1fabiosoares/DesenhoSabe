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

public class CalculoOhmResistencia extends AppCompatActivity {

    TextView ohmResResultTextView;
    EditText ohmResVEditText;
    EditText ohmResiEditText;
    Button ohmResCalculateButton;
    TextView ohmResFormulatextView;

    double v, i;

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_ohm_resistencia);

        ohmResResultTextView = (TextView) findViewById(R.id.ohmResResultTextView);
        ohmResFormulatextView = (TextView) findViewById(R.id.ohmResFormulatextView);

        ohmResVEditText = (EditText) findViewById(R.id.ohmResVEditText);
        ohmResiEditText = (EditText) findViewById(R.id.ohmResiEditText);

        ohmResCalculateButton = (Button) findViewById(R.id.ohmResCalculateButton);

        ohmResCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v = Double.parseDouble(ohmResVEditText.getText().toString());
                i = Double.parseDouble(ohmResiEditText.getText().toString());

                result = String.format("%.3f Ω", calculate(v, i));

                ohmResResultTextView.setText(result);
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
                        ohmResFormulatextView.getText().toString(),
                        new String[] {"V", "i"},
                        new String[] {String.format("%.2f V", v), String.format("%.2f A", i)},
                        result
                ));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public double calculate(double v, double i){
        return v/i;
    }
}
