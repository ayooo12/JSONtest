package com.example.jsontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonArray;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tv);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://3.36.163.80:8080");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        retrofitAPI.getData("\"리날룰\"").enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call,
                                   @NonNull Response<Post> response) {
                if(response.isSuccessful()) {

                    Post data = response.body();
                    Log.d("TEST","성공성공");
                    Log.d("TEST", data.getName());

                    String str = data.getName() + "\n" + data.getType() + "\n";
                    tv.setText(str);
                }



            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
                Log.d("TEST","실패");;
            }


        });


    }

    public interface RetrofitAPI{
        @GET("test")
        Call<Post> getData(@Query("name") String name);

        @FormUrlEncoded
        @POST("/posts")
        Call<Post> postData(@FieldMap HashMap<String, Object> param);
    }



}

