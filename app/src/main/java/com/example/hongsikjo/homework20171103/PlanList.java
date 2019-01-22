package com.example.hongsikjo.homework20171103;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hongsik on 2017-10-18.
 */
// 인텐트 객체로 전달하기 위해서는 반드시 parcelable을 구현해야 함.
public class PlanList implements Parcelable {
    private String date;
    private String caption;
    private String memo;


    public PlanList(String date, String caption){
        this.date = date;
        this.caption = caption;
        this.memo = "";
    }
    public PlanList(String date,String caption,String memo){
        this.date = date;
        this.caption = caption;
        this.memo = memo;
    }
    // 넘겨받은 액티비티에서 parcel로부터 객체 생성
    protected PlanList(Parcel in) {
        date = in.readString();
        caption = in.readString();
        memo = in.readString();
    }

    public static final Creator<PlanList> CREATOR = new Creator<PlanList>() {
        @Override
        public PlanList createFromParcel(Parcel parcel) { // parcel를 이용해서 객체 생성.
            return new PlanList(parcel);
        }

        @Override
        public PlanList[] newArray(int size) {
            return new PlanList[size];
        }
    };

    public void setMemo(String memo) {this.memo = memo;}

    public String getMemo(){return memo;}

    public void setDate(String date){
        this.date = date;
    }

    public void setCaption(String caption){
        this.caption = caption;
    }

    public String getDate(){
        return date;
    }

    public String getCaption(){
        return caption;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    //객체를 전달하기 위해 parcel 생성
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(date);
        parcel.writeString(caption);
        parcel.writeString(memo);
    }
}
