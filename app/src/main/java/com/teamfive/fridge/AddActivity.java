package com.teamfive.fridge;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    //파이어베이스
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    //리싸이클뷰
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<food> arrayList;

    //데이트 다이얼로그
    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    Button search_btn;
    EditText search_area,date_area;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        search_btn = findViewById(R.id.search_btn); //버튼 연결
        search_area = findViewById(R.id.search_area); //텍스트창 연결
        date_area = findViewById(R.id.date_area); //유통기한창 연결

        recyclerView = findViewById(R.id.recyclerView); //아이디 연결
        recyclerView.setHasFixedSize(true); //리사이클러뷰 성능강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //food 객체를 어댑터 쪽으로 담을 배열리스트

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("foodlist");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터 가져오는 곳
                arrayList.clear(); //초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    food food = snapshot.getValue(food.class);
                    arrayList.add(food);
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //db Read error 발생 경우
                Log.e("AddActivity", String.valueOf(error.toException()));
            }
        });

        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);

        date_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddActivity.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //에디트 텍스트 값을 문자열로 바꾸어 insert_food 함수에 넣는다
                insert_food(search_area.getText().toString(), date_area.getText().toString());
            }
        });

    }



    //값을 파이어베이스 Realtime database로 넘기는 함수
    public void insert_food(String name, String date) {

        //food.java에서 선언했던 함수.
        food food = new food(name, date);

        //child는 해당 키 위치로 이동하는 함수.
        //키가 없는데 "foodlist"와 name같이 값을 지정한 경우 자동으로 생성합니다.
        //databaseReference.child("foodlist").child(name).setValue(food);
        databaseReference.child(name).setValue(food);

        //액티비티 새로고침
        try {
            //TODO 액티비티 화면 재갱신 시키는 코드
            Intent intent = getIntent();
            finish(); //현재 액티비티 종료 실시
            overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
            startActivity(intent); //현재 액티비티 재실행 실시
            overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //데이트다이얼로그 함수
    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";    // 출력형식   2022/11/02
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText date_area = (EditText) findViewById(R.id.date_area);
        date_area.setText(sdf.format(myCalendar.getTime()));


    }
}