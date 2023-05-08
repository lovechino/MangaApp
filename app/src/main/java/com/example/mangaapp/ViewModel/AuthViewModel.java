package com.example.mangaapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mangaapp.Data.Authentication;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends AndroidViewModel {
    private Authentication authentication;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<Boolean> loggedStatus;
    public AuthViewModel(@NonNull Application application) {
        super(application);
        authentication = new Authentication(application);
        userData = authentication.getFirebaseUserMutableLiveData();
        loggedStatus = authentication.getUserLogged();
    }
    public void register(String email,String password){
        authentication.register(email, password);
    }
    public void signin(String email,String password){
        authentication.login(email, password);
    }
    public void signOut(){
        authentication.logOut();
    }
}
