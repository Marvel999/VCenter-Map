package com.WrapX.vcentremap.ui.FindSlot

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.WrapX.vcentremap.databinding.FindSlotFragmentBinding
import java.util.*


//Todo: Call this api = https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=110042&date=27-05-2021

class FindSlotFragment : Fragment(){






    private var _binding: FindSlotFragmentBinding? = null

    private val binding get() = _binding!!
    private lateinit var viewModel: FindSlotViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FindSlotFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FindSlotViewModel::class.java)



        _binding?.dateTv?.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                this.requireContext(), { _, year, monthOfYear, dayOfMonth ->
                    val dateStr = """$dayOfMonth-${monthOfYear + 1}-${year}"""

                    binding.dateTv.text= dateStr
                },
                year,
                month,
                day
            )
            dpd.show()
        }








    }






}