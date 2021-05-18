package com.assignment.satya.cartrack.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "users")
public class UserDatabaseModel {
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        public int id;
        @ColumnInfo(name = "email")
        public String email;
        @ColumnInfo(name = "country")
        public String country;
        @ColumnInfo(name = "token")
        public String authToken;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getCountry() {
                return country;
        }

        public void setCountry(String country) {
                this.country = country;
        }

        public String getAuthToken() {
                return authToken;
        }

        public void setAuthToken(String authToken) {
                this.authToken = authToken;
        }
}