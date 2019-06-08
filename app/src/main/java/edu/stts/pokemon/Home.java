package edu.stts.pokemon;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;
import net.gotev.uploadservice.UploadService;

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
import java.util.UUID;

public class Home extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText etnama,etevo;
    Spinner spinner;
    ImageView imageView;
    Button btnupload,btnchoose;
    ArrayList<Pokemon> arrayList=new ArrayList<>();
    ArrayList<Skill> allSkill=new ArrayList<>();

    TextView tv;
    ListView listView;
    ArrayAdapter<String> adapters;
    String address="https://zerorhytm.000webhostapp.com/selectPokemon.php";
    InputStream is=null;
    String line=null;
    String results=null;
    String[] data;
    private static final int STORAGE_PERMISSION_CODE=2342;
    private static final int PICK_IMAGE_REQUEST=22;
    private Uri filepath;
    private Bitmap bitmap;
    private static final String UPLOAD_URL="https://zerorhytm.000webhostapp.com/registerPokemon.php";

    String address2="https://zerorhytm.000webhostapp.com/selectSkill.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        UploadService.NAMESPACE = BuildConfig.APPLICATION_ID;
        requestStoragePermission();
        btnupload=findViewById(R.id.btnInsert);
        btnchoose=findViewById(R.id.btnchoose);
        imageView=findViewById(R.id.imageView);
        spinner=findViewById(R.id.spinner1);
        etnama=findViewById(R.id.etNama);
        etevo=findViewById(R.id.etEvolusi);
        btnchoose.setOnClickListener(this);
        btnupload.setOnClickListener(this);
        tv=findViewById(R.id.textView6);
        listView=findViewById(R.id.lv);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.element,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        getData();
        getDataSkill();

    }
    private void requestStoragePermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            return;
        }
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }
    public void OnView(View v){
        tv.setText("");
        for(Skill p:allSkill){
            tv.append(p.toString());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==STORAGE_PERMISSION_CODE){
            if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null&&data.getData()!=null){
            filepath=data.getData();
            try{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                imageView.setImageBitmap(bitmap);
            }catch(Exception e){}
        }
    }
    private String getPath(Uri uri){
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        cursor.moveToFirst();
        String document_id=cursor.getString(0);
        document_id=document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();
        cursor=getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID+"=?",new String[]{document_id},null
        );
        cursor.moveToFirst();
        String path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    private void getDataSkill(){
        try{
            String Text=spinner.getSelectedItem().toString();

            String alamat="https://zerorhytm.000webhostapp.com/selectSkill"+Text+".php";

            URL url=new URL(alamat);
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

    private void UploadImage(){
        String name=etnama.getText().toString().trim();
        String evo=etevo.getText().toString().trim();
        String Text=spinner.getSelectedItem().toString();
        String path=getPath(filepath);
        allSkill.clear();
        getDataSkill();
        try{
            String uploadid= UUID.randomUUID().toString();
            Random rnd= new Random();
            int hp=rnd.nextInt(7)+10;
            int def=rnd.nextInt(5)+5;
            int att=rnd.nextInt(5)+5;
            ArrayList<Integer> randomNumber = new ArrayList<Integer>();

            while (randomNumber.size() < 4) { // how many numbers u need - it will 6
                int a = rnd.nextInt(allSkill.size())+1; // this will give numbers between 1 and 50.

                if (!randomNumber.contains(a)) {
                    randomNumber.add(allSkill.get(a).getIdskill());
                }
            }

            new MultipartUploadRequest(this,uploadid,UPLOAD_URL)
                    .addParameter("nama",name)
                    .addParameter("element",Text)
                    .addParameter("evoname",evo)
                    .addParameter("exp","0")
                    .addParameter("maxexp","100")
                    .addParameter("lv","1")
                    .addParameter("hp",""+hp)
                    .addParameter("maxhp",""+hp)
                    .addParameter("def",""+def)
                    .addParameter("att",""+att)
                    .addFileToUpload(path,"gambar")
                    .addParameter("idskill1",""+randomNumber.get(0).intValue())
                    .addParameter("idskill2",""+randomNumber.get(1).intValue())
                    .addParameter("idskill3",""+randomNumber.get(2).intValue())
                    .addParameter("idskill4",""+randomNumber.get(3).intValue())
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();
        }catch(Exception e){

        }
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
                data[i]=jo.getString("Nama");
                arrayList.add(new Pokemon(jo.getInt("IdPokemon"),jo.getString("Nama")
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
    private void showFileChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
    }
    @Override
    public void onClick(View v) {
        if(v==btnupload){
            UploadImage();
        }
        if(v==btnchoose){
            showFileChooser();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void GoSkill(View v){
        Intent intent = new Intent(Home.this, SkillAct.class);
        startActivity(intent);
    }
    public void GoWorldMap(View v){
        Intent intent = new Intent(Home.this, WorldMap.class);
        startActivity(intent);
    }
    public void battle(View v){
        Intent intent = new Intent(Home.this, Battle.class);
        startActivity(intent);
    }
}
