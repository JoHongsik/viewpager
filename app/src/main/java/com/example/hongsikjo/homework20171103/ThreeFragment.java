package com.example.hongsikjo.homework20171103;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by HongsikJo on 2017. 11. 3..
 */

public class ThreeFragment extends Fragment {
    private String memo;
    private TextView textview;
    private EditText editText;
    private Button btn;

    public ThreeFragment(){}  //empty 생성자

    //프레그먼트가 생성될 때 호출
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        memo = "메모장 입니다.";
        textview = (TextView) view.findViewById(R.id.three_textview);
        editText = (EditText) view.findViewById(R.id.three_editText);

        String text = editText.getText().toString();
        textview.setText(memo);

        btn = (Button) view.findViewById(R.id.three_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                memo = text;
                textview.setText(memo);
                editText.setText("");
            }
        });


    }
    // 프레그먼트에 보여질 뷰를 연결한 다음 뷰를 리턴
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_three,container,false);
    }
}
