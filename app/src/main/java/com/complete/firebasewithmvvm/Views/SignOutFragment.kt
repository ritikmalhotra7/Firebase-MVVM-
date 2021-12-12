package com.complete.firebasewithmvvm.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.complete.firebasewithmvvm.R
import com.complete.firebasewithmvvm.ViewModels.AuthVM
import com.complete.firebasewithmvvm.databinding.FragmentSignInBinding
import com.complete.firebasewithmvvm.databinding.FragmentSignOutBinding

class SignOutFragment : Fragment() {
    private var _binding : FragmentSignOutBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : AuthVM
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(
            activity?.application!!
        )).get(AuthVM::class.java)
        viewModel.loggedDataVM?.observe(this,{
            if(it){
                navController.navigate(R.id.action_signOutFragment_to_signInFragment)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_out, container, false)
    }


}