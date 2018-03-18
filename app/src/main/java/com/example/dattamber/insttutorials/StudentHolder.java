package com.example.dattamber.insttutorials;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by VeeraReddyKonabhai on 9/11/2017.
 */

public class StudentHolder extends RecyclerView.ViewHolder {
    public TextView tv1,tv2,tv3,tv4;
  //  public ImageView iv1;
    public StudentHolder(View itemView) {
        super(itemView);
        tv1=(TextView)itemView.findViewById(R.id.name);
        tv2=(TextView)itemView.findViewById(R.id.courses);
        tv3=(TextView)itemView.findViewById(R.id.phone);
        tv4=(TextView)itemView.findViewById(R.id.area);
       // tv2=(TextView)itemView.findViewById(R.id.course);
     //   iv1=(ImageView)itemView.findViewById(R.id.loc_img);
    }
}
