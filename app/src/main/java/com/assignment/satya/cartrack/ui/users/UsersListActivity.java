package com.assignment.satya.cartrack.ui.users;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.assignment.satya.cartrack.R;
import com.assignment.satya.cartrack.room.model.RestUserModel;
import com.assignment.satya.cartrack.ui.login.LoginActivity;
import com.assignment.satya.cartrack.ui.view.UsersRecyclerViewAdapter;

import java.util.List;


public class UsersListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button logoutButton;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        logoutButton = (Button)findViewById(R.id.logout);

        sharedPreferences = getSharedPreferences("details", MODE_PRIVATE);

        UsersViewModel usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);

        if (isNetworkConnected(this)) {
            usersViewModel.getCountriesFromAPIAndStore();
        } else {
            Toast.makeText(this, "No internet found. Showing cached list in the view", Toast.LENGTH_LONG).show();
        }

        usersViewModel.getAllCountryList().observe(this, new Observer<List<RestUserModel>>() {
            @Override
            public void onChanged(List<RestUserModel> restUserModels) {
                setUpCountryRecyclerView(restUserModels);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear();
                edit.commit();
                Intent intent=new Intent(UsersListActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean isNetworkConnected(Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void setUpCountryRecyclerView(List<RestUserModel> users) {
        UsersRecyclerViewAdapter userRecyclerViewAdapter = new UsersRecyclerViewAdapter(users);
        recyclerView.setAdapter(userRecyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setHasFixedSize(true);
    }
}
