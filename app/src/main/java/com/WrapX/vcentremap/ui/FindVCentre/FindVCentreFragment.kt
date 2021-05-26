package com.WrapX.vcentremap.ui.FindVCentre

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.WrapX.vcentremap.adapter.VCentreAdapter
import com.WrapX.vcentremap.databinding.FragmentFindVcentreBinding
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.database.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


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

        _binding?.header?.userName?.text="Rama"

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