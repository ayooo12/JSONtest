package com.example.jsontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import retrofit2.http.Path;
import retrofit2.http.Query;


public class MainActivity extends AppCompatActivity {
    TextView ing[] = new TextView[4];

    String ingfull = "리날룰";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ing[0] = (TextView)findViewById(R.id.ing0);
        ing[1] = (TextView)findViewById(R.id.ing1);
        ing[2] = (TextView)findViewById(R.id.ing1);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://3.36.163.80:8080");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        ingfull = "트리클로산";



        retrofitAPI.getPosts(ingfull).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call,
                                   @NonNull Response<List<Post>> response) {
                if(response.isSuccessful()) {

                    List<Post> data = response.body();
                    Log.d("TEST","성공성공");
                    Log.d("TEST", data.get(0).getName());


                    for(int i=0;i<data.size();i++){
                        ing[i].setText(data.get(i).getName());
                        ing[i].setVisibility(View.VISIBLE);
                    }

                    /*
                    ing[0].setText(data.get(0).getName());
                    ing[1].setText(data.get(1).getName());*/

                    /*
                    if(data.get(0).getType().toString()=="O"){
                        tv[1].setBackgroundColor(Color.RED);
                    }else tv[1].setBackgroundColor(Color.YELLOW);

                    if(data.get(1).getType().toString()=="O"){
                        tv[3].setBackgroundColor(Color.RED);
                    }tv[3].setBackgroundColor(Color.YELLOW);
*/

                    for(int i=0;i<data.size();i++){
                        if(data.get(i).getType().equals("H")){
                            ing[i].setBackgroundColor(Color.RED);
                        }else ing[i].setBackgroundColor(Color.GREEN);

                    }
                }


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
                Log.d("TEST","실패");;
            }


        });


    }

    public interface RetrofitAPI{
        @GET("/IngreTest?")
        Call<List<Post>> getPosts(@Query("nameTest") String name);

        @FormUrlEncoded
        @POST("/posts")
        Call<Post> postData(@FieldMap HashMap<String, Object> param);
    }



}

