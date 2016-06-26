package br.com.developbox.desenhosabe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Info extends AppCompatActivity {

    Button seeMoreButton;
    Button bugReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        seeMoreButton = (Button) findViewById(R.id.seeMoreButton);
        bugReportButton = (Button) findViewById(R.id.bugReportButton);

        seeMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri githubUrl = Uri.parse("https://github.com/1fabiosoares/DesenhoSabe");
                Intent it = new Intent(Intent.ACTION_VIEW, githubUrl);

                startActivity(it);
            }
        });

        bugReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeEmail(new String[] {"fabioars@live.com"}, getString(R.string.bug_repport)+":"+getString(R.string.app_name)+" "+getString(R.string.app_version), getString(R.string.bug_text));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(R.string.info);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return  super.onOptionsItemSelected(item);
    }
    public void composeEmail(String[] addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
