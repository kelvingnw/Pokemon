package edu.stts.pokemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText etuser,etpass,etconfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etuser=findViewById(R.id.etusername);
        etpass=findViewById(R.id.etPassword);
        etconfirm=findViewById(R.id.etConfirm);
    }
    public void OnReg(View v){
        String username=etuser.getText().toString();
        String password=etpass.getText().toString();
        String confirm=etconfirm.getText().toString();
        String type="register";
        DoInBackground bg=new DoInBackground(this);
        bg.execute(type,username,password,confirm);
    }
}
