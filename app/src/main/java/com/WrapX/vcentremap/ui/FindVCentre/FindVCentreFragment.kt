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
import com.WrapX.vcentremap.SettingsActivity
import com.WrapX.vcentremap.adapter.VCentreAdapter
import com.WrapX.vcentremap.databinding.FragmentFindVcentreBinding
import com.WrapX.vcentremap.repo.SharePrefrance.UserSharedPreferences
import com.WrapX.vcentremap.repo.firebaseDatabase.GetVCentre
import com.WrapX.vcentremap.repo.localDB.VaccinationCentre
import com.WrapX.vcentremap.repo.model.VCentre
import com.WrapX.vcentremap.utils.CalenderDate
import com.WrapX.vcentremap.utils.CalenderDate.getDate
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.database.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


class FindVCentreFragment : Fragment() {

  private lateinit var homeViewModel: FindVCentreViewModel
  private lateinit var vaccinationCentreList: ArrayList<VCentre>
  private lateinit var userSharedPreferences: UserSharedPreferences
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
      vaccinationCentreList= ArrayList();
      userSharedPreferences= UserSharedPreferences(this.requireContext());


    return root
  }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adaptor=VCentreAdapter{openMap(it)};
        _binding?.recyclerView?.layoutManager= LinearLayoutManager(context)
        _binding?.recyclerView?.adapter=adaptor
        _binding?.Loading?.visibility=View.VISIBLE
        binding.header.userName.text="Hey "+userSharedPreferences.name

        // observe data get from api
        homeViewModel.vCList.observe({lifecycle}){
            if(it.size>0) {
                for (iteam in it){
                        homeViewModel.AddVaccinationCentre(VaccinationCentre(0,iteam.vCName,iteam.vCAddress,iteam.vCPinCode,iteam.vCMapLink))
                    }
                homeViewModel.getVaccinationcentreData()

            }
            Log.e("VCentre Data",""+it.toString())
        }

        // observe the list from database
        homeViewModel.dataBasevaccinationCentreList.observe({lifecycle}){
            var pincode=userSharedPreferences.pincode;
            if (it.size>0) {
                for (iteam in it) {
                    if (iteam.Pincode == pincode) {
                        vaccinationCentreList.add(
                            VCentre(
                                iteam.VaccinationCentreName,
                                iteam.VaccinationCentreAddress,
                                iteam.Pincode,
                                iteam.GoogleMapLink
                            )
                        )
                    }
                }
                if (vaccinationCentreList.size>0){
                adaptor.submitList(vaccinationCentreList).let {
                    _binding?.Loading?.visibility = View.GONE
                    _binding?.notfound?.visibility=View.GONE
                }
                }else{
                    _binding?.Loading?.visibility = View.GONE
                    _binding?.notfound?.visibility=View.VISIBLE
                }
            }else{
                apiCall()
            }

        }

        binding.header.imgSetting.setOnClickListener {
            val intent=Intent(activity,SettingsActivity::class.java)
            startActivity(intent);
        }



    }

   // get Api Call
    fun apiCall(){
        if (!userSharedPreferences.date.equals(CalenderDate.getDate())) {
            homeViewModel.getData().let {
                userSharedPreferences.date= CalenderDate.getDate()
            }
        }
    }

    // Open map link
    fun openMap(mapLink:String){
        val intent=Intent(android.content.Intent.ACTION_VIEW, Uri.parse(mapLink))
        startActivity(intent)

    }
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}