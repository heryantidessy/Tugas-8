package com.appmobil.apprepair.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.appmobil.apprepair.data.repository.AuthRepository
import com.appmobil.apprepair.databinding.FragmentRegisterBinding

class RegisterrFragment : Fragment() {
    val parent: AuthActivity by lazy { activity as AuthActivity }
    val viewModel: AuthViewModel by lazy { AuthViewModel(AuthRepository(parent)) }
    lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observe()

    }

    private fun init() {
        binding.ButtonLogin.setOnClickListener {
            findNavController().navigate(RegisterrFragmentDirections.actionRegisterrFragmentToLoginFragment())
        }
    }

    private fun observe() {
        viewModel.authRegister.observe(viewLifecycleOwner) {
            if (it.isConsumed) {
                Log.i("Register", "isConsumed")
            } else if (!it.isSuccsess) {
                Toast.makeText(parent, it.message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(parent, it.message, Toast.LENGTH_SHORT).show()
                findNavController().navigate(RegisterrFragmentDirections.actionRegisterrFragmentToLoginFragment())
            }

            it.isConsumed = true
        }
    }
}