package com.WrapX.vcentremap.ui.FindVCentre

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.WrapX.vcentremap.adapter.VCentreAdapter
import com.WrapX.vcentremap.databinding.FragmentFindVcentreBinding
import com.WrapX.vcentremap.repo.firebaseDatabase.GetVCentre
import com.google.firebase.database.*

class FindVCentreFragment : Fragment() {

  private lateinit var homeViewModel: FindVCentreViewModel
private var _binding: FragmentFindVcentreBinding? = null

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel.getData()

        val adaptor=VCentreAdapter();
        _binding?.recyclerView?.layoutManager= LinearLayoutManager(context)
        _binding?.recyclerView?.adapter=adaptor

        homeViewModel.vCList.observe({lifecycle}){
            adaptor.submitList(it).let {
                adaptor.notifyDataSetChanged()
//                progressDialog.dismiss()
            }
            Log.e("VCentre Data",""+it.toString())
        }



    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}