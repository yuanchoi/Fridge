package com.teamfive.fridge;

public class food {


    //테이블이라고 생각하고, 테이블에 들어갈 속성값을 넣기
    //파이어베이스는 RDBMS와 다르기 때문에 테이블이라는 개념이 없음. 원래는 키값이라고 부름
    String name; //재료 이름
    String date; //유통기한

    public food(){} // 생성자 메서드

    //getter, setter 설정
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public String getdate() {
        return date;
    }
    public void setdate(String date) {
        this.date = date;
    }


    //값을 추가할때 쓰는 함수, AddActivity에서 insert_food함수에서 사용할 것임.
    public food(String name, String date){
        this.name = name;
        this.date = date;
    }

}