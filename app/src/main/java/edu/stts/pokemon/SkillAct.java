package edu.stts.pokemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SkillAct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText etskill,etdesc,etpp,etmulti;
    Spinner spinner;
    Button buttoninsert;
    ArrayList<Skill> arrayList=new ArrayList<>();

    String address="https://zerorhytm.000webhostapp.com/selectSkill.php";
    InputStream is=null;
    String line=null;
    String results=null;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);
        etskill=findViewById(R.id.etSkillName);
        etdesc=findViewById(R.id.etDesc);
        etpp=findViewById(R.id.etpp);
        etmulti=findViewById(R.id.etmulti);
        buttoninsert=findViewById(R.id.btnInsertSkill);
        spinner=findViewById(R.id.typespinner);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.element,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }
    public void onInsert(View v){

        String skillname=etskill.getText().toString();
        String desc=etdesc.getText().toString();
        String Element=spinner.getSelectedItem().toString();
        int pp=Integer.parseInt(etpp.getText().toString());
        int multi=Integer.parseInt(etmulti.getText().toString());
        String pp2=Integer.toString(pp);
        String multi2=Integer.toString(multi);
        String type="insertskill";
        DoInBackground bg=new DoInBackground(this);
        bg.execute(type,skillname,desc,Element,pp2,multi2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
                data[i]=jo.getString("SkillName");
                arrayList.add(new Skill(jo.getInt("IdSkill"),jo.getString("SkillName"),jo.getString("Desk"),jo.getString("Element"),jo.getInt("PP"),jo.getInt("Multiplier")));
            }
        }catch(Exception e){

        }

    }
}
