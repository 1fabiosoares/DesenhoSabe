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

public class CalculoOhmTensao extends AppCompatActivity {
    TextView ohmTenResultTextView;
    TextView ohmTenFormulatextView;

    EditText ohmTenREditText;
    EditText ohmTeniEditText;

    Button ohmTenCalculateButton;
    double r, i;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_ohm_tensao);

        ohmTenResultTextView = (TextView) findViewById(R.id.ohmTenResultTextView);
        ohmTenFormulatextView = (TextView) findViewById(R.id.ohmTenFormulatextView);

        ohmTenREditText = (EditText) findViewById(R.id.ohmTenREditText);
        ohmTeniEditText = (EditText) findViewById(R.id.ohmTeniEditText);

        ohmTenCalculateButton = (Button) findViewById(R.id.ohmTenCalculateButton);

        ohmTenCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r = Double.parseDouble(ohmTenREditText.getText().toString());
                i = Double.parseDouble(ohmTeniEditText.getText().toString());

                result = String.format("%.3f V", calculate(r, i));

                ohmTenResultTextView.setText(result);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.calculo_resistencia_2_menu, menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.ohmLaw));
        getSupportActionBar().setSubtitle(getString(R.string.tension));

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
                        ohmTenFormulatextView.getText().toString(),
                        new String[] {"R", "i"},
                        new String[] {String.format("%.2f Ω", r), String.format("%.2f A", i)},
                        result
                ));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public double calculate(double r, double i){
        return r*i;
    }
}
