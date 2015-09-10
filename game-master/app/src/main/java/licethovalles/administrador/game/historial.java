package licethovalles.administrador.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class historial extends AppCompatActivity {

    private Button button;
    private TextView ed,ed2;
    String puntajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial2);
        button=(Button) findViewById(R.id.button);
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historial.this.startActivity(new Intent(historial.this, MainActivity.class));

            }
        });



        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int game = sharedPref.getInt("PUNTAJES", 0);
        int puntaje=sharedPref.getInt("MAYORPTJ", 0);
        puntajes=puntajes+sharedPref.getString("PUNTAJES2", Integer.toString(puntaje));




        ed = (TextView) findViewById(R.id.textView2);
        ed.setText("No juegos Ganados "+game);

        ed2=(TextView) findViewById(R.id.textView4);
        ed2.setText("Mayor Puntaje "+puntajes);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_historial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
