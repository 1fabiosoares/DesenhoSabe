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

public class CalculoResistencia2 extends AppCompatActivity {

    TextView resi2ResultTextView;
    TextView resi2FormulatextView;

    EditText resi2R1EditText;
    EditText resi2R2EditText;

    Button resi2CalculateButton;

    double r1 = 0, r2 = 0;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_resistencia2);

        resi2ResultTextView = (TextView) findViewById(R.id.resi2ResultTextView);
        resi2FormulatextView = (TextView) findViewById(R.id.resi2FormulatextView);
        resi2R1EditText = (EditText) findViewById(R.id.resi2R1EditText);
        resi2R2EditText = (EditText) findViewById(R.id.resi2R2EditText);
        resi2CalculateButton = (Button) findViewById(R.id.resi2CalculateButton);

        resi2CalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r1 = Double.parseDouble(resi2R1EditText.getText().toString());
                r2 = Double.parseDouble(resi2R2EditText.getText().toString());

                result = String.format("%.3f Ω", calculate(r1, r2));

                resi2ResultTextView.setText(result);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.calculo_resistencia_2_menu, menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.equivalentResistance));
        getSupportActionBar().setSubtitle(getString(R.string.resistors2));

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
                        resi2FormulatextView.getText().toString(),
                        new String[]{"R1", "R2"},
                        new String[]{String.format("%.2f Ω", r1), String.format("%.2f Ω", r2)},
                        result)
                );
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public double calculate(double r1, double r2){
        return (r1*r2)/(r1+r2);
    }

}
