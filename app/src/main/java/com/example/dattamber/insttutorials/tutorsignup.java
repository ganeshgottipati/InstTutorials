package com.example.dattamber.insttutorials;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class tutorsignup extends AppCompatActivity {
    ImageView imageView,imageview1;
    CheckBox tc1,tc1cse,tc1ece,tc1civil,tc1mech,tc1eee,tc2,tc2cse,tc2ece,tc2civil,tc2mech,tc2eee,tc3,tc3mpc,tc3bipc,tc3mec,tc3cec,tc4,tc4ssc,tc4icse,tc4cbse;
    public static final int GALLERY_INTENT = 2;
    public static final int GALLERY_INTENTO = 4;
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    Button tsub;

    String tname,tarea,tadd,temail,taadhar,tcour,tsubcour,tcerti,tpwd,tpcwd;

    Long tid,tphno=0l;
    Map<String,String> tsubcourse = new HashMap<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,count;
    FirebaseAuth firebaseAuth;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Uri uri,uri1;
   // Long sid;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorsignup);
        getSupportActionBar().hide();
        imageView = (ImageView) findViewById(R.id.taadhar);
        imageview1 = (ImageView) findViewById(R.id.tcerti);
        tc1 = (CheckBox) findViewById(R.id.tc1);
        tc1cse = (CheckBox) findViewById(R.id.tc1cse);
        tc1ece = (CheckBox) findViewById(R.id.tc1ece);
        tc1civil = (CheckBox) findViewById(R.id.tc1civil);
        tc1mech = (CheckBox) findViewById(R.id.tc1mech);
        tc1eee = (CheckBox) findViewById(R.id.tc1eee);
        tc2 = (CheckBox) findViewById(R.id.tc2);
        tc2cse = (CheckBox) findViewById(R.id.tc2cse);
        tc2ece = (CheckBox) findViewById(R.id.tc2ece);
        tc2civil = (CheckBox) findViewById(R.id.tc2civil);
        tc2mech = (CheckBox) findViewById(R.id.tc2mech);
        tc2eee = (CheckBox) findViewById(R.id.tc2eee);
        tc3 = (CheckBox) findViewById(R.id.tc3);
        tc3mpc = (CheckBox) findViewById(R.id.tc3mpc);
        tc3bipc = (CheckBox) findViewById(R.id.tc3bipc);
        tc3mec = (CheckBox) findViewById(R.id.tc3mec);
        tc3cec = (CheckBox) findViewById(R.id.tc3cec);
        tc4 = (CheckBox) findViewById(R.id.tc4);
        tc4ssc = (CheckBox) findViewById(R.id.tc4ssc);
        tc4icse = (CheckBox) findViewById(R.id.tc4icse);
        tc4cbse = (CheckBox) findViewById(R.id.tc4cbse);
        ed1 = (EditText) findViewById(R.id.tname);
        ed2 = (EditText) findViewById(R.id.tphone);
        ed3 = (EditText) findViewById(R.id.tarea);
        ed4 = (EditText) findViewById(R.id.taddress);
        ed5 = (EditText) findViewById(R.id.temail);
        ed6 = (EditText) findViewById(R.id.tpwd);
        ed7 = (EditText) findViewById(R.id.tpwdcnfm);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        count=FirebaseDatabase.getInstance().getReference().child("countt");
        if (tc1.isChecked()) {
            tcour = tc1.getText().toString();

            if (tc1cse.isChecked())
                tsubcour = tc1cse.getText().toString();
            else if (tc1ece.isChecked())
                tsubcour = tc1ece.getText().toString();
            else if (tc1mech.isChecked())
                tsubcour = tc1mech.getText().toString();
            else if (tc1civil.isChecked())
                tsubcour = tc1civil.getText().toString();
            else if (tc1eee.isChecked())
                tsubcour = tc1eee.getText().toString();
            tsubcourse.put(tcour, tsubcour);
        } else if (tc2.isChecked()) {
            tcour = tc2.getText().toString();

            if (tc2cse.isChecked())
                tsubcour = tc2cse.getText().toString();
            else if (tc2ece.isChecked())
                tsubcour = tc2ece.getText().toString();
            else if (tc2mech.isChecked())
                tsubcour = tc2mech.getText().toString();
            else if (tc2civil.isChecked())
                tsubcour = tc2civil.getText().toString();
            else if (tc2eee.isChecked())
                tsubcour = tc2eee.getText().toString();
            tsubcourse.put(tcour, tsubcour);
        } else if (tc3.isChecked()) {
            tcour = tc3.getText().toString();

            if (tc3mpc.isChecked())
                tsubcour = tc3mpc.getText().toString();
            else if (tc3bipc.isChecked())
                tsubcour = tc3bipc.getText().toString();
            else if (tc3mec.isChecked())
                tsubcour = tc3mec.getText().toString();
            else if (tc3cec.isChecked())
                tsubcour = tc3cec.getText().toString();
            tsubcourse.put(tcour, tsubcour);
        } else if (tc4.isChecked()) {
            tcour = tc4.getText().toString();

            if (tc4ssc.isChecked())
                tsubcour = tc4ssc.getText().toString();
            else if (tc4icse.isChecked())
                tsubcour = tc4icse.getText().toString();
            else if (tc4cbse.isChecked())
                tsubcour = tc4cbse.getText().toString();
            tsubcourse.put(tcour, tsubcour);
        }


        pd = new ProgressDialog(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENTO);
            }
        });
        tc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tc1.isChecked()) {
                    tc1cse.setVisibility(View.VISIBLE);
                    tc1ece.setVisibility(View.VISIBLE);
                    tc1mech.setVisibility(View.VISIBLE);
                    tc1civil.setVisibility(View.VISIBLE);
                    tc1eee.setVisibility(View.VISIBLE);
                } else {
                    tc1cse.setVisibility(View.GONE);
                    tc1ece.setVisibility(View.GONE);
                    tc1mech.setVisibility(View.GONE);
                    tc1civil.setVisibility(View.GONE);
                    tc1eee.setVisibility(View.GONE);
                }
            }
        });
        tc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tc2.isChecked()) {
                    tc2cse.setVisibility(View.VISIBLE);
                    tc2ece.setVisibility(View.VISIBLE);
                    tc2mech.setVisibility(View.VISIBLE);
                    tc2civil.setVisibility(View.VISIBLE);
                    tc2eee.setVisibility(View.VISIBLE);
                } else {
                    tc2cse.setVisibility(View.GONE);
                    tc2ece.setVisibility(View.GONE);
                    tc2mech.setVisibility(View.GONE);
                    tc2civil.setVisibility(View.GONE);
                    tc2eee.setVisibility(View.GONE);
                }
            }
        });
        tc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tc3.isChecked()) {
                    tc3mpc.setVisibility(View.VISIBLE);
                    tc3bipc.setVisibility(View.VISIBLE);
                    tc3mec.setVisibility(View.VISIBLE);
                    tc3cec.setVisibility(View.VISIBLE);
                } else {
                    tc3mpc.setVisibility(View.GONE);
                    tc3bipc.setVisibility(View.GONE);
                    tc3mec.setVisibility(View.GONE);
                    tc3cec.setVisibility(View.GONE);
                }
            }
        });
        tc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tc4.isChecked()) {
                    tc4ssc.setVisibility(View.VISIBLE);
                    tc4icse.setVisibility(View.VISIBLE);
                    tc4cbse.setVisibility(View.VISIBLE);
                } else {
                    tc4ssc.setVisibility(View.GONE);
                    tc4icse.setVisibility(View.GONE);
                    tc4cbse.setVisibility(View.GONE);

                }
            }
        });
        tsub = (Button)findViewById(R.id.tsubmit);
        tsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View v1 = v;
                tname = ed1.getText().toString();
                tarea = ed3.getText().toString();
                tadd = ed4.getText().toString();
                temail = ed5.getText().toString();
                tphno = Long.parseLong(ed2.getText().toString());
                tpwd = ed6.getText().toString();
                tpcwd = ed7.getText().toString();
                if (tc1.isChecked()) {
                    tcour = tc1.getText().toString();

                    if (tc1cse.isChecked())
                        tsubcour = tc1cse.getText().toString();
                    else if (tc1ece.isChecked())
                        tsubcour = tc1ece.getText().toString();
                    else if (tc1mech.isChecked())
                        tsubcour = tc1mech.getText().toString();
                    else if (tc1civil.isChecked())
                        tsubcour = tc1civil.getText().toString();
                    else if (tc1eee.isChecked())
                        tsubcour = tc1eee.getText().toString();
                    tsubcourse.put(tcour, tsubcour);
                } else if (tc2.isChecked()) {
                    tcour = tc2.getText().toString();

                    if (tc2cse.isChecked())
                        tsubcour = tc2cse.getText().toString();
                    else if (tc2ece.isChecked())
                        tsubcour = tc2ece.getText().toString();
                    else if (tc2mech.isChecked())
                        tsubcour = tc2mech.getText().toString();
                    else if (tc2civil.isChecked())
                        tsubcour = tc2civil.getText().toString();
                    else if (tc2eee.isChecked())
                        tsubcour = tc2eee.getText().toString();
                    tsubcourse.put(tcour, tsubcour);
                } else if (tc3.isChecked()) {
                    tcour = tc3.getText().toString();

                    if (tc3mpc.isChecked())
                        tsubcour = tc3mpc.getText().toString();
                    else if (tc3bipc.isChecked())
                        tsubcour = tc3bipc.getText().toString();
                    else if (tc3mec.isChecked())
                        tsubcour = tc3mec.getText().toString();
                    else if (tc3cec.isChecked())
                        tsubcour = tc3cec.getText().toString();
                    tsubcourse.put(tcour, tsubcour);
                } else if (tc4.isChecked()) {
                    tcour = tc4.getText().toString();

                    if (tc4ssc.isChecked())
                        tsubcour = tc4ssc.getText().toString();
                    else if (tc4icse.isChecked())
                        tsubcour = tc4icse.getText().toString();
                    else if (tc4cbse.isChecked())
                        tsubcour = tc4cbse.getText().toString();
                    tsubcourse.put(tcour, tsubcour);
                }
                if (true) {
                    count.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                         final Long[] coun1 = {dataSnapshot.getValue(Long.class)};
                         try {
                             StorageReference st = FirebaseStorage.getInstance().getReference().child("images/" + uri.getLastPathSegment());
                             st.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                 @Override
                                 public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                     taadhar = taskSnapshot.getDownloadUrl().toString();
                                     Log.d("aadhar", taadhar);
                                 }
                             });
                             StorageReference st1 = FirebaseStorage.getInstance().getReference().child("images/" + uri1.getLastPathSegment());
                             st1.putFile(uri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                 @Override
                                 public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                     tcerti = taskSnapshot.getDownloadUrl().toString();
                                     //Log.d("addproof", iaddprf);
                                     if (!tname .equals("") && tphno!=0 && !tarea.equals("") && !tadd .equals("") && !temail .equals("") && !tpwd.equals("") && tsubcourse!=null && !taadhar.equals("") &&  taadhar!=null  && !tcerti.equals("") && tcerti!=null &&  !tpcwd.equals("")) {


                                         if (tc1.isChecked()) {
                                             tcour = tc1.getText().toString();

                                             if (tc1cse.isChecked())
                                                 tsubcour = tc1cse.getText().toString();
                                             else if (tc1ece.isChecked())
                                                 tsubcour = tc1ece.getText().toString();
                                             else if (tc1mech.isChecked())
                                                 tsubcour = tc1mech.getText().toString();
                                             else if (tc1civil.isChecked())
                                                 tsubcour = tc1civil.getText().toString();
                                             else if (tc1eee.isChecked())
                                                 tsubcour = tc1eee.getText().toString();
                                             tsubcourse.put(tcour, tsubcour);
                                         } else if (tc2.isChecked()) {
                                             tcour = tc2.getText().toString();

                                             if (tc2cse.isChecked())
                                                 tsubcour = tc2cse.getText().toString();
                                             else if (tc2ece.isChecked())
                                                 tsubcour = tc2ece.getText().toString();
                                             else if (tc2mech.isChecked())
                                                 tsubcour = tc2mech.getText().toString();
                                             else if (tc2civil.isChecked())
                                                 tsubcour = tc2civil.getText().toString();
                                             else if (tc2eee.isChecked())
                                                 tsubcour = tc2eee.getText().toString();
                                             tsubcourse.put(tcour, tsubcour);
                                         } else if (tc3.isChecked()) {
                                             tcour = tc3.getText().toString();

                                             if (tc3mpc.isChecked())
                                                 tsubcour = tc3mpc.getText().toString();
                                             else if (tc3bipc.isChecked())
                                                 tsubcour = tc3bipc.getText().toString();
                                             else if (tc3mec.isChecked())
                                                 tsubcour = tc3mec.getText().toString();
                                             else if (tc3cec.isChecked())
                                                 tsubcour = tc3cec.getText().toString();
                                             tsubcourse.put(tcour, tsubcour);
                                         } else if (tc4.isChecked()) {
                                             tcour = tc4.getText().toString();

                                             if (tc4ssc.isChecked())
                                                 tsubcour = tc4ssc.getText().toString();
                                             else if (tc4icse.isChecked())
                                                 tsubcour = tc4icse.getText().toString();
                                             else if (tc4cbse.isChecked())
                                                 tsubcour = tc4cbse.getText().toString();
                                             tsubcourse.put(tcour, tsubcour);
                                         }
                                         if (tpwd.equals(tpcwd)) {
                                             pd.setMessage("Signing up");
                                             pd.show();

                                             editor.putString("type", "tutor");
                                             editor.putString("name", tname);
                                             editor.putString("email", temail);
                                             editor.putString("contact", tphno.toString());
                                             editor.commit();

                                             Tutuor t = new Tutuor(tname, coun1[0], tphno, tarea, tadd, temail, taadhar, tcerti, tsubcourse);
                                             Map<String, Object> postValues = t.toMap();
                                             Map<String, Object> childUpdates = new HashMap<>();
                                             childUpdates.put("/tutors/tutor" + String.valueOf(coun1[0]), postValues);

                                             final Long finalCoun = coun1[0];

                                             firebaseAuth.createUserWithEmailAndPassword(temail, tpwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                 @Override
                                                 public void onSuccess(AuthResult authResult) {
                                                     Log.d("gv", ";jhgff");
                                                 }
                                             });

                                             databaseReference.updateChildren(childUpdates).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                 @Override
                                                 public void onSuccess(Void aVoid) {
                                                     try {
                                                         long fcount = finalCoun;
                                                         fcount++;
                                                         databaseReference.child("countt").setValue(fcount);
                                                         pd.dismiss();
                                                         Toast.makeText(getApplicationContext(), "tutor signed up Successfully", Toast.LENGTH_LONG).show();
                                                         Intent intent = new Intent(getApplicationContext(), TutorHome.class);
                                                         startActivity(intent);
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
                                         } else {
                                             Toast.makeText(tutorsignup.this, "Please enter the same password", Toast.LENGTH_LONG).show();
                                         }
                                     }
                                     else{
                                         Toast.makeText(tutorsignup.this,"Please give all your details",Toast.LENGTH_LONG).show();
                                     }
                                 }
                             });
                         }
                         catch (Exception e){
                             //catch(NullPointerException n){
                                 Toast.makeText(tutorsignup.this,"Please give your aadhar proof and recent graduate certificate",Toast.LENGTH_LONG).show();
                             //}
                         }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            uri=data.getData();
            Log.d("img","lol");
            Picasso.with(tutorsignup.this).load(data.getData()).fit().centerInside().into(imageView);
        }
        if (requestCode == GALLERY_INTENTO && resultCode == RESULT_OK) {
            uri1=data.getData();
            Log.d("address proof","error in it");
            Picasso.with(tutorsignup.this).load(data.getData()).fit().centerInside().into(imageview1);
        }
    }
}