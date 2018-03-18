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

public class instisignup extends AppCompatActivity {
    ImageView imageView,imageView1;
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    CheckBox ch1,ch2,ch3,ch4;
    Button b1;
    String iname,iarea,iadd,iemail,ipwd,icourse1="",icourse2="",icourse3="",icourse4="",iaadhar,iaddprf,icpwd;
    Long iphno=0l,iid;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference,count;
    Long coun1;

    public static final int GALLERY_INTENT = 2;
    public static final int GALLERY_INTENTO = 4;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Uri uri,uri1;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instisignup);
        getSupportActionBar().hide();

        ed1 = (EditText) findViewById(R.id.iname);
        ed2 = (EditText) findViewById(R.id.iphone);
        ed3 = (EditText) findViewById(R.id.iarea);
        ed4 = (EditText) findViewById(R.id.iaddress);
        ed5 = (EditText) findViewById(R.id.iemail);
        ed6 = (EditText) findViewById(R.id.ipwd);
        ed7 = (EditText) findViewById(R.id.ipwdcnfm);
        ch1 = (CheckBox) findViewById(R.id.ic1);
        ch2 = (CheckBox) findViewById(R.id.ic2);
        ch3 = (CheckBox) findViewById(R.id.ic3);
        ch4 = (CheckBox) findViewById(R.id.ic4);
        imageView = (ImageView) findViewById(R.id.iaadhar);
        imageView1 = (ImageView) findViewById(R.id.iaddressproof);
        b1 = (Button) findViewById(R.id.isubmit);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        pd = new ProgressDialog(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        count = FirebaseDatabase.getInstance().getReference().child("counti");

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENTO);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                iname = ed1.getText().toString();
                iarea = ed3.getText().toString();
                iadd = ed4.getText().toString();
                iemail = ed5.getText().toString();
                ipwd = ed6.getText().toString();
                icpwd = ed7.getText().toString();
                if (ch1.isChecked())
                    icourse1 = ch1.getText().toString();
                else
                    icourse1 = "";
                if (ch2.isChecked())
                    icourse2 = ch2.getText().toString();
                else
                    icourse2 = "";
                if (ch3.isChecked())
                    icourse3 = ch3.getText().toString();
                else
                    icourse3 = "";
                if (ch4.isChecked())
                    icourse4 = ch4.getText().toString();
                else
                    icourse4 = "";



            //    l && ipwd !if (iname .equals() && iphno .equals() && iarea .equals() && iadd .equals() && iemail != nul= null && (icourse2 .equals() || icourse4 .equals() || icourse1 .equals() || icourse3 .equals())) {

                    count.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            coun1 = dataSnapshot.getValue(Long.class);
                            try {
                                if(!ed2.getText().toString().equals("")) {
                                    iphno = Long.parseLong(ed2.getText().toString());
                                }
                                else
                                {
                                    iphno = 0L;
                                }
                                StorageReference st = FirebaseStorage.getInstance().getReference().child("images/" + uri.getLastPathSegment());
                                st.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        iaadhar = taskSnapshot.getDownloadUrl().toString();
                                        Log.d("aadhar", iaadhar);
                                    }
                                });
                                StorageReference st1 = FirebaseStorage.getInstance().getReference().child("images/" + uri1.getLastPathSegment());
                                st1.putFile(uri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        iaddprf = taskSnapshot.getDownloadUrl().toString();
                                        //Log.d("addproof", iaddprf);
                                        if (!iname .equals("") && iphno!=0 && !iarea.equals("") && !iadd .equals("") && !iemail .equals("") && !ipwd.equals("") && ((!icourse1.equals(""))|| (!icourse2.equals(""))|| (!icourse3.equals("")) || (!icourse4.equals(""))) && !iaadhar.equals("") &&  iaadhar!=null && !iaddprf.equals("") && iaddprf!=null &&  !icpwd.equals("")) {


                                            if (ch1.isChecked())
                                                icourse1 = ch1.getText().toString();
                                            else
                                                icourse1 = "";
                                            if (ch2.isChecked())
                                                icourse2 = ch2.getText().toString();
                                            else
                                                icourse2 = "";
                                            if (ch3.isChecked())
                                                icourse3 = ch3.getText().toString();
                                            else
                                                icourse3 = "";
                                            if (ch4.isChecked())
                                                icourse4 = ch4.getText().toString();
                                            else
                                                icourse4 = "";
                                            if (ipwd.equals(icpwd)) {

                                                pd.setMessage("Signing up ...");
                                                pd.show();
                                                editor.putString("type","institute");
                                                editor.putString("name",iname);
                                                editor.putString("email",iemail);
                                                editor.putString("contact",iphno.toString());
                                                editor.commit();

                                                Institute i = new Institute(coun1, iname, iphno, iarea, iadd, iemail, icourse1, icourse2, icourse3, icourse4, iaadhar, iaddprf);
                                                Map<String, Object> postValues = i.toMap();
                                                Map<String, Object> childUpdates = new HashMap<>();
                                                childUpdates.put("/institutes/institute" + String.valueOf(coun1), postValues);
                                                coun1++;
                                                final Long finalCoun = coun1;
                                                Log.d("success", iname);
                                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(iemail, ipwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                    @Override
                                                    public void onSuccess(AuthResult authResult) {
                                                        Log.d("success", "fali");
                                                    }
                                                });
                                                databaseReference.updateChildren(childUpdates).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        try {
                                                            databaseReference.child("counti").setValue(finalCoun);
                                                            pd.dismiss();
                                                            Toast.makeText(getApplicationContext(), "institute signed up Successfully", Toast.LENGTH_LONG).show();
                                                            Intent intent = new Intent(getApplicationContext(), InstituteHome.class);
                                                            startActivity(intent);
                                                            finish();
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                            Toast.makeText(v.getContext(), "Please try again", Toast.LENGTH_LONG).show();
                                                        }

                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        pd.dismiss();
                                                        Toast.makeText(v.getContext(), "Please try again", Toast.LENGTH_LONG).show();
                                                    }
                                                });
                       /*     Toast.makeText(instisignup.this, "logged in", Toast.LENGTH_SHORT).show();
                            System.out.println(iname+" "+iphno+" "+iemail+" "+coun1+" "+coun1[0]);
*/                                              pd.dismiss();
                                            }
                                            else {
                                                Toast.makeText(instisignup.this,"Please enter the same password",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        else{
                                            Toast.makeText(instisignup.this,"Please give all your details",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                            }
                            catch(NullPointerException n){
                                Toast.makeText(instisignup.this,"Please give your aadhar and address proof",Toast.LENGTH_LONG).show();
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            //}


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            uri=data.getData();
            Log.d("aadhar","error");
            Picasso.with(instisignup.this).load(data.getData()).fit().centerInside().into(imageView);
        }
        if (requestCode == GALLERY_INTENTO && resultCode == RESULT_OK) {
            uri1=data.getData();
            Log.d("address proof","error in it");
            Picasso.with(instisignup.this).load(data.getData()).fit().centerInside().into(imageView1);
        }
    }
}
