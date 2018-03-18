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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button s,t,i;
    Spinner s1,s2;
    Button msub;
    EditText us,pwd;
    FirebaseAuth mAuth;
    DatabaseReference dref,dref1,dref2;
    ProgressDialog pd;
    FirebaseDatabase database;

    String uname,passwd;

    public Long si;
    public String sName;
    public Long scontact;
    public String sArea;
    public String sAddress;
    public String sEdu;
    public String sCourse;
    SharedPreferences.Editor editor;
    public String tname;
    public long tid;
    public long tphno;
    public String tarea;
    public String tadd;
    //    public String taadhar;
    public Map<String,String> tsubcourse;
    SharedPreferences sharedPreferences;
    private String iName;
    private Long iphnumber;
    private String iarea;
    private String iaddress;
    private String icourse1,icourse2,icourse3,icourse4;
    private String iaadhar;
    private String iaddprf;

    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        //Toast.makeText(getApplicationContext(),FirebaseAuth.getInstance().getCurrentUser().getEmail().toString(),Toast.LENGTH_LONG).show();
        s = (Button)findViewById(R.id.mstudent);
        t = (Button)findViewById(R.id.mtutor);
        i = (Button)findViewById(R.id.minstitute);
        us=(EditText)findViewById(R.id.memail);
        pwd=(EditText)findViewById(R.id.mpass);
        msub=(Button)findViewById(R.id.msignin);
        if(FirebaseAuth.getInstance().getCurrentUser()!= null)
        {
            if(sharedPreferences.getString("type","login").equals("student")) {
                Intent intent = new Intent(getApplicationContext(), StudentHome.class);
                startActivity(intent);
                finish();
            }
            else if(sharedPreferences.getString("type","login").equals("tutor")){
                Intent intent = new Intent(getApplicationContext(), TutorHome.class);
                startActivity(intent);
                finish();
            }
            else{
                Intent intent = new Intent(getApplicationContext(), InstituteHome.class);
                startActivity(intent);
                finish();
            }
        }
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        dref= database.getReference("students");
        dref1= database.getReference("tutors");
        dref2= database.getReference("institutes");
        pd = new ProgressDialog(MainActivity.this);

        msub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                uname = us.getText().toString();
                passwd = pwd.getText().toString();
                if (!uname.equals("") && !passwd.equals("")) {
                    pd.setMessage("Signing in");
                    pd.setCancelable(false);
                    pd.show();
                    mAuth.signInWithEmailAndPassword(uname, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //    Intent i=new Intent(v.getContext(),Main3Activity.class);
                                //       startActivity(i);

                             //   Toast.makeText(MainActivity.this,"It entered",Toast.LENGTH_LONG).show();
                                dref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        List<student> list;
                                        list = new ArrayList<student>();
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                            student value = dataSnapshot1.getValue(student.class);
                                            if (value.getsEmail().equals(user.getEmail())) {
                                                Intent intent = new Intent(getApplicationContext(), StudentHome.class);
                                                Bundle bd = new Bundle();
                                          /*  lat = value.dlat();
                                            lon = value.dlon();
                                            location = value.dLocation();*/
                                                sName = value.getsName();
                                                sArea = value.getsArea();
                                                sAddress = value.getsAddress();
                                                scontact = value.getScontact();
                                                sEdu = value.getsEdu();
                                                sCourse = value.getsCourse();
                                                editor.putString("type","student");
                                                editor.putString("name",sName);
                                                editor.putString("email",mAuth.getCurrentUser().getEmail());
                                                editor.putString("contact",scontact.toString());
                                                editor.putString("area",sArea);
                                                editor.commit();
                                                bd.putString("sCourse", sCourse);
                                                bd.putString("sEdu", sEdu);
                                                bd.putString("sEmail",user.getEmail());
                                                bd.putString("sAddress", sAddress);
                                                bd.putLong("scontact", scontact);
                                                bd.putString("sName", sName);
                                                intent.putExtras(bd);
                                                flag=1;
                                                Toast.makeText(MainActivity.this, "student logged in", Toast.LENGTH_SHORT).show();
                                                pd.dismiss();
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                        }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                dref1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Log.d("abcd1","asd");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        List<Tutuor> list;
                                        list = new ArrayList<Tutuor>();
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                            Tutuor value = dataSnapshot1.getValue(Tutuor.class);
                                            if (value.getTemail().equals(user.getEmail())) {
                                                Intent intent = new Intent(getApplicationContext(),TutorHome.class);
                                                Bundle bd = new Bundle();
                                          /*  lat = value.dlat();
                                            lon = value.dlon();
                                            location = value.dLocation();*/
                                                tname= value.getTname();
                                                tarea = value.getTarea();
                                                tadd = value.getTadd();
                                                tphno = value.getTphno();
                                                tsubcourse = value.getTsubcourse();
                                                editor.putString("type","tutor");
                                                editor.putString("name",tname);
                                                editor.putString("email",mAuth.getCurrentUser().getEmail());
                                                editor.putString("contact",tphno+"");
                                                editor.putString("area",tarea);
                                                editor.commit();
                                                bd.putString("tarea", tarea);
                                                bd.putString("tadd", tadd);
                                                bd.putLong("tphno", tphno);
                                                bd.putString("tname", tname);
                                                bd.putString("temail",user.getEmail());
                                                intent.putExtras(bd);
                                                flag=2;
                                                Toast.makeText(getApplicationContext(), "Tutor logged in", Toast.LENGTH_SHORT).show();
                                                Log.d("abcd","asd");
                                                pd.dismiss();
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                dref2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        List<Institute> list;
                                        list = new ArrayList<Institute>();
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                            Institute value = dataSnapshot1.getValue(Institute.class);
                                            if (value.getIemail().equals(user.getEmail())) {
                                                Intent intent = new Intent(getApplicationContext(), InstituteHome.class);
                                                Bundle bd = new Bundle();
                                          /*  lat = value.dlat();
                                            lon = value.dlon();
                                            location = value.dLocation();*/
                                                iName= value.getiName();
                                                iarea = value.getIarea();
                                                iaddress = value.getIaddress();
                                                iphnumber = value.getiphnumber();
                                                icourse1 = value.getIcourse1();
                                                icourse2 = value.getIcourse2();
                                                icourse3 = value.getIcourse3();
                                                icourse4 = value.getIcourse4();
                                                editor.putString("type","institute");
                                                editor.putString("name",iName);
                                                editor.putString("email",mAuth.getCurrentUser().getEmail());
                                                editor.putString("contact",iphnumber.toString());
                                                editor.putString("area",iarea);
                                                editor.commit();
                                                bd.putString("iarea", iarea);
                                                bd.putString("iaddress", iaddress);
                                                bd.putLong("iphnumber", iphnumber);
                                                bd.putString("iname", iName);
                                                bd.putString("iemail",user.getEmail());
                                                bd.putString("icourse1", icourse1);
                                                bd.putString("icourse2", icourse2);
                                                bd.putString("icourse3", icourse3);
                                                bd.putString("icourse4", icourse4);
                                                intent.putExtras(bd);
                                                Toast.makeText(MainActivity.this, "Institute logged in", Toast.LENGTH_SHORT).show();
                                                flag=3;
                                                pd.dismiss();
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                            else
                            {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(), "Please check your email and password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     Intent intent = new Intent(v.getContext(),studentsignup.class);
                                     startActivity(intent);
                                 }
                             }
        );
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),tutorsignup.class);
                startActivity(intent);
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),instisignup.class);
                startActivity(intent);
            }
        });
    }
}
