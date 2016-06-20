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

public class CalculoResistencia3 extends AppCompatActivity {

    TextView resi3ResultTextView;
    TextView resi3FormulaTextView;

    EditText resi3R1EditView;
    EditText resi3R2EditView;
    EditText resi3R3EditView;

    Button resi3CalculateButton;

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_resistencia3);

        resi3ResultTextView = (TextView) findViewById(R.id.resi3ResultTextView);
        resi3FormulaTextView = (TextView) findViewById(R.id.resi3FormulatextView);

        resi3R1EditView = (EditText) findViewById(R.id.resi3R1EditText);
        resi3R2EditView = (EditText) findViewById(R.id.resi3R2EditText);
        resi3R3EditView = (EditText) findViewById(R.id.resi3R3EditText);

        resi3CalculateButton = (Button) findViewById(R.id.resi3CalculateButton);

        resi3CalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double r1 = Double.parseDouble(resi3R1EditView.getText().toString());
                double r2 = Double.parseDouble(resi3R2EditView.getText().toString());
                double r3 = Double.parseDouble(resi3R3EditView.getText().toString());

                result = String.format("%.3f Ω", calculate(r1, r2, r3));

                resi3ResultTextView.setText(result);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.calculo_resistencia_2_menu, menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.equivalentResistance));
        getSupportActionBar().setSubtitle(getString(R.string.resistors3));

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
                        resi3FormulaTextView.getText().toString(),
                        new String[]{"R1", "R2", "R3", "1/Re"},
                        new String[]{resi3R1EditView.getText().toString(), resi3R2EditView.getText().toString(), resi3R3EditView.getText().toString(), result},
                        result
                ));
        }

        return super.onOptionsItemSelected(item);
    }
    public double calculate(double r1, double r2, double r3){
        return 1/((1/r1) + (1/r2) + (1/r3));
    }
}
