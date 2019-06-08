package edu.stts.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorldMap extends AppCompatActivity {

    Button btnWater,btnFire,btnGrass,btnThunder,btnIce,btnTrainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_map);
        btnFire=findViewById(R.id.btnVolcano);
        btnGrass=findViewById(R.id.btnForest);
        btnThunder=findViewById(R.id.btnThunder);
        btnIce=findViewById(R.id.btnSnow);
        btnWater=findViewById(R.id.btnSea);
        btnTrainer=findViewById(R.id.btnTrainer);

    }

    public void onClick(View v) {
        Intent intent= new Intent(WorldMap.this, Battle.class);
        String element="";
        if(v==btnFire){
            element="Fire";
        }
        else if(v==btnGrass){
            element="Grass";
        }
        else if(v==btnIce){
            element="Ice";
        }
        else if(v==btnThunder){
            element="Thunder";
        }
        else if(v==btnTrainer){
            element="Trainer";
        }
        else if(v==btnWater){
            element="Water";
        }
        intent.putExtra("Element", element);

        startActivity(intent);

    }
}
