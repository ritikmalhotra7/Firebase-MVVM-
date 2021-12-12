package com.complete.firebasewithmvvm.Views

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.complete.firebasewithmvvm.R
import com.complete.firebasewithmvvm.ViewModels.AuthVM
import com.complete.firebasewithmvvm.databinding.FragmentSignInBinding
import com.complete.firebasewithmvvm.databinding.FragmentSignupBinding

class SignInFragment : Fragment() {
    private var _binding : FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : AuthVM
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(
            activity?.application!!)).get(AuthVM::class.java)
        viewModel.userLDVM?.observe(this,{
            if(it != null){
                navController.navigate(R.id.action_signInFragment_to_signOutFragment)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignInBinding.inflate(layoutInflater)
        navController = Navigation.findNavController(view)

        binding.signin.setOnClickListener {
            val email = binding.emailid.text.toString()
            val pass = binding.password.text.toString()
            if(!TextUtils.isEmpty(binding.emailid.text) && !TextUtils.isEmpty(binding.password.text)){
                viewModel.login(email,pass)
            }
        }
        binding.signUpBut.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_signupFragment)
            Toast.makeText(activity,navController.navigate(R.id.action_signInFragment_to_signupFragment).toString(),Toast.LENGTH_SHORT).show()
        }
    }

}