package com.assignment.satya.cartrack.rest;

import com.assignment.satya.cartrack.room.model.RestUserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi{
    @GET("users/")
    Call<List<RestUserModel>> getallUsers();
}