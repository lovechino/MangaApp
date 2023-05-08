package com.example.mangaapp.Data;

import android.app.Application;
import android.icu.text.UnicodeSetSpanner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication {
    private Application application;
    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private MutableLiveData<Boolean> userLogged;

    private FirebaseAuth auth;

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public MutableLiveData<Boolean> getUserLogged() {
        return userLogged;
    }

    public Authentication(Application application) {
        this.application = application;
        firebaseUserMutableLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();
        userLogged = new MutableLiveData<>();
        if ((auth.getCurrentUser()) !=null){
            firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
        }
    }
    public void register(String email,String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                    Toast.makeText(application,"successfull",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(application,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void login(String email,String password){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                }else {
                    Toast.makeText(application,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public void logOut(){
        auth.signOut();
        userLogged.postValue(true);
    }
}

