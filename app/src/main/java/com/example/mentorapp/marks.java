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


public class marks extends AppCompatActivity implements View.OnClickListener{


        EditText whichut,subj1,subj2,subj3,subj4,subj5,subj6,mregno;
    Button update;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        whichut = (EditText)findViewById(R.id.whichut);
        mregno = (EditText)findViewById(R.id.mregno);
        subj1 = (EditText)findViewById(R.id.subj1);
        subj2 = (EditText)findViewById(R.id.subj2);
        subj3 = (EditText)findViewById(R.id.subj3);
        subj4 = (EditText)findViewById(R.id.subj4);
        subj5 = (EditText)findViewById(R.id.subj5);
        subj6 = (EditText)findViewById(R.id.subj6);
        update = (Button)findViewById(R.id.update);

        update.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.update){
            updatemarks();
        }
    }


    private void updatemarks(){
        String ut=whichut.getText().toString().trim();
        String rno=mregno.getText().toString().trim();
        String s1=subj1.getText().toString().trim();
        String s2 = subj2.getText().toString().trim();
        String s3 = subj3.getText().toString().trim();
        String s4 = subj4.getText().toString().trim();
        String s5 = subj5.getText().toString().trim();
        String s6 = subj6.getText().toString().trim();



        if(TextUtils.isEmpty(ut)){
            //email is empty
            Toast.makeText(this,"Please enter ut",Toast.LENGTH_SHORT).show();
            //stopping from executing further
            return;
        }
        if(TextUtils.isEmpty(rno)){
            //password is empty
            Toast.makeText(this,"Please enter rno",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(s1)){
            //password is empty
            Toast.makeText(this,"Please enter s1",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(s2)){
            //password is empty
            Toast.makeText(this,"Please enter s2",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(s3)){
            //password is empty
            Toast.makeText(this,"Please enter s3",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(s4)){
            //password is empty
            Toast.makeText(this,"Please enter s4",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(s5)){
            //password is empty
            Toast.makeText(this,"Please enter s5",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(s6)){
            //password is empty
            Toast.makeText(this,"Please enter s6",Toast.LENGTH_SHORT).show();
            return;
        }



        if(TextUtils.isEmpty(s1)){
            //password is empty
            Toast.makeText(this,"Please enter password again",Toast.LENGTH_SHORT).show();
            return;
        }



        if(ut.length()!=3){
            Toast.makeText(this,"Enter valid UT",Toast.LENGTH_SHORT).show();
        }


        progressDialog.setMessage("Updating Marks");
        progressDialog.show();





                            Map<String,String> map = new HashMap<>();
                            map.put("Sub1",s1);
                            map.put("Sub2",s2);
                            map.put("Sub3",s3);
                            map.put("Sub4",s4);
                            map.put("Sub5",s5);
                            map.put("Sub6",s6);


                            //map.put("Floor",fl);
//                            user User=new user(
//                                    name1,
//                                    email1,desig
//                            );

                            FirebaseDatabase.getInstance().getReference("Students").child(rno).child(ut)
                                    .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(marks.this, "success", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(marks.this, "failed", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });
                        }

}

        //if validations are ok
        //we first show a progress

        // FirebaseDatabase.getInstance().getReference("Floors").child(fl).setValue(map1);
