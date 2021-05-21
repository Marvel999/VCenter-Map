package com.WrapX.vcentremap.ui.FindVCentre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.WrapX.vcentremap.databinding.FragmentFindVcentreBinding

class FindVCentreFragment : Fragment() {

  private lateinit var homeViewModel: FindVCentreViewModel
private var _binding: FragmentFindVcentreBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    homeViewModel =
            ViewModelProvider(this).get(FindVCentreViewModel::class.java)

    _binding = FragmentFindVcentreBinding.inflate(inflater, container, false)
    val root: View = binding.root


    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}