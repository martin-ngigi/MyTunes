package com.example.mytunes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytunes.domain.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var registerSuccess = false
    private var loginSuccess = false

    fun register(user: User,password:String):Boolean{
        viewModelScope.launch {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email,password)
                .addOnSuccessListener {
                    FirebaseDatabase.getInstance().getReference("/users/${FirebaseAuth.getInstance().uid}")
                        .setValue(user)
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                registerSuccess = true
                            }
                        }.addOnFailureListener {
                            registerSuccess = false
                        }
                }.addOnFailureListener {
                    registerSuccess  = false
                }
        }
        return registerSuccess
    }
    fun login(email:String,password:String):Boolean{
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                loginSuccess = true
            }.addOnFailureListener {
                loginSuccess = false
            }
        return loginSuccess
    }
}