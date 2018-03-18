package com.example.dattamber.insttutorials;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VeeraReddyKonabhai on 9/12/2017.
 */

public class TutorAdapter extends RecyclerView.Adapter<TutorHolder>{
    List<Institute> list1;
    Context contex;    private int expandedPosition = -1;
    List<student> list=new ArrayList<>();
    String utype="";int f=0;
    Dialog d;
    public TutorAdapter(List<student> list, Context context,int f) {
        this.list = list;
        this.contex = context;
        this.f=f;
    }
    TutorAdapter(List<Institute> list, Context context,String type) {
        this.list1 = list;
        this.contex = context;
        this.utype=type;
        f=0;
    }

    @Override
    public TutorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=
                LayoutInflater.from(contex);
        View v=inflater.inflate(R.layout.individualview,
                parent,false);

        TutorHolder pHolder=new TutorHolder(v);
       // Toast.makeText(contex," individual xml is presented",Toast.LENGTH_LONG).show();
        return pHolder;
    }

    @Override
    public void onBindViewHolder(TutorHolder holder, int position) {
        if (f == 1) {
            final student mylist = list.get(position);
            String m;
            holder.tv1.setText(mylist.getsName());
            m = mylist.getsCourse();
            holder.tv2.setText("Course : " + m);
            holder.tv3.setText("Contact : " + mylist.getScontact());
            holder.tv4.setText("Area : "+mylist.getsArea());

        } else {
            Log.d("onBindView","displaying institutes");
            final Institute mylist = list1.get(position);
            String m;
            holder.tv1.setText(mylist.getiName());
            m = mylist.getIcourse1() + " " + mylist.getIcourse2() + " " + mylist.getIcourse3() + " " + mylist.getIcourse4();
            holder.tv2.setText(m);
            holder.tv3.setText("Contact : " + mylist.getiphnumber());
            holder.tv4.setText("Area : " + mylist.getIarea());
                   }

    }


    @Override
    public int getItemCount() {
        int arr = 0;

        try{
            if(list.size()==0){
                arr = 0;
            }
            else{
                arr=list.size();
            }
        }catch (Exception e){

        }

        return arr;

    }
}

