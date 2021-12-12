package com.complete.firebasewithmvvm.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.complete.firebasewithmvvm.R
import com.complete.firebasewithmvvm.ViewModels.AuthVM
import com.complete.firebasewithmvvm.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseUser


class SignupFragment : Fragment() {
    private var _binding : FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : AuthVM
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(
            activity?.application!!
        )).get(AuthVM::class.java)
        viewModel.userLDVM?.observe(this,{
            if(it != null){
                navController.navigate(R.id.action_signupFragment_to_signInFragment2)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignupBinding.inflate(layoutInflater)
        navController = Navigation.findNavController(binding.root)

        binding.loginButton.setOnClickListener {
            navController.navigate(R.id.action_signupFragment_to_signInFragment2)
        }
    }
}