package com.example.dattamber.insttutorials;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static com.example.dattamber.insttutorials.R.id.saddress;
import static com.example.dattamber.insttutorials.R.id.sname;
import static com.example.dattamber.insttutorials.R.id.sphone;

public class studentsignup extends AppCompatActivity {
    Spinner s1,s2;
    Button ssub;
    EditText sn,spn,sarea,sadd,semail,spd,scpd;
    String ssn,ssarea,ssadd,ssemail,ss1,ss2,spwd,spcd;
    Long si,sspn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,count;
    FirebaseAuth firebaseAuth;
    Long sid;
    ProgressDialog pd;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentsignup);
        getSupportActionBar().hide();
        s1=(Spinner)findViewById(R.id.sedu);
        s2=(Spinner)findViewById(R.id.scourse);
        final String[] l1 ={"SELECT"};
        final String[] l2 ={"SELECT","CSE","ECE","CIVIL","MECH","EEE","OTHERS"};
        final String[] l3 ={"SELECT","CSE","ECE","CIVIL","MECH","EEE","OTHERS"};
        final String[] l4 ={"SELECT","MPC","BIPC","MEC","OTHERS"};
        final String[] l5 ={"SELECT","SSC","CBSE","ICSE"};
        pd=new ProgressDialog(this);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<String> adapter;
                if(s1.getSelectedItem().toString().equals("Engineering")) {
                    adapter = new ArrayAdapter<String>(studentsignup.this, android.R.layout.simple_spinner_item, l2);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s2.setAdapter(adapter);
                }
                else if(s1.getSelectedItem().toString().equals("SELECT")) {
                    adapter = new ArrayAdapter<String>(studentsignup.this, android.R.layout.simple_spinner_item, l1);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s2.setAdapter(adapter);
                }
                else if(s1.getSelectedItem().toString().equals("Diploma")) {
                    adapter = new ArrayAdapter<String>(studentsignup.this, android.R.layout.simple_spinner_item, l3);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s2.setAdapter(adapter);
                }
                else if(s1.getSelectedItem().toString().equals("Intermediate")) {
                    adapter = new ArrayAdapter<String>(studentsignup.this, android.R.layout.simple_spinner_item, l4);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s2.setAdapter(adapter);
                }
                else if(s1.getSelectedItem().toString().equals("Tenth")){
                    adapter = new ArrayAdapter<String>(studentsignup.this,android.R.layout.simple_spinner_item,l5);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s2.setAdapter(adapter);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sn=(EditText)findViewById(sname);
        spn=(EditText)findViewById(sphone);
        sarea=(EditText)findViewById(R.id.sarea);
        sadd=(EditText)findViewById(saddress);
        semail=(EditText)findViewById(R.id.semail);
        spd=(EditText)findViewById(R.id.spassenter);
        scpd=(EditText)findViewById(R.id.spassconfirm);
        ssub=(Button)findViewById(R.id.ssubmit);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        firebaseAuth=FirebaseAuth.getInstance();
        count=FirebaseDatabase.getInstance().getReference().child("counts");
        ssub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View v1=v;
                ssn=sn.getText().toString();
                ssarea=sarea.getText().toString();
                ssadd=sadd.getText().toString();
                ssemail=semail.getText().toString();
                ss1=s1.getSelectedItem().toString();
                ss2=s2.getSelectedItem().toString();
                spwd=spd.getText().toString();
                spcd=scpd.getText().toString();



                try {
                    sspn=Long.parseLong(spn.getText().toString());
                    if (!ssn.equals("") && sspn != null && !s1.getSelectedItem().toString().equals("")&& !s2.getSelectedItem().toString().equals("")&&!ssarea.equals("") && !ssadd.equals("") && !ssemail.equals("") && !spwd.equals("") && !spcd.equals("")) {
                        count.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Long coun1 = dataSnapshot.getValue(Long.class);
                                if(spwd.equals(spcd)) {
                                    pd.setMessage("Signing up");
                                    pd.show();
                                    editor.putString("type", "student");
                                    editor.putString("name", ssn);
                                    editor.putString("email", ssemail);
                                    editor.putString("contact", sspn.toString());
                                    editor.commit();

                                    student stu = new student(coun1, ssn, sspn, ssarea, ssadd, ssemail, ss1, ss2);

                                    Map<String, Object> postValues = stu.toMap();
                                    Map<String, Object> childUpdates = new HashMap<>();
                                    childUpdates.put("/students/student" + String.valueOf(coun1), postValues);
                                    coun1++;
                                    final Long finalCoun = coun1;
                                    Log.d("success", spwd);
                                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(ssemail, spwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {
                                            Toast.makeText(studentsignup.this, "" + spwd + "", Toast.LENGTH_LONG).show();
                                            Log.d("success", "fail");
                                        }
                                    });
                                    databaseReference.updateChildren(childUpdates).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            try {
                                                databaseReference.child("counts").setValue(finalCoun);
                                                pd.dismiss();
                                                Toast.makeText(getApplicationContext(), "student signed up Successfully", Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(getApplicationContext(), StudentHome.class);
                                                startActivity(i);
                                                finish();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Toast.makeText(v1.getContext(), "Please try again", Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            pd.dismiss();
                                            Toast.makeText(v1.getContext(), "Please try again", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    pd.dismiss();
                                }
                                else{
                                    pd.dismiss();
                                    Toast.makeText(getApplicationContext(),"Please enter the same password ",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    } else {
                        pd.dismiss();
                        Toast.makeText(studentsignup.this, "Please give all your details", Toast.LENGTH_LONG);
                    }
                }

                catch(NullPointerException n  ){
                Toast.makeText(studentsignup.this,"Please enter your phone number",Toast.LENGTH_LONG).show();
                }
                catch(NumberFormatException ne){
                    Toast.makeText(studentsignup.this,"Please enter your phone number",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
