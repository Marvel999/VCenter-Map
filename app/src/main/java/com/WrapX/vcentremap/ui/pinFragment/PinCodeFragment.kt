package com.WrapX.vcentremap.ui.pinFragment

import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.WrapX.vcentremap.AppActivity
import com.WrapX.vcentremap.R
import com.WrapX.vcentremap.databinding.PinCodeFragmentBinding
import com.WrapX.vcentremap.repo.SharePrefrance.UserSharedPreferences
import com.WrapX.vcentremap.utils.Utills

class PinCodeFragment : Fragment() {


    private lateinit var viewModel: PinCodeViewModel
    private lateinit var _viewBinding:PinCodeFragmentBinding

    private val viewBinding get()=_viewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _viewBinding= PinCodeFragmentBinding.inflate(inflater,container,false)
        val root: View = viewBinding.root

        return root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PinCodeViewModel::class.java)
        val userSharedPreferences=UserSharedPreferences(requireContext())
        viewBinding.include.userName.text="Hey "+userSharedPreferences.name
        val pincode=userSharedPreferences.pincode.toString();
        if (!pincode.equals("110001"))
           viewBinding.edPincode.setText(userSharedPreferences.pincode.toString())

        viewBinding.findBtn.setOnClickListener {
            val pincode=viewBinding.edPincode.text;
            if(pincode.length==6){
                userSharedPreferences.pincode= pincode.toString();
                val intent=Intent(requireActivity(),AppActivity::class.java);
                startActivity(intent)

            }else{
                Utills.showSnackBar(requireActivity(),"Invalid Pincode")
            }
        }

        // TODO: Use the ViewModel
    }

}