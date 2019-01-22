package com.example.hongsikjo.homework20171103;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by HongsikJo on 2017. 11. 3..
 */

public class OneFragment extends Fragment  {
    Button btn;
    public OneFragment(){}  //empty 생성자
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    static PlanListAdapter adapter;    // photoAlbumAdapter 클래스를 생성해야함.
    public static ArrayList<PlanList> PList;  //Data Collection

    //프레그먼트가 생성될 때 호출
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PList = new ArrayList<PlanList>();
        setPlanData();

        //recyclerView Lookup
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        //layoutManager 생성
        layoutManager = new LinearLayoutManager(getActivity());  //리니어하게 데이터를 보여줌
        //layoutManager = new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL,false);   //두줄로 배치
        //Adapter 생성, 이때 data collecction 을 넘겨야 함
        adapter = new PlanListAdapter(getActivity(), PList);

        //RecyclerView에 LayoutManager 등록
        recyclerView.setLayoutManager(layoutManager);   //리니어하게 데이터를 보여줌
        //recyclerView에 Adapter 등록
        recyclerView.setAdapter(adapter);




    }
    // 프레그먼트에 보여질 뷰를 연결한 다음 뷰를 리턴
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_one,container,false);
    }

    private void setPlanData() {
        // DataCollection에 등록하자


        PList.add(new PlanList("2017년 11월 03일", "안드로이드 과제 하기"));
        PList.add(new PlanList("2017년 11월 05일", "안드로이드 과제 하기"));
        PList.add(new PlanList("2017년 11월 10일", "안드로이드 과제 제출"));


    }

    public static void setPlanDate(String date,String contents,String memo){
        PList.add(new PlanList(date,contents,memo));
        adapter.notifyDataSetChanged();
    }

    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 100:
                PlanList pl = data.getParcelableExtra("newList");
                PList.add(pl);
                adapter.notifyItemInserted(PList.size() - 1);
                break;
            case 1000:
                String memo = data.getStringExtra("newMemo");
                int num = data.getIntExtra("num", 0);
                //Toast.makeText(this,memo + Integer.toString(num),Toast.LENGTH_LONG).show();
                PList.get(num).setMemo(memo);
                break;
            case 10000:
                int num2 = data.getIntExtra("num", 0);
                PList.remove(num2);
                adapter.notifyDataSetChanged();
        }
    }

}
