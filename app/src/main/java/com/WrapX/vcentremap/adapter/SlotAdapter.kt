package com.WrapX.vcentremap.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.WrapX.vcentremap.R
import com.WrapX.vcentremap.databinding.VeccinationSlotLayoutBinding
import com.WrapX.vcentremap.repo.model.SlotData
import java.util.*

class SlotAdapter: ListAdapter<SlotData, SlotAdapter.SlotViewHolder>(

    object :DiffUtil.ItemCallback<SlotData>(){
        override fun areItemsTheSame(oldItem: SlotData, newItem: SlotData): Boolean {
            return oldItem==newItem;
        }

        override fun areContentsTheSame(oldItem: SlotData, newItem: SlotData): Boolean {
           return oldItem.toString()==newItem.toString()
        }

    }
) {

//    lateinit var _binding:VeccinationSlotLayoutBinding
//    val binding get() = _binding
    inner class SlotViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    val vcPinCode : TextView =itemView.findViewById(R.id.vcPinCode)
    val  vCName: TextView=itemView.findViewById(R.id.vcentre)
    val  vCAddress: TextView=itemView.findViewById(R.id.vcAddress)
    val  navigateBtn: ImageButton=itemView.findViewById(R.id.navigate_btn)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotAdapter.SlotViewHolder {
        val inflater=LayoutInflater.from(parent.context);
        val view=inflater.inflate(R.layout.veccination_slot_layout,parent,false);
        val root=SlotViewHolder(
            view
        )
        return root;
    }

    override fun onBindViewHolder(holder: SlotViewHolder, position: Int) {
        val slotinf=getItem(position);



    }

}