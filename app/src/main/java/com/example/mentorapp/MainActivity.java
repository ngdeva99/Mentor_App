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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public String retreived_email;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String name;
    EditText e1,e2,e3,e4,e5,e6,n,attendance,feedback,u;
    Button chatblo;
    Button a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retreived_email = Main3Activity.email;
        a = (Button) findViewById(R.id.showstuds);
        e1  = (EditText)findViewById(R.id.e1);
        e2  = (EditText)findViewById(R.id.e2);
        e3  = (EditText)findViewById(R.id.e3);
        e4  = (EditText)findViewById(R.id.e4);
        e5  = (EditText)findViewById(R.id.e5);
        e6  = (EditText)findViewById(R.id.e6);
        n= (EditText)findViewById(R.id.n);
        attendance = (EditText)findViewById(R.id.attendance);
        feedback= (EditText)findViewById(R.id.feedback);

        chatblo = (Button)findViewById(R.id.chatblo);
        u = (EditText)findViewById(R.id.u);
        a.setOnClickListener(this);
        chatblo.setOnClickListener(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.showstuds) {
            retreive();
        }

        if (v.getId() == R.id.chatblo) {
            Intent i = getPackageManager().getLaunchIntentForPackage("com.social.joanlouji.chatblo");
            startActivity(i);
        }
    }

    private void retreive() {
        // Read from the database
        int i=0;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                FirebaseDatabase.getInstance().getReference("Students").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {

                            //if (data.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".", "").substring(0, FirebaseAuth.getInstance().getCurrentUser().getEmail().indexOf("@")))) {
                            if(data.getKey()!=null){
                                if (data.child("Email").getValue().toString().equals(firebaseAuth.getCurrentUser().getEmail().toString())) {
                                    n.setText(data.child("Name").getValue().toString());
                                    //settext;
                                    DataSnapshot ds = data.child(u.getText().toString());
                                    if (ds!=null) {
                                        e1.setText(ds.child("Sub1").getValue().toString());
                                        e2.setText(ds.child("Sub2").getValue().toString());
                                        e3.setText(ds.child("Sub3").getValue().toString());
                                        e4.setText(ds.child("Sub4").getValue().toString());
                                        e5.setText(ds.child("Sub5").getValue().toString());
                                        e6.setText(ds.child("Sub6").getValue().toString());
                                        attendance.setText(ds.child("Attendance").getValue().toString());
                                        feedback.setText(ds.child("Feedback").getValue().toString());
                                    }

                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }





}
