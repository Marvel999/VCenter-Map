package com.WrapX.vcentremap.ui.VCentre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.WrapX.vcentremap.databinding.FragmentFindVcentreBinding
import com.WrapX.vcentremap.databinding.FragmentVcentreBinding

class VCentreFragment : Fragment() {

  private lateinit var dashboardViewModel: VCentreViewModel
private var _binding: FragmentVcentreBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    dashboardViewModel =
            ViewModelProvider(this).get(VCentreViewModel::class.java)

    _binding = FragmentVcentreBinding.inflate(inflater, container, false)
    val root: View = binding.root

    
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}