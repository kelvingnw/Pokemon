package edu.stts.pokemon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import java.util.Random;

public class Battle extends AppCompatActivity {

    String address="https://zerorhytm.000webhostapp.com/selectPokemon.php";
    String address2="https://zerorhytm.000webhostapp.com/selectSkill.php";

    InputStream is=null;
    String line=null;
    String results=null;
    String element="";
    String[] data;
    Boolean battlemenu=false,itemmenu=false,mainmenu=true,partymenu=false,myturn=true,battleover=false;

    ArrayList<Integer> randomNumber = new ArrayList<Integer>();
    ArrayList<Pokemon> partyPokemon=new ArrayList<>();
    ArrayList<Pokemon> enemyPokemon=new ArrayList<>();
    ArrayList<Skill> allSkill=new ArrayList<>();
    int enemySkill1=0,enemySkill2=0,enemySkill3=0,enemySkill4=0;


    Random r = new Random();
    TextView mymaxhp,myname,mylv,enemylv,enemyname,enemymaxhp;
    ProgressBar enemybar,mybar;
    ImageView enemyimg,myimg;
    Button dialog,skill1,skill2,skill3,skill4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            element = extras.getString("Element");
            if(!element.equalsIgnoreCase("Trainer")){
                address="https://zerorhytm.000webhostapp.com/selectPokemon"+element+".php";
                address2="https://zerorhytm.000webhostapp.com/selectSkill"+element+".php";
            }
        }
        getData();
        getDataSkill();
        int enemyid=r.nextInt(enemyPokemon.size());
        while (randomNumber.size() < 4) { // how many numbers u need - it will 6
            int a = r.nextInt(allSkill.size()); // this will give numbers between 1 and 50.

            if (!randomNumber.contains(a)) {
                randomNumber.add(a);
            }
        }
        mymaxhp=findViewById(R.id.myMaxHp);
        mybar=findViewById(R.id.myHealthBar);
        myimg=findViewById(R.id.myImage);
        mylv=findViewById(R.id.myLv);
        myname=findViewById(R.id.myName);
        enemybar=findViewById(R.id.enemyHealthBar);
        enemymaxhp=findViewById(R.id.enemyMaxHp);
        enemyname=findViewById(R.id.enemyName);
        enemylv=findViewById(R.id.enemyLv);
        enemyimg=findViewById(R.id.enemyImage);
        dialog=findViewById(R.id.btnDialog);
        skill1=findViewById(R.id.btnSkillOne);
        skill2=findViewById(R.id.btnSkillTwo);
        skill3=findViewById(R.id.btnSkillTri);
        skill4=findViewById(R.id.btnSkillFour);


        dialog.setText("A Wild "+enemyPokemon.get(enemyid).getName()+" appeared!");
        enemylv.setText("Lv "+enemyPokemon.get(enemyid).getLv());
        enemyname.setText(""+enemyPokemon.get(enemyid).getName());
        enemymaxhp.setText("Hp: "+enemyPokemon.get(enemyid).getHp()+"/"+enemyPokemon.get(enemyid).getMaxhp());
        enemybar.setMax(enemyPokemon.get(enemyid).getMaxhp());
        enemybar.setProgress(enemyPokemon.get(enemyid).getMaxhp());
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.pikachus);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 200, 200, true);
        enemyimg.setImageBitmap(bMapScaled);
        myimg.setImageBitmap(bMapScaled);

        enemySkill1=randomNumber.get(0).intValue();
        enemySkill2=randomNumber.get(1).intValue();
        enemySkill3=randomNumber.get(2).intValue();
        enemySkill4=randomNumber.get(3).intValue();

        skill1.setText(allSkill.get(enemySkill1).getSkillname()+"\n"+allSkill.get(enemySkill1).getPp());
        skill2.setText(allSkill.get(enemySkill2).getSkillname()+"\n"+allSkill.get(enemySkill2).getPp());
        skill3.setText(allSkill.get(enemySkill3).getSkillname()+"\n"+allSkill.get(enemySkill3).getPp());
        skill4.setText(allSkill.get(enemySkill4).getSkillname()+"\n"+allSkill.get(enemySkill4).getPp());



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
            data=new String[js.length()];
            for(int i=0; i<js.length(); i++){
                jo=js.getJSONObject(i);
                enemyPokemon.add(new Pokemon(jo.getInt("IdPokemon"),jo.getString("Nama")
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

    private void getDataSkill(){
        try{
            URL url=new URL(address2);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            line=null;
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
            data=new String[js.length()];
            for(int i=0; i<js.length(); i++){
                jo=js.getJSONObject(i);
                allSkill.add(new Skill(jo.getInt("IdSkill"),jo.getString("SkillName"),jo.getString("Desk"),jo.getString("Element"),jo.getInt("PP"),jo.getInt("Multiplier")));
            }
        }catch(Exception e){

        }

    }
}
