package com.WrapX.vcentremap.ui.pinFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.WrapX.vcentremap.R

class PinCodeFragment : Fragment() {


    private lateinit var viewModel: PinCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root=inflater.inflate(R.layout.pin_code_fragment, container, false);
        return root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PinCodeViewModel::class.java)



        // TODO: Use the ViewModel
    }

}