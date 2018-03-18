package com.example.dattamber.insttutorials;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

public class  TutorHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth;
    DatabaseReference dref,dref1,dref2;
    ProgressDialog pd;
    FirebaseDatabase database;

  //  ImageView iv;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        Bundle b=getIntent().getExtras();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        View view=navigationView.getHeaderView(0);
      //  iv=(ImageView)view.findViewById(R.id.imageView1);
        tv1=(TextView)view.findViewById(R.id.name1);
        tv2=(TextView)view.findViewById(R.id.mail1);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getSupportActionBar().setTitle("InstiTutors");
        tv1.setText(sharedPreferences.getString("name","user"));
        tv2.setText(sharedPreferences.getString("email","email"));
      /*  iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        navigationView.setNavigationItemSelectedListener(this);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        //if(item.getTitle().equals("Students") ) {
            dref = database.getReference("students");
            pd = new ProgressDialog(TutorHome.this);
            dref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("abcd1", "asd");
                    FirebaseUser user = mAuth.getCurrentUser();
                    List<Card> list;
                    list = new ArrayList<Card>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        student value = dataSnapshot1.getValue(student.class);
                        Card c=new Card();
                        c.setName(value.getsName());
                        c.setCourse("Studying : "+value.getsCourse());
                        c.setArea(value.getsArea());
                        c.setPhno(value.getScontact()+" ");
                        list.add(c);
                        //  pd.show();
                    }
                    RecyclerView rview = (RecyclerView)
                            findViewById(R.id.rview1);
                    LinearLayoutManager lManager =
                            new LinearLayoutManager(TutorHome.this,
                                    LinearLayoutManager.VERTICAL, false);
                    rview.setLayoutManager(lManager);
                    rview.setAdapter(new
                            StudentAdapter1(list, TutorHome.this,1));
                                          /*  lat = value.dlat();
                                            lon = value.dlon();
                                            location = value.dLocation();*/
                    Toast.makeText(getApplicationContext(), "student values presented "+list.size(), Toast.LENGTH_SHORT).show();
                    Log.d("abcd in tutour", "asd");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tutor_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        if(item.getTitle().equals("Students") ) {
            dref = database.getReference(item.getTitle().toString().toLowerCase());
            pd = new ProgressDialog(TutorHome.this);
            dref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("abcd1", "asd");
                    FirebaseUser user = mAuth.getCurrentUser();
                    List<Card> list;
                    list = new ArrayList<Card>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        student value = dataSnapshot1.getValue(student.class);
                        Card c=new Card();
                        c.setName(value.getsName());
                        c.setCourse("Studying : "+value.getsCourse());
                        c.setArea(value.getsArea());
                        c.setPhno(value.getScontact()+" ");
                        list.add(c);
                        //  pd.show();
                    }
                    RecyclerView rview = (RecyclerView)
                            findViewById(R.id.rview1);
                    LinearLayoutManager lManager =
                            new LinearLayoutManager(TutorHome.this,
                                    LinearLayoutManager.VERTICAL, false);
                    rview.setLayoutManager(lManager);
                    rview.setAdapter(new
                            StudentAdapter1(list, TutorHome.this,1));
                                          /*  lat = value.dlat();
                                            lon = value.dlon();
                                            location = value.dLocation();*/
                    Toast.makeText(getApplicationContext(), "student values presented "+list.size(), Toast.LENGTH_SHORT).show();
                    Log.d("abcd in tutour", "asd");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else if(item.getTitle().toString().equals("Logout")){
            mAuth.signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            //  finish();
        }
        else if(item.getTitle().equals("Payment")){
            Intent intent = new Intent(getApplicationContext(), payment.class);
            startActivity(intent);}
        else if(item.getTitle().equals("Institutes")){
            dref = database.getReference("institutes");
            pd = new ProgressDialog(TutorHome.this);
            dref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("abcd1", "asd");
                    FirebaseUser user = mAuth.getCurrentUser();
                    List<Card> list;
                    list = new ArrayList<Card>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Institute value = dataSnapshot1.getValue(Institute.class);
                        Card c=new Card();
                        c.setName(value.getiName());
                        Map<String,String> m;
                        String s="Courses offered : \n";
                        if(!value.getIcourse1().equals("") && value.getIcourse1()!=null ){
                            s=s+value.getIcourse1()+" ";
                        }
                        if(!value.getIcourse2().equals("") && value.getIcourse2()!=null ){
                            s=s+value.getIcourse2()+" ";
                        }
                        if(!value.getIcourse3().equals("") && value.getIcourse3()!=null ){
                            s=s+value.getIcourse3()+" ";
                        }
                        if( !value.getIcourse4().equals("") && value.getIcourse4()!=null ){
                            s=s+value.getIcourse4()+" ";
                        }
                        c.setCourse(s);
                        //+value.getIcourse2()+" "+value.getIcourse3()+" "+value.getIcourse4());
                        c.setArea(value.getIarea());
                        c.setPhno(value.getiphnumber().toString());
                        list.add(c);
                        //  pd.show();
                    }
                    RecyclerView rview = (RecyclerView)
                            findViewById(R.id.rview1);
                    LinearLayoutManager lManager =
                            new LinearLayoutManager(TutorHome.this,
                                    LinearLayoutManager.VERTICAL, false);
                    rview.setLayoutManager(lManager);
                    rview.setAdapter(new
                            StudentAdapter1(list, TutorHome.this,2));
                                          /*  lat = value.dlat();
                                            lon = value.dlon();
                                            location = value.dLocation();*/
                    Toast.makeText(getApplicationContext(), "Institute values presented "+list.size(), Toast.LENGTH_SHORT).show();
                    Log.d("abcd in institute", "asd");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else if(item.getTitle().equals("Account")){
            Intent intent = new Intent(getApplicationContext(), EditProfile.class);
            startActivity(intent);
        }
        else{

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
