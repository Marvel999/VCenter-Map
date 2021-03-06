package com.WrapX.vcentremap.ui.VCentre

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.WrapX.vcentremap.PdfViewer
import com.WrapX.vcentremap.SettingsActivity
import com.WrapX.vcentremap.adapter.VCentrePdfAdapter
import com.WrapX.vcentremap.databinding.FragmentVcentreBinding
import com.WrapX.vcentremap.repo.SharePrefrance.UserSharedPreferences
import com.WrapX.vcentremap.repo.model.VCentrePdf

class VCentreFragment : Fragment() {

    private var datalist: ArrayList<VCentrePdf> = ArrayList()
    private lateinit var vCentrePdfAdapter: VCentrePdfAdapter

    private var _binding: FragmentVcentreBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentVcentreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userSharedPreferences = UserSharedPreferences(requireContext())
        binding.header.userName.text = "Hey ${userSharedPreferences.name}"

        binding.header.imgSetting.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent);
        }

        vCentrePdfAdapter = VCentrePdfAdapter {
            openPdf(it)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context);
        binding.recyclerView.adapter = vCentrePdfAdapter
        addDataList()
        vCentrePdfAdapter.submitList(datalist)

    }

    private fun addDataList() {
        datalist.add(
            VCentrePdf(
                "Goa",
                "https://github.com/Marvel999/Imgur-App/files/6554245/Goa.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Haryana",
                "https://github.com/Marvel999/Imgur-App/files/6554246/Haryana.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Himachal Pradesh",
                "https://github.com/Marvel999/Imgur-App/files/6554247/Himachal.Pradesh.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Jammu & Kashmir",
                "https://github.com/Marvel999/Imgur-App/files/6554248/Jammu.Kashmir.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Jharkhand",
                "https://github.com/Marvel999/Imgur-App/files/6554249/Jharkhand.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Karnataka",
                "https://github.com/Marvel999/Imgur-App/files/6554250/Karnataka.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Madhya Pradesh",
                "https://github.com/Marvel999/Imgur-App/files/6554251/Madhya.Pradesh.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Maharashtra",
                "https://github.com/Marvel999/Imgur-App/files/6554252/Maharashtra.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Nagaland, Meghalaya, and Manipur",
                "https://github.com/Marvel999/Imgur-App/files/6554253/Nagaland.Meghalaya.and.Manipur.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Punjab",
                "https://github.com/Marvel999/Imgur-App/files/6554254/Punjab.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Rajasthan and Sikkim",
                "https://github.com/Marvel999/Imgur-App/files/6554255/Rajasthan.and.Sikkim.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Tamil Nadu",
                "https://github.com/Marvel999/Imgur-App/files/6554257/Tamil.Nadu.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Telangana",
                "https://github.com/Marvel999/Imgur-App/files/6554258/Telangana.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Uttar Pradesh",
                "https://github.com/Marvel999/Imgur-App/files/6554260/Uttar.Pradesh.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Uttarakhand",
                "https://github.com/Marvel999/Imgur-App/files/6554261/Uttarakhand.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "West Bengal",
                "https://github.com/Marvel999/Imgur-App/files/6554262/West.Bengal.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Assam",
                "https://github.com/Marvel999/Imgur-App/files/6554263/Assam.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Bihar",
                "https://github.com/Marvel999/Imgur-App/files/6554265/Bihar.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Chhattisgarh",
                "https://github.com/Marvel999/Imgur-App/files/6554266/Chhattisgarh.pdf"
            )
        )
        datalist.add(
            VCentrePdf(
                "Delhi",
                "https://github.com/Marvel999/Imgur-App/files/6554267/Delhi.pdf"
            )
        )

    }

    private fun openPdf(link: String) {
        val intent = Intent(activity, PdfViewer::class.java)
        intent.putExtra("link", link)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}