package com.complete.firebasewithmvvm.ViewModels

import android.app.Application
import android.provider.Settings
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.complete.firebasewithmvvm.Repos.AuthenticationRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.annotation.NonNull




class AuthVM() : ViewModel() {
    private lateinit var application: Application
    private lateinit var repo : AuthenticationRepo
    private var _userLDVM  : MutableLiveData<FirebaseUser>? = null
     val userLDVM get() = _userLDVM
    private var _loggedDataVM : MutableLiveData<Boolean>? = null
     val loggedDataVM get() = _loggedDataVM

    constructor(application: Application) : this() {
        this.application = application
        repo = AuthenticationRepo(application)
        _userLDVM = repo.userLDRepo
        _loggedDataVM = repo.loggedDataRepo
    }

    public fun register(email:String,password:String) = GlobalScope.launch {
        repo.register(email,password)
    }
    public fun login(email:String,password:String) = GlobalScope.launch {
        repo.login(email, password)
    }
    public fun signOut() = GlobalScope.launch {
        repo.signOut()
    }
}