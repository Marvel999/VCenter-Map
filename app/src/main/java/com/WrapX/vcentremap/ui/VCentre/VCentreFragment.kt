package com.WrapX.vcentremap.ui.VCentre

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.WrapX.vcentremap.InfoActivity
import com.WrapX.vcentremap.adapter.VCentrePdfAdapter
import com.WrapX.vcentremap.databinding.FragmentFindVcentreBinding
import com.WrapX.vcentremap.databinding.FragmentVcentreBinding
import com.WrapX.vcentremap.repo.SharePrefrance.UserSharedPreferences
import com.WrapX.vcentremap.repo.model.VCentrePdf

class VCentreFragment : Fragment() {

  private lateinit var dashboardViewModel: VCentreViewModel
  private lateinit var datalist: ArrayList<VCentrePdf>
  private lateinit var vCentrePdfAdapter: VCentrePdfAdapter

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
      datalist= ArrayList()


    
    return root
  }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userSharedPreferences= UserSharedPreferences(requireContext())
        binding.header.userName.text="Hey "+userSharedPreferences.name

        binding.header.imgInfo.setOnClickListener {
            val intent= Intent(activity, InfoActivity::class.java)
            startActivity(intent);
        }

        vCentrePdfAdapter= VCentrePdfAdapter()
        binding.recyclerView.layoutManager= LinearLayoutManager(context);
        binding.recyclerView.adapter=vCentrePdfAdapter
         addDataList()
        vCentrePdfAdapter.submitList(datalist)

    }

    fun addDataList(){
        datalist.add(VCentrePdf("Goa","https://github.com/Marvel999/Imgur-App/files/6554245/Goa.pdf"))
        datalist.add(VCentrePdf("Haryana","https://github.com/Marvel999/Imgur-App/files/6554246/Haryana.pdf"))
        datalist.add(VCentrePdf("Himachal Pradesh","https://github.com/Marvel999/Imgur-App/files/6554247/Himachal.Pradesh.pdf"))
        datalist.add(VCentrePdf("Jammu & Kashmir","https://github.com/Marvel999/Imgur-App/files/6554248/Jammu.Kashmir.pdf"))
        datalist.add(VCentrePdf("Jharkhand","https://github.com/Marvel999/Imgur-App/files/6554249/Jharkhand.pdf"))
        datalist.add(VCentrePdf("Karnataka","https://github.com/Marvel999/Imgur-App/files/6554250/Karnataka.pdf"))
        datalist.add(VCentrePdf("Madhya Pradesh","https://github.com/Marvel999/Imgur-App/files/6554251/Madhya.Pradesh.pdf"))
        datalist.add(VCentrePdf("Maharashtra","https://github.com/Marvel999/Imgur-App/files/6554252/Maharashtra.pdf"))
        datalist.add(VCentrePdf("Nagaland, Meghalaya, and Manipur","https://github.com/Marvel999/Imgur-App/files/6554253/Nagaland.Meghalaya.and.Manipur.pdf"))
        datalist.add(VCentrePdf("Punjab","https://github.com/Marvel999/Imgur-App/files/6554254/Punjab.pdf"))
        datalist.add(VCentrePdf("Rajasthan and Sikkim","https://github.com/Marvel999/Imgur-App/files/6554255/Rajasthan.and.Sikkim.pdf"))
        datalist.add(VCentrePdf("Tamil Nadu","https://github.com/Marvel999/Imgur-App/files/6554257/Tamil.Nadu.pdf"))
        datalist.add(VCentrePdf("Telangana","https://github.com/Marvel999/Imgur-App/files/6554258/Telangana.pdf"))
        datalist.add(VCentrePdf("Uttar Pradesh","https://github.com/Marvel999/Imgur-App/files/6554260/Uttar.Pradesh.pdf"))
        datalist.add(VCentrePdf("Uttarakhand","https://github.com/Marvel999/Imgur-App/files/6554261/Uttarakhand.pdf"))
        datalist.add(VCentrePdf("West Bengal","https://github.com/Marvel999/Imgur-App/files/6554262/West.Bengal.pdf"))
        datalist.add(VCentrePdf("Assam","https://github.com/Marvel999/Imgur-App/files/6554263/Assam.pdf"))
        datalist.add(VCentrePdf("Bihar","https://github.com/Marvel999/Imgur-App/files/6554265/Bihar.pdf"))
        datalist.add(VCentrePdf("Chhattisgarh","https://github.com/Marvel999/Imgur-App/files/6554266/Chhattisgarh.pdf"))
        datalist.add(VCentrePdf("Delhi","https://github.com/Marvel999/Imgur-App/files/6554267/Delhi.pdf"))

    }
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}