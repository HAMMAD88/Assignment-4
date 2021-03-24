package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.test.models.Result;
import com.example.test.models.UsersResponse;
import com.example.test.networking.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =findViewById(R.id.recyclerview);
        progressBar=findViewById(R.id.progressbar);
        final RecyclerAdapter adapter=new RecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
//        usersResponse = new ArrayList<>();
//         imageView = findViewById(R.id.image_view);
//        textView = findViewById(R.id.text_view);

//        textView.setText("Mobile Computing");

//        Picasso.get().load("https://square.github.io/picasso/static/sample.png").into(imageView);
//        recyclerView =(RecyclerView)findViewById(R.id.recyclerview);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), usersResponse);
//        recyclerView.setAdapter(recyclerAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        service.fetchUsers(25).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                List<Result> mlist = response.body().getResults();
                progressBar.setVisibility(View.INVISIBLE);
                adapter.setUsersResponseList(mlist);
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Log.e("Main", t.toString());
            }
        });

//
//        Call<UsersResponse> call = service.getSingleUser();
//
//        call.enqueue(new Callback<UsersResponse>() {
//            @Override
//            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
//
//                Log.v("", response.body().toString());
//                List<Result> mlist =response.body().getResults();
//                progressBar.setVisibility(View.INVISIBLE);
//                adapter.setUsersResponseList(mlist);
////                usersResponse=response.body().getResults();
////                recyclerAdapter.setUsersResponseList(usersResponse);
//
////                Picasso.get().load(response.body().getResults().get(4).getPicture().getLarge()).into(imageView);
//            }
//
//            @Override
//            public void onFailure(Call<UsersResponse> call, Throwable t) {
//
//                Log.e("", t.getLocalizedMessage());
//            }
//        });
        /*
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://randomuser.me/")
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<UsersResponse> call = service.getSingleUser();

        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

                Log.v("", response.body().toString());

                Picasso.get().load(response.body().getResults().get(0).getPicture().getLarge()).into(imageView);
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

                Log.e("", t.getLocalizedMessage());

            }
        });*/
    }

    /*

    Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
     */
}