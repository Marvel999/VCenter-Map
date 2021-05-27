package com.WrapX.vcentremap.ui.FindVCentre

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.WrapX.vcentremap.InfoActivity
import com.WrapX.vcentremap.PdfViewer
import com.WrapX.vcentremap.adapter.VCentreAdapter
import com.WrapX.vcentremap.databinding.FragmentFindVcentreBinding
import com.WrapX.vcentremap.repo.SharePrefrance.UserSharedPreferences
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
        val userSharedPreferences= UserSharedPreferences(requireContext())

        userSharedPreferences.pincode?.let { homeViewModel.getData(it) }

        val adaptor=VCentreAdapter{openMap(it)};
        _binding?.recyclerView?.layoutManager= LinearLayoutManager(context)
        _binding?.recyclerView?.adapter=adaptor

        _binding?.Loading?.visibility=View.VISIBLE

        binding.header.userName.text="Hey "+userSharedPreferences.name
        homeViewModel.vCList.observe({lifecycle}){
            if(it.size>0) {
                adaptor.submitList(it).let {
                    _binding?.Loading?.visibility = View.GONE

                }
            }else{
                _binding?.Loading?.visibility = View.GONE
                _binding?.notfound?.visibility=View.VISIBLE

            }
            Log.e("VCentre Data",""+it.toString())
        }


        binding.header.imgInfo.setOnClickListener {
            val intent=Intent(activity,InfoActivity::class.java)
            startActivity(intent);
        }



    }


    fun openMap(mapLink:String){
        val intent=Intent(android.content.Intent.ACTION_VIEW, Uri.parse(mapLink))
        startActivity(intent)

    }
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}