package dev.jayson.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dev.jayson.weatherapp.data.model.UserData
import dev.jayson.weatherapp.databinding.FragmentLoginBinding
import dev.jayson.weatherapp.databinding.FragmentRegisterBinding
import dev.jayson.weatherapp.presentation.viewmodel.LoginUserViewModel
import dev.jayson.weatherapp.presentation.viewmodel.RegisterUserViewModel
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerUserViewModel: RegisterUserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        registerUserViewModel = (activity as LoginActivity).registerUserViewModel

        binding.btnRegister.setOnClickListener {
            val usernamePattern = "^[a-zA-Z0-9]{7,}$"
            val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)\\S{8,}$"

            val isUsernameValid = binding.etUsername.text.toString().matches(Regex(usernamePattern))
            val isPasswordValid = binding.etPassword.text.toString().matches(Regex(passwordPattern))
            val isReenterPasswordValid = binding.etPassword.text.toString() == binding.etReenterpassword.text.toString()

            if (isUsernameValid && isPasswordValid && isReenterPasswordValid) {
                val userData = UserData(username = binding.etUsername.text.toString(), password = binding.etPassword.text.toString())
                registerUser(userData)
            }

            else{
                when {
                    !isUsernameValid -> Toast.makeText(context, "Username must be at least 7 characters long, alphanumeric, and without spaces.",Toast.LENGTH_LONG).show()
                    !isPasswordValid ->Toast.makeText(context, "Password must be at least 8 characters long, with numbers and without spaces.",Toast.LENGTH_LONG).show()
                    !isReenterPasswordValid -> Toast.makeText(context, "Password and Re-enter password do not match.",Toast.LENGTH_LONG).show()
                    else -> Toast.makeText(context, "Invalid input: Unknown error.",Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun registerUser(userData: UserData){
        try{
            viewLifecycleOwner.lifecycleScope.launch {
                // Repeat this coroutine as long as the lifecycle is in the CREATED state
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                    registerUserViewModel.registerUser(userData)
                    Log.e("Registered","Success")
                    Toast.makeText(context, "Success Register", Toast.LENGTH_LONG).show()
                }
                }

        }

        catch (e:Exception){
            Log.e("RegisteredFail","Existing")
            Toast.makeText(context, "Failed to Register",Toast.LENGTH_LONG).show()
        }

    }
}