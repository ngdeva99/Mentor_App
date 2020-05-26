package com.example.mentorapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class mentor_main extends AppCompatActivity implements View.OnClickListener{


    private ProgressDialog progressDialog;
    private TextView greeting;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String a;
    Button marks,list,attendance,feedback,chatblo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_main);

        greeting = (TextView)findViewById(R.id.greeting);
        marks = (Button)findViewById(R.id.marks);
        list = (Button)findViewById(R.id.list);
        attendance = (Button)findViewById(R.id.attendance);
        feedback = (Button)findViewById(R.id.feedback);
        chatblo = (Button)findViewById(R.id.chatblo);
        chatblo.setOnClickListener(this);
        a = Mentor.names;
        greeting.setText(a);
        marks.setOnClickListener(this);
        list.setOnClickListener(this);
        attendance.setOnClickListener(this);
        feedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.marks){
            startActivity(new Intent(this,marks.class));
        }
        if(v.getId()==R.id.list){
            startActivity(new Intent(this,students.class));
        }

        if(v.getId()==R.id.attendance){
            startActivity(new Intent(this,attendance.class));
        }

        if(v.getId()==R.id.feedback){
            startActivity(new Intent(this,feedback.class));
        }

        if(v.getId()==R.id.chatblo){
            Intent i = getPackageManager().getLaunchIntentForPackage("com.social.joanlouji.chatblo");
            startActivity(i);
        }




    }
}
