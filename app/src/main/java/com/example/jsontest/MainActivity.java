package com.example.jsontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
        builder.baseUrl("http://jsonplaceholder.typicode.com/posts/1/");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        retrofitAPI.getData("1").enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call,
                                   @NonNull Response<List<Post>> response) {
                if(response.isSuccessful()) {

                    List<Post> data = response.body();
                    Log.d("TEST","성공성공");
                    Log.d("TEST", data.get(0).getTitle());

                    String str = data.get(0).getUserId() + "\n" + data.get(0).getId() + "\n" +
                            data.get(0).getTitle() + "\n" + data.get(0).getBody();
                    tv.setText(str);
                }



            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
            }


        });


    }

    public interface RetrofitAPI{
        @GET("/posts")
        Call<List<Post>> getData(@Query("userId") String id);

        @FormUrlEncoded
        @POST("/posts")
        Call<Post> postData(@FieldMap HashMap<String, Object> param);
    }



}