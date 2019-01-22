package com.example.hongsikjo.homework20171103;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Hongsik on 2017-11-01.
 */

public class PlanDetailView extends AppCompatActivity {
    private PlanList onePlan;
    private String memo;
    private  EditText editText;
    private TextView Memo_text;
    private int num;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plandetailview);

        TextView textView1 = (TextView) findViewById(R.id.captionview1);
        TextView textView2 = (TextView) findViewById(R.id.captionview2);
        editText = (EditText) findViewById(R.id.editText4);
        Button save_btn = (Button) findViewById(R.id.save_btn);
        Memo_text = (TextView) findViewById(R.id.Memo_text);

        intent = getIntent();

        onePlan = intent.getParcelableExtra("plan_parcel");
        num = intent.getIntExtra("num",0);
        String Date = onePlan.getDate();
        String photoCaption = onePlan.getCaption();
        String memo = onePlan.getMemo();


        Memo_text.setText(memo);

        //이미지뷰와 텍스트뷰에 데이터 설정

        textView1.setText(Date);

        textView2.setText(photoCaption);

    }

    protected void onClickSave(View view){
        memo = editText.getText().toString();
        String test = memo;
        onePlan.setMemo(memo);
        Memo_text.setText(onePlan.getMemo());

        intent.putExtra("newMemo",memo);
        intent.putExtra("num",num);
        setResult(1000,intent);
        Toast.makeText(this,"메모 저장 완료",Toast.LENGTH_SHORT).show();
        finish();
    }

    protected void onClickDelete(View view){
        intent.putExtra("num",num);
        setResult(10000,intent);
        Toast.makeText(this,"삭제 완료",Toast.LENGTH_SHORT).show();
        finish();
    }
}


