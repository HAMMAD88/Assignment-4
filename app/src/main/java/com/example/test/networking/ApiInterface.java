package com.example.test.networking;

import com.example.test.models.UsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api/?inc=name,picture")
    Call<UsersResponse> fetchUsers(@Query("results")int results);

}
