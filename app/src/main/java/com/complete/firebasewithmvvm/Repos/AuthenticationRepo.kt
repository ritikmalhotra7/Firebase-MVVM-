package com.complete.firebasewithmvvm.Repos

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepo(val application: Application) {
    private var mAuth :FirebaseAuth
    private var _userLDRepo  : MutableLiveData<FirebaseUser>? = null
    val userLDRepo get() = _userLDRepo!!
    private var _loggedDataRepo : MutableLiveData<Boolean>? = null
    val loggedDataRepo get() = _loggedDataRepo!!

    init {
        _userLDRepo = MutableLiveData()
        _loggedDataRepo = MutableLiveData()
        mAuth = FirebaseAuth.getInstance()
        if(mAuth.currentUser != null){
            _userLDRepo!!.postValue(mAuth.currentUser)
        }
    }
    public fun register(email:String,password:String){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                userLDRepo.postValue(mAuth.currentUser)
            }else{
                Toast.makeText(application,it.exception?.message,Toast.LENGTH_SHORT).show()
            }
        }
    }
    public fun login(email :String, password:String){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                userLDRepo.postValue(mAuth.currentUser)
            }else{
                Toast.makeText(application,it.exception?.message,Toast.LENGTH_SHORT).show()
            }
        }
    }
    public fun signOut(){
        mAuth.signOut()
        loggedDataRepo.postValue(true)
    }
}