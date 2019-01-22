package com.example.hongsikjo.homework20171103;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Hongsik on 2017-10-18.
 */

public class PlanListAdapter extends RecyclerView.Adapter {
    private ArrayList<PlanList> PList;
    private Context context;

    public PlanListAdapter(Context context, ArrayList<PlanList> PList){
        this.context = context;
        this.PList = PList;
    }

    // 한개의 데이터모습을 구성
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 데이터 한개를 표시 할 아이템 뷰를 생성
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.plancardview,parent,false);
        PlanViewHolder  holder = new PlanViewHolder(itemView); //viewHolder가 이미지하고 텍스트 연결해서 가지고있음
        return holder;
    }

    // 아이템뷰의 각 세부 뷰에 데이터를 연결해주는 작업
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        PlanViewHolder viewHolder = (PlanViewHolder) holder;
        viewHolder.textView1.setText(PList.get(position).getDate());     //글씨 가져와서 셋팅하는 작업
        viewHolder.textView2.setText(PList.get(position).getCaption());  //글씨를 가져와서 셋팅하는 작업

        //이미지뷰에 클릭 리스너 등록
        viewHolder.textView1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                int pos = holder.getAdapterPosition(); // 클릭한 아이템의 위치 값

                Intent intent = new Intent(context,PlanDetailView.class);
                intent.putExtra("plan_parcel",PList.get(pos));
                intent.putExtra("num",pos);

                ((MainActivity)context).startActivityForResult(intent,1000);


            }
        });
        viewHolder.textView2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                int pos = holder.getAdapterPosition(); // 클릭한 아이템의 위치 값

                Intent intent = new Intent(context,PlanDetailView.class);
                intent.putExtra("plan_parcel",PList.get(pos));
                intent.putExtra("num",pos);
                ((MainActivity)context).startActivityForResult(intent,1000);

            }
        });
    }


    // 데이터 갯수 반환
    @Override
    public int getItemCount() {
        return PList.size();
    }
}

