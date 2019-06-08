package edu.stts.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Starter extends AppCompatActivity {

    String address="https://zerorhytm.000webhostapp.com/selectPokemon.php";
    InputStream is=null;
    String line=null;
    String results=null;
    Button str1,str2,str3,dialog,yes,no;
    TextView tvstr1,tvstr2,tvstr3;
    Boolean picker=false,begin=false;

    ArrayList<Pokemon> allPokemon=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        getData();
        str1=findViewById(R.id.Starter1);
        str2=findViewById(R.id.Starter2);
        str3=findViewById(R.id.Starter3);
        dialog=findViewById(R.id.dialogStarter);
        yes=findViewById(R.id.btnYesStarter);
        no=findViewById(R.id.btnNoStarter);
        tvstr1=findViewById(R.id.tvStarter1);
        tvstr2=findViewById(R.id.tvStarter2);
        tvstr3=findViewById(R.id.tvStarter3);

        tvstr1.setText(allPokemon.get(0).getName());
        tvstr2.setText(allPokemon.get(1).getName());
        tvstr3.setText(allPokemon.get(2).getName());
        dialog.setText("Choose your Starter Pokemon! You can choose any from these three pokemon to start your journey!");

    }

    public void onClick(View v) {
        if(begin){
            Intent intent = new Intent(Starter.this, SkillAct.class);
            startActivity(intent);
        }
        if(!picker) {
            if (v == str1) {
                dialogChanger(allPokemon.get(0).getName(),"powerful",allPokemon.get(0).getElement());
            }
            if (v == str2) {
                dialogChanger(allPokemon.get(1).getName(),"well-balanced",allPokemon.get(1).getElement());
            }
            if (v == str2) {
                dialogChanger(allPokemon.get(2).getName(),"sturdy",allPokemon.get(2).getElement());
            }
            picker=true;
        }
        else{
            if (v == yes) {
                dialog.setText("Congratulations! Now your adventure will begin!\n Touch here to begin...");
                begin=true;
            }
            else if (v == no) {
                picker=false;
                dialog.setText("Choose your Starter Pokemon! You can choose any from these three pokemon to start your journey!");
                yes.setVisibility(View.INVISIBLE);
                no.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void dialogChanger(String name,String prefix,String element){
        dialog.setText(name+" is a "+prefix+" "+element+" type Pokemon!\nDo you want it to be your Starter Pokemon?");
        yes.setVisibility(View.VISIBLE);
        no.setVisibility(View.VISIBLE);
    }

    private void getData(){
        try{
            URL url=new URL(address);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            is.close();
            results=sb.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        //Parse Json data
        try{
            JSONArray js=new JSONArray(results);
            JSONObject jo=null;
            for(int i=0; i<js.length(); i++){
                jo=js.getJSONObject(i);
                allPokemon.add(new Pokemon(jo.getInt("IdPokemon"),jo.getString("Nama")
                        ,jo.getString("Element"),jo.getString("EvoName")
                        ,jo.getInt("Exp"),jo.getInt("MaxExp")
                        ,jo.getInt("Lv"),jo.getInt("Hp")
                        ,jo.getInt("MaxHp"),jo.getInt("Def")
                        ,jo.getInt("Att"),jo.getString("Gambar")
                        ,jo.getInt("IdSkill1"),jo.getInt("IdSkill2")
                        ,jo.getInt("IdSkill3"),jo.getInt("IdSkill4")));
            }
        }catch(Exception e){

        }

    }
}
