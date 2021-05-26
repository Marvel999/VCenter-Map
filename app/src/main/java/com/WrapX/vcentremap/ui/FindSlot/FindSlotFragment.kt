package com.WrapX.vcentremap.ui.FindSlot

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.WrapX.vcentremap.adapter.SlotAdapter
import com.WrapX.vcentremap.databinding.FindSlotFragmentBinding
import com.WrapX.vcentremap.repo.model.SlotData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


//Todo: Call this api = https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=110042&date=27-05-2021

class FindSlotFragment : Fragment(){



    private lateinit var pinCode:String;
    private lateinit var date:String;
    private lateinit var slotDatalist:ArrayList<SlotData>
    private lateinit var slotAdapter: SlotAdapter


    private var _binding: FindSlotFragmentBinding? = null

    private val binding get() = _binding!!
    private lateinit var viewModel: FindSlotViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FindSlotFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        slotDatalist=ArrayList()
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
                     date=dateStr
                    binding.dateTv.text= dateStr
                },
                year,
                month,
                day
            )
            dpd.show()
        }

          slotAdapter= SlotAdapter()
        _binding?.recyclerView?.layoutManager=LinearLayoutManager(requireContext())
        _binding?.recyclerView?.adapter= slotAdapter

        binding.searchBtn.setOnClickListener {
            pinCode=binding.pinCodeTv.text.toString()
            if(fieldValidation(pinCode,date)){
                fetchData(pinCode,date);
//                viewModel.getFeed(pinCode,date);
            }else{
                Toast.makeText(this.requireContext(),"Invalid Input",Toast.LENGTH_LONG).show();
            }
        }

        viewModel.vCList.observe({lifecycle}){
//            Toast.makeText(this.requireContext(),"Size: "+it.size,Toast.LENGTH_LONG).show()
        }








    }


    private fun fetchData(pincode:String,date:String) {
        val url =
            "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=" + pincode + "&date=" + date
        val request = StringRequest(
            Request.Method.GET, url,
            object : Response.Listener<String?> {
                override fun onResponse(response: String?) {
                    try {
                        val js = JSONObject(response)
                        val stringy = js.getString("sessions")
                        val jsonArray = JSONArray(stringy)
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val name = jsonObject.getString("name")
                            val address = jsonObject.getString("address")
                            val state_name = jsonObject.getString("state_name")
                            val district_name = jsonObject.getString("district_name")
                            val pincode = jsonObject.getString("pincode")
                            val available_capacity_dose1 =
                                jsonObject.getString("available_capacity_dose1")
                            val available_capacity_dose2 =
                                jsonObject.getString("available_capacity_dose2")
                            val available_capacity = jsonObject.getString("available_capacity")
                            val fee = jsonObject.getString("fee_type")
                            val min_age_limit = jsonObject.getString("min_age_limit")
                            val vaccine = jsonObject.getString("vaccine")

                            Toast.makeText(requireContext(), jsonObject.toString(), Toast.LENGTH_SHORT).show()

                            val sloat = SlotData(
                                name,
                                address,
                                vaccine,
                                state_name,
                                pincode,
                                min_age_limit,
                                fee,
                                available_capacity_dose1,
                                available_capacity_dose2,
                                available_capacity

                            )
                            slotDatalist.add(sloat)
                        }

                        slotAdapter.submitList(slotDatalist).let {
                            slotAdapter.notifyDataSetChanged();
                        }

                    } catch (e: JSONException) {
                        e.printStackTrace() }
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {
                    Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        val requestQueue: RequestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(request)
    }



    private fun fieldValidation(pincode:String, date:String):Boolean{
        if(pincode.isEmpty())
            return false
         if(date.isEmpty())
             return false
        return true
    }






}