package com.WrapX.vcentremap.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.WrapX.vcentremap.R

class LoginFragment : Fragment() {


    private lateinit var viewModel: LoginViewModel
    private lateinit var singIn: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=inflater.inflate(R.layout.login_fragment, container, false);
        singIn=root.findViewById(R.id.singInButton);

        return root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        singIn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_pinCodeFragment)
        }
    }

}