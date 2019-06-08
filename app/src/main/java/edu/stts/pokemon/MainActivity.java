package edu.stts.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etusername,etpassword;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etusername=findViewById(R.id.etUser);
        etpassword=findViewById(R.id.etPassword);
        login=findViewById(R.id.btnLogin);
    }
    public void OnLogin(View v){
        String username=etusername.getText().toString();
        String password=etpassword.getText().toString();
        String type="login";
        DoInBackground bg=new DoInBackground(this);
        bg.execute(type,username,password);
    }
    public void ToRegister(View v){
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }
}
