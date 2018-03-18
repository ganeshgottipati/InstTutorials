package com.example.dattamber.insttutorials;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class payment extends AppCompatActivity {
    ImageView paytm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        paytm = (ImageView) findViewById(R.id.paytm);
        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("net.one97.paytm");
              /*  if (isCallable(payment.this, LaunchIntent)) {
                    startActivity(LaunchIntent);// Attach any extras, start or start with callback
                } else {
                    Toast.makeText(getApplicationContext(),"dobey",Toast.LENGTH_LONG).show();// Respond to the application or activity not being available
                }*/
                try{
                    startActivity(LaunchIntent);
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(),"dobey",Toast.LENGTH_LONG).show();// Respond to the application or activity not being available
                }
            }
        });
    }
    public static boolean isCallable(Activity activity, Intent intent) {
        List<ResolveInfo> list = activity.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
