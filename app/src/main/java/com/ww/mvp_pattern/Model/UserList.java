package com.ww.mvp_pattern.Model;

import com.google.gson.annotations.SerializedName;

public class UserList {
    // DTO Model 클래스 UserList 정의
    // 첫번째 REST API 요청인 30명의 유저목록 데이터를 저장할 용도의 DTO Model

    @SerializedName("login")    // REST Request 결과 중 저장할 속성 - "login"
    private String login;

    @SerializedName("id")       // REST Request 결과 중 저장할 속성 - "id"
    private int id;

    // UserList 생성자(Constructor)
    public UserList(String login, int id) {
        this.login = login;
        this.id = id;
    }

    // Getter & Setter 메서드
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
