package com.ww.mvp_pattern.Network;

import com.ww.mvp_pattern.Model.User;
import com.ww.mvp_pattern.Model.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiInterface {
    // Retrofit REST 요청 메서드를 정의한 interface 선언

    // GET 요청, User 목록 요청 (MainCall)
    @GET("users")
    Call<List<UserList>> getUsers(@Header("Authorization") String token);


    // GET 요청, 각 User 세부 정보 요청 (SubCall)
    @GET("users/{login}")	// {login} 부분을 함수인자로 전달받도록 설정
    Call<User> getUserInfo(
            @Header("Authorization") String token,
            @Path("login") String login
    );
}
