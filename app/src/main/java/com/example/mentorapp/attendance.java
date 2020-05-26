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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class attendance extends AppCompatActivity implements View.OnClickListener {

    EditText whichut, mregno, attendance;
    Button update;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);


        whichut = (EditText) findViewById(R.id.whichut);
        mregno = (EditText) findViewById(R.id.mregno);
        attendance = (EditText) findViewById(R.id.attendance);
        update = (Button)findViewById(R.id.update);


        update.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.update) {
            updateAttendance();

        }
    }

    private void updateAttendance() {
        String ut = whichut.getText().toString().trim();
        String rno = mregno.getText().toString().trim();
        String attendance1 = attendance.getText().toString().trim();


        if (TextUtils.isEmpty(ut)) {
            //email is empty
            Toast.makeText(this, "Please enter ut", Toast.LENGTH_SHORT).show();
            //stopping from executing further
            return;
        }
        if (TextUtils.isEmpty(rno)) {
            //password is empty
            Toast.makeText(this, "Please enter rno", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(attendance1)) {
            //password is empty
            Toast.makeText(this, "Please enter attendance", Toast.LENGTH_SHORT).show();
            return;
        }


        if (ut.length() != 3) {
            Toast.makeText(this, "Enter valid UT", Toast.LENGTH_SHORT).show();
        }

        if (ut.length() == 3) {
            if ((ut.charAt(0) == 'U') || (ut.charAt(0) == 'u') && (ut.charAt(1) == 'U') || (ut.charAt(1) == 't') && (Character.isDigit(ut.charAt(2)))) {

                progressDialog.setMessage("Updating Attendance");
                progressDialog.show();


                Map<String, String> map = new HashMap<>();
                map.put("Attendance", attendance1);


                FirebaseDatabase.getInstance().getReference("Students").child(rno).child(ut)
                        .child("Attendance").setValue(attendance1).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(attendance.this, "success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(attendance.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            } else {
                Toast.makeText(this, "Invalid UT", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
