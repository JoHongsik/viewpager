package com.example.hongsikjo.homework20171103;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by HongsikJo on 2017. 11. 3..
 */

public class TwoFragment extends Fragment {
    public TwoFragment(){}  //empty 생성자
    DatePicker datePicker;
    EditText editText;
    private int year;
    private int month;
    private int day;
    private String date;
    private String contents;
    private Button add_btn;
    MainActivity mHostActivity;


    //프레그먼트가 생성될 때 호출
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //EditText2개 가져오기
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        editText = (EditText) view.findViewById(R.id.editText);

        add_btn = (Button) view.findViewById(R.id.button);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = datePicker.getYear();
                month = (datePicker.getMonth()+1);
                day = datePicker.getDayOfMonth();
                contents = editText.getText().toString();
                date = "" + year+ "년 "+ month+"월 "+ day+ "일 ";

                OneFragment.PList.add(new PlanList(date, contents));
                OneFragment.adapter.notifyDataSetChanged();

                editText.setText("");
                Toast.makeText(getActivity(),"추가 되었습니다.",Toast.LENGTH_LONG);
                MainActivity.viewPager.setCurrentItem(1);

            }
        });

    }
    // 프레그먼트에 보여질 뷰를 연결한 다음 뷰를 리턴
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_two,container,false);
    }

    private void Addbtn(View v){
        year = datePicker.getYear();
        month = (datePicker.getMonth()+1);
        day = datePicker.getDayOfMonth();
        contents = editText.getText().toString();
        date = "" + year+ "년 "+ month+"월 "+ day+ "일 ";

        PlanList PL = new PlanList(date,contents);
        OneFragment.PList.add(PL);
    }
}
