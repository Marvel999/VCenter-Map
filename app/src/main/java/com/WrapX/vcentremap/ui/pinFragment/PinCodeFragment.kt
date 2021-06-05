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

    private var _viewBinding: PinCodeFragmentBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = PinCodeFragmentBinding.inflate(inflater, container, false)
        val root: View = viewBinding.root

        return root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userSharedPreferences = UserSharedPreferences(requireContext())
        viewBinding.include.userName.text = "Hey ${userSharedPreferences.name}"
        val pincode = userSharedPreferences.pincode.toString()

        if (!pincode.equals("110001"))
            viewBinding.edPincode.setText(userSharedPreferences.pincode.toString())

        viewBinding.findBtn.setOnClickListener {
            val pincode = viewBinding.edPincode.text.toString()
            if (pincodeValidation(pincode = pincode)) {
                userSharedPreferences.pincode = pincode
                val intent = Intent(requireActivity(), AppActivity::class.java)
                startActivity(intent)
            } else {
                Utills.showSnackBar(requireActivity(), "Invalid Pincode")
            }
        }
    }

    fun pincodeValidation(pincode: String) = !(pincode.isEmpty() && pincode.length < 0)


    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

}