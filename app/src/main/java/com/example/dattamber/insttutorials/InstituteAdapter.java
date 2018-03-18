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
import java.util.Map;

/**
 * Created by VeeraReddyKonabhai on 9/11/2017.
 */

public class InstituteAdapter extends RecyclerView.Adapter<InstituteHolder>{
    List<student> list1;
    Context contex;    private int expandedPosition = -1;
    List<Tutuor> list=new ArrayList<>();
    String utype="";int f=0;
    Dialog d;
    public InstituteAdapter(List<Tutuor> list, Context context,int f) {
        this.list = list;
        this.contex = context;
        this.f=f;
    }
    InstituteAdapter(List<student> list, Context context,String type) {
        this.list1 = list;
        this.contex = context;
        this.utype=type;
        f=0;
    }

    @Override
    public InstituteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=
                LayoutInflater.from(contex);
        View v=inflater.inflate(R.layout.individualview,
                parent,false);

        InstituteHolder pHolder=new InstituteHolder(v);
       // Toast.makeText(contex," individual xml is presented",Toast.LENGTH_LONG).show();
        return pHolder;
    }

    @Override
    public void onBindViewHolder(InstituteHolder holder, int position) {
        if (f == 1) {
            final Tutuor mylist = list.get(position);
            Map<String, String> m;
            holder.tv1.setText(mylist.getTname());
            m = mylist.getTsubcourse();
            holder.tv2.setText("Course : " + m.get((Object) "tsubcourse"));
            holder.tv3.setText("Contact : " + mylist.getTphno());
            holder.tv4.setText("Area : "+mylist.getTarea());


        } else {
            Log.d("onBindView","displaying students");
            final student mylist = list1.get(position);
            String m;
            holder.tv1.setText(mylist.getsName());
            m = mylist.getsCourse();
            holder.tv2.setText(m);
            holder.tv3.setText("Contact : " + mylist.getScontact());
            holder.tv4.setText("Area : "+mylist.getsArea());
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
