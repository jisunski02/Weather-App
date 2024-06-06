package dev.jayson.weatherapp

import android.content.Intent
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
import androidx.navigation.fragment.findNavController
import dev.jayson.weatherapp.databinding.FragmentLoginBinding
import dev.jayson.weatherapp.presentation.viewmodel.LoginUserViewModel
import dev.jayson.weatherapp.presentation.viewmodel.RegisterUserViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginUserViewModel: LoginUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        loginUserViewModel = (activity as LoginActivity).loginUserViewModel

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            val usernamePattern = "^[a-zA-Z0-9]{7,}$"
            val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)\\S{8,}$"

            val isUsernameValid = binding.etUsername.text.toString().matches(Regex(usernamePattern))
            val isPasswordValid = binding.etPassword.text.toString().matches(Regex(passwordPattern))

            if (isUsernameValid && isPasswordValid) {
                viewLifecycleOwner.lifecycleScope.launch {
                    // Repeat this coroutine as long as the lifecycle is in the CREATED state
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {

                        loginUserViewModel.loginUser(binding.etUsername.text.toString(), binding.etPassword.text.toString()).collect{ userData->

                            try{
                                //Toast.makeText(context, "Success Login",Toast.LENGTH_LONG).show()
                                Log.e("Success", "${userData.username} ${userData.password}")
                                startActivity(Intent(activity, WeatherActivity::class.java)
                                )
                            }

                            catch (e: Exception){
                                Log.e("Failed", "Account does not exists")
                                Toast.makeText(context, "Account does not exists",Toast.LENGTH_LONG).show()
                            }

                        }
                }

                    }

            } else {
                Toast.makeText(context,"Invalid credentials",Toast.LENGTH_LONG).show()
            }
        }
    }
}