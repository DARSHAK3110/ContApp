package com.example.contapp;
import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.List;

public class ApplicationClass extends Application {
    public static final String APP_ID = "364B806C-6047-B1AD-FFE1-74CEA370D800";
    public static final String API_KEY = "7ACD3F7B-4906-46E3-B8D5-1BB5AC8718FB";
    public static final String SERVER_URL = "https://api.backendless.com";
    public static BackendlessUser user;
    public static List<Contact> contacts;
    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APP_ID, API_KEY );
    }
}
