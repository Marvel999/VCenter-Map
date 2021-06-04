package com.WrapX.vcentremap.ui.FindSlot

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.WrapX.vcentremap.InfoActivity
import com.WrapX.vcentremap.adapter.SlotAdapter
import com.WrapX.vcentremap.databinding.FindSlotFragmentBinding
import com.WrapX.vcentremap.repo.model.SlotData
import com.WrapX.vcentremap.utils.Utills
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



    private  var pinCode:String?=""
    private  var date:String?=null
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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FindSlotViewModel::class.java)


        _binding?.noResultFirst?.Mainlayout?.visibility=View.VISIBLE

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
            _binding?.pinCodeTv?.let { it1 -> hideSoftKeyboard(it1) }

            if(!pinCode!!.isEmpty() && date?.isEmpty()==false && pinCode!!.length>=6){
                slotDatalist.clear()
                _binding?.noResultFirst?.Mainlayout?.visibility=View.GONE
                _binding?.Loading?.visibility=View.VISIBLE
                if(Utills.isOnline(requireContext()))
                fetchData(pinCode!!,date!!);
                else{
                    _binding?.Loading?.visibility=View.GONE
                    _binding?.notfound?.visibility=View.VISIBLE
                    Utills.showSnackBar(requireActivity(),"No Internet")
                }
//                viewModel.getFeed(pinCode,date);
            }else{

//                Toast.makeText(this.requireContext(),"Invalid Input",Toast.LENGTH_LONG).show();
                Utills.showSnackBar(requireActivity(),"Invalid Input")
            }
        }

        viewModel.vCList.observe({lifecycle}){
//            Toast.makeText(this.requireContext(),"Size: "+it.size,Toast.LENGTH_LONG).show()
        }


        binding.infoIV.setOnClickListener {
            val intent = Intent(activity,InfoActivity::class.java);
            startActivity(intent)
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

//                            Toast.makeText(requireContext(), jsonObject.toString(), Toast.LENGTH_SHORT).show()

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

                        if (slotDatalist.size>0){
                            _binding?.noResultFirst?.Mainlayout?.visibility=View.GONE
                            _binding?.notfound?.visibility=View.GONE
                            _binding?.Loading?.visibility=View.GONE
                            slotAdapter.submitList(slotDatalist).let {
                           slotAdapter.notifyDataSetChanged();
                        }
                        }else{
                            _binding?.Loading?.visibility=View.GONE
                            _binding?.notfound?.visibility=View.VISIBLE

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






    fun hideSoftKeyboard(view: View) {
        val imm =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }




}