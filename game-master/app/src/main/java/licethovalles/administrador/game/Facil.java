package licethovalles.administrador.game;



import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.hardware.Sensor;
        import android.hardware.SensorManager;
        import android.preference.PreferenceManager;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

        import android.os.Handler;


public class Facil extends ActionBarActivity  {

    private String TAG = "elJuego";
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    private TextView ed;
    ArrayList botones;
    RelativeLayout X;
    int contador,total=0,intento=0,NoJuego,MayorPtj=0;
    String puntajes;
    Bundle temp;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private shakedetector mShakeDetector;
    //Handler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facil);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        b6=(Button)findViewById(R.id.button6);
        b7=(Button)findViewById(R.id.button7);
        b8=(Button)findViewById(R.id.button8);
        b9=(Button)findViewById(R.id.button9);
        b10=(Button)findViewById(R.id.button10);

        botones=new ArrayList<Button>();
        puntajes="";
        X= (RelativeLayout)findViewById(R.id.game);
        ed = (TextView)findViewById(R.id.textView);
        ed.setText("Intentos : "+intento+"\r\ntotal : "+total);
        contador=6;



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setText("1");
                botones.add(b1);
                comparar2();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setText("2");
                botones.add(b2);
                comparar2();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b3.setText("1");
                botones.add(b3);
                comparar2();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b4.setText("3");
                botones.add(b4);
                comparar2();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b5.setText("3");
                botones.add(b5);
                comparar2();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b6.setText("2");
                botones.add(b6);
                comparar2();
            }
        });


        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Facil.this.startActivity(new Intent(Facil.this, historial.class));

            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Facil.this.startActivity(new Intent(Facil.this, MainActivity.class));

            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Facil.this.startActivity(new Intent(Facil.this, Dificil.class));

            }
        });

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        NoJuego = sharedPref.getInt("PUNTAJES", 0);
        MayorPtj= sharedPref.getInt("MAYORPTJ",0);
        puntajes=sharedPref.getString("PUNTAJES2","");

        Log.d(TAG, "Iniciamos con " + NoJuego);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new shakedetector();
        mShakeDetector.setOnShakeListener(new shakedetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                Toast.makeText(getApplicationContext(),"Nueva Partida",Toast.LENGTH_SHORT).show();
                reiniciar();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void comparar2()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                comparar();

            }
        }, 500);

    }


    public void comparar(){


        if(botones.size()==2){
            //    new Timer().schedule(new TimerTask() {
            //      @Override
            //    public void run() {

            Button a = (Button) botones.get(0);
            Button b = (Button) botones.get(1);
            botones.clear();
            intento = intento + 1;
            if (a.getText().equals(b.getText())) {


                total = total + 1;

                a.setEnabled(false);
                b.setEnabled(false);
                contador -= 2;
                if (contador == 0) {
                    NoJuego++;
                    MayorPtj=6*100/intento;
                    Toast.makeText(getApplicationContext(), "Has Ganado y tu Puntuacion es "+MayorPtj, Toast.LENGTH_LONG).show();
                    juegos();
                }
            } else {

                a.setText("");
                b.setText("");


            }
            ed.setText("Intentos : " + intento + "\r\nNo Parejas: " + total);
            //}
            //}, 2000);


        }



    }

    public void juegos(){
        // Create object of SharedPreferences.
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        //now get Editor
        SharedPreferences.Editor editor = sharedPref.edit();
        //put your value
        editor.putInt("PUNTAJES",NoJuego );
        editor.putInt("MAYORPTJ",MayorPtj);
        editor.putString("PUNTAJES2", Integer.toString(MayorPtj));
        //commits your edits
        editor.commit();

        Log.d(TAG, "Almacenamos  " + NoJuego);
    }

    public void reiniciar()
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void reiniciar2(View view)
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }


}



