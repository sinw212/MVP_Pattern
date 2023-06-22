package com.ww.mvp_pattern.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    // 해당 Retrofit 인스턴스는 매번 새로운 인스턴스를 생성하는건 비효율적이기 때문에 싱글톤으로 정의해서 앱 Lifecycler 단위 동안 단일 인스턴스를 유지하도록 함

    private static final String BASE_URL = "https://api.github.com/";	// 기본 Base URL

    public static ApiClient ourInstance = null;
    private static Retrofit retrofit = null;	// private 접근한정자로 외부에서 직접 접근 방지

    // ApiClient 생성자
    public ApiClient() {
        // ApiClient 타입의 ourInstance 존재 확인
        if (ourInstance == null) {
            // Null이라면 Retrofit 객체 생성
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())	// REST 요청 로그확인 위해 HttpLoggingInterceptor 등록
                    .build();
        }
    }

    // Retrofit 객체 반환 메서드, 전역함수 설정 (public static)
    public static Retrofit getInstance() {
        // ourInstance 존재 확인, 없다면 ApiClient 생성자 호출
        if (ourInstance == null) {
            ourInstance = new ApiClient();
        }

        // Retrofit 객체 반환, private 접근한정자로 설정되어서 getInstance() 메서드로만 접근가능
        return retrofit;
    }

    // REST API 요청 로그확인을 위해 LoggingInterceptor 생성
    public HttpLoggingInterceptor getIntercepter() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    public OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(getIntercepter())	// LoggingInterceptor 등록
                .build();

        return client;
    }
}
