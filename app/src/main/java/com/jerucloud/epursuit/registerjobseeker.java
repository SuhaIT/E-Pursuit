package com.jerucloud.epursuit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;



public class registerjobseeker extends AppCompatActivity {
    Button SULoginBtn;
EditText firstname,editTextLastName,editTextGender,editTextDate,editTextPhone,editTextEmail,editTexPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_registerjobseeker);
        Button NextBtn=findViewById(R.id.NextBtn);
        SULoginBtn=findViewById(R.id.SULoginBtn);
        SULoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(registerjobseeker.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        firstname= findViewById(R.id.editTextFirstName);
        editTextLastName= findViewById(R.id.editTextLastName);
        editTextGender= findViewById(R.id.editTextGender);
        editTextDate= findViewById(R.id.editTextDate);
        editTextPhone= findViewById(R.id.editTextPhone);
        editTextEmail= findViewById(R.id.editTextEmail);
        editTexPassword= findViewById(R.id.editTexPassword);


        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(registerjobseeker.this,registerjobseek2.class);
                i.putExtra("firstname", firstname.getText().toString().trim());
                i.putExtra("lastname",editTextLastName.getText().toString().trim());
                i.putExtra("gender", editTextGender.getText().toString().trim());
                i.putExtra("Date", editTextDate.getText().toString().trim());
                i.putExtra("Phone", editTextPhone.getText().toString().trim());
                i.putExtra("Password", editTexPassword.getText().toString());
                i.putExtra("email", editTextEmail.getText().toString());
                startActivity(i);
                finish();


            }
        });
    }
}