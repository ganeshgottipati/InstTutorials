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
 * Created by VeeraReddyKonabhai on 9/11/2017.
 */

public class StudentAdapter1 extends RecyclerView.Adapter<StudentHolder>{
    List<Institute> list1=new ArrayList<>();
    Context contex;    private int expandedPosition = -1;
    List<Card> list=new ArrayList<>();
    String utype="";int f=0;
    Dialog d;
    public StudentAdapter1(List<Card> list, Context context,int f) {
        this.list = list;
        this.contex = context;
        this.f=f;
    }
     public StudentAdapter1(List<Institute> list, Context context,String type) {
        this.list1 = list;
        this.contex = context;
         this.utype=type;
         f=0;
    }

    @Override
    public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=
                LayoutInflater.from(contex);
        View v=inflater.inflate(R.layout.individualview,
                parent,false);

        StudentHolder pHolder=new StudentHolder(v);
       // Toast.makeText(contex," individual xml is presented",Toast.LENGTH_LONG).show();
        return pHolder;
    }

    @Override
    public void onBindViewHolder(StudentHolder holder, int position) {
        /*if (f == 1) {
            final Tutuor mylist = list.get(position);
            Map<String, String> m;
            holder.tv1.setText(mylist.getTname());
            m = mylist.getTsubcourse();
            holder.tv2.setText("course:" + m.get("Engineering")+" "+m.get("School"));
            holder.tv3.setText("" + mylist.getTphno());
            holder.iv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d = new Dialog(contex);
                    //   d.setContentView(R.layout.);
                    d.setTitle(mylist.getTname());
                    //Dialog configuration
                    //        d.show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+mylist.contactNumber()));
                if(true)
                {
                    v.getContext().startActivity(intent);
                }
                }
            });
        } else {*/
            Log.d("onBindView","displaying institutes");
            final Card mylist = list.get(position);
            String m;
            holder.tv1.setText(mylist.getName());
            m = mylist.getCourse();
            holder.tv2.setText(m);
            holder.tv3.setText("Contact : " + mylist.getPhno());
            holder.tv4.setText("Area : "+mylist.getArea());

      //  }

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
