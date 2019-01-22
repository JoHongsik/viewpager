package com.example.hongsikjo.homework20171103;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Hongsik on 2017-11-01.
 */

public class PlanNewView extends AppCompatActivity {
    DatePicker datePicker;
    EditText editText;
    private int year;
    private int month;
    private int day;
    private String date;
    private String contents;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plannewview);

        //EditText2개 가져오기
       datePicker = (DatePicker) findViewById(R.id.datePicker);
       editText = (EditText) findViewById(R.id.editText);
    }

    protected void onClickAddBtn2(View view){
        year = datePicker.getYear();
        month = (datePicker.getMonth()+1);
        day = datePicker.getDayOfMonth();
        contents = editText.getText().toString();
        date = "" + year+ "년 "+ month+"월 "+ day+ "일 ";

        PlanList PList;

        PList = new PlanList(date,contents);
        Intent intent = getIntent();
        intent.putExtra("newList",PList);

        setResult(100,intent);
        if(contents.isEmpty()){
            Toast.makeText(this,"내용을 입력해주세요",Toast.LENGTH_LONG).show();
        }
        else {
            finish();   //화면을 닫는다.
        }

    }
}
