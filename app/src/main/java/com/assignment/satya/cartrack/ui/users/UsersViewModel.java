package com.assignment.satya.cartrack.ui.users;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.assignment.satya.cartrack.room.model.RestUserModel;

import java.util.List;

public class UsersViewModel extends ViewModel {

    UsersRepository usersRepository = new UsersRepository();

    public LiveData<List<RestUserModel>> getAllCountryList() {
        return usersRepository.getUsers();
    }

    public void getCountriesFromAPIAndStore() {
        usersRepository.ApiCallAndPutInDB();
    }
}