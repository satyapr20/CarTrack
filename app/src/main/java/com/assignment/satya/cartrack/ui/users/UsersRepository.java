package com.assignment.satya.cartrack.ui.users;

import android.util.Log;
import androidx.lifecycle.LiveData;

import com.assignment.satya.cartrack.CarTrackApp;
import com.assignment.satya.cartrack.rest.RestApi;
import com.assignment.satya.cartrack.room.model.RestUserModel;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersRepository {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    String TAG = UsersRepository.class.getSimpleName();

    public LiveData<List<RestUserModel>> getUsers() {
        return CarTrackApp.database.restUserDao().getAllUsers();
    }

    public void ApiCallAndPutInDB() {
        Gson gson = new Gson();
        Retrofit retrofit =  new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build();

        RestApi restApi = retrofit.create(RestApi.class);

        Call<List<RestUserModel>> call = restApi.getallUsers();

        call.enqueue(new Callback<List<RestUserModel>>() {
            @Override
            public void onResponse(Call<List<RestUserModel>> call, Response<List<RestUserModel>> response) {
                Log.e("Satya",response.body().toString());
                if(response.code() == 200) {
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            CarTrackApp.database.restUserDao().deleteAllUsers();
                            CarTrackApp.database.restUserDao().insertAllUsers(response.body());
                        }
                    }).start();
                }
            }

            @Override
            public void onFailure(Call<List<RestUserModel>> call, Throwable t) {
                Log.e(TAG,"OOPS!! something went wrong..");
            }
        });
    }
}