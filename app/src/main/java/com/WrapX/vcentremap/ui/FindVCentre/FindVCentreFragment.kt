package com.WrapX.vcentremap.ui.FindVCentre

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.WrapX.vcentremap.SettingsActivity
import com.WrapX.vcentremap.adapter.VCentreAdapter
import com.WrapX.vcentremap.databinding.FragmentFindVcentreBinding
import com.WrapX.vcentremap.repo.SharePrefrance.UserSharedPreferences
import com.WrapX.vcentremap.repo.localDB.VaccinationCentre
import com.WrapX.vcentremap.repo.model.VCentre
import com.WrapX.vcentremap.utils.CalenderDate
import com.WrapX.vcentremap.utils.Utills
import kotlin.collections.ArrayList


class FindVCentreFragment : Fragment() {

    private lateinit var homeViewModel: FindVCentreViewModel
    private val vaccinationCentreList: ArrayList<VCentre> = ArrayList()
    private lateinit var userSharedPreferences: UserSharedPreferences
    private var _binding: FragmentFindVcentreBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(FindVCentreViewModel::class.java)
        _binding = FragmentFindVcentreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userSharedPreferences = UserSharedPreferences(this.requireContext())

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adaptor = VCentreAdapter { openMap(it) }
        _binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        _binding?.recyclerView?.adapter = adaptor
        _binding?.Loading?.visibility = View.VISIBLE
        binding.header.userName.text = "Hey  ${userSharedPreferences.name}"

        // observe data get from api
        homeViewModel.vCList.observe({ lifecycle }) {
            if (it.size > 0) {
                homeViewModel.deleteTable()
                for (iteam in it) {
                    homeViewModel.addVaccinationCentre(
                        VaccinationCentre(
                            0,
                            iteam.vCName,
                            iteam.vCAddress,
                            iteam.vCPinCode,
                            iteam.vCMapLink
                        )
                    )
                }
                homeViewModel.getVaccinationcentreData()

            }
            Log.e("VCentre Data", "" + it.toString())
        }

        // observe the list from database
        homeViewModel.dataBasevaccinationCentreList.observe(viewLifecycleOwner) {
            val pincode = userSharedPreferences.pincode
            if (it.isNotEmpty() && userSharedPreferences.date.equals(CalenderDate.getDate())) {
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
                if (vaccinationCentreList.size > 0) {
                    adaptor.submitList(vaccinationCentreList).let {
                        _binding?.Loading?.visibility = View.GONE
                        _binding?.notfound?.visibility = View.GONE
                    }
                } else {
                    _binding?.Loading?.visibility = View.GONE
                    _binding?.notfound?.visibility = View.VISIBLE
                }
            } else {
                if (Utills.isOnline(requireContext()))
                    apiCall()
                else {
                    _binding?.Loading?.visibility = View.GONE
                    _binding?.notfound?.visibility = View.VISIBLE
                }

            }

        }

        binding.header.imgSetting.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }


    }

    // get Api Call
    private fun apiCall() {
        if (!userSharedPreferences.date.equals(CalenderDate.getDate())) {
            homeViewModel.getData().let {
                userSharedPreferences.date = CalenderDate.getDate()
            }
        }
    }

    // Open map link
    private fun openMap(mapLink: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapLink))
        startActivity(intent)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}