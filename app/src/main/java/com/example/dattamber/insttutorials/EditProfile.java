package com.example.dattamber.insttutorials;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditProfile extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5,ed6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ed1 = (EditText) findViewById(R.id.edname);
        ed2 = (EditText) findViewById(R.id.edphno);
        ed3 = (EditText) findViewById(R.id.edarea);
        ed4 = (EditText) findViewById(R.id.edadd);
        ed5 = (EditText) findViewById(R.id.edname);
        ed6 = (EditText) findViewById(R.id.edname);
    }
}
