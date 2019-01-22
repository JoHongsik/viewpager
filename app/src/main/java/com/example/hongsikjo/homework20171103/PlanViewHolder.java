package com.example.hongsikjo.homework20171103;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hongsik on 2017-10-18.
 */
//재활용 가능하게 해줌
public class PlanViewHolder extends RecyclerView.ViewHolder{
    public TextView textView1;
    public TextView textView2;

    public PlanViewHolder(View itemView) {
        super(itemView);

        // 아이템뷰에 클릭리스너 등록
        textView1 = (TextView) itemView.findViewById(R.id.textView1);  //매번 findview를 안해도 되게 해줌
        textView2 = (TextView) itemView.findViewById(R.id.textView2);
    }


    // 아이템뷰에 클릭하면 이 메소드가 자동 실행행

}
