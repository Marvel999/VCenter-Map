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
import com.WrapX.vcentremap.repo.model.SlotData
import java.util.*

class SlotAdapter : ListAdapter<SlotData, SlotAdapter.SlotViewHolder>(

    object : DiffUtil.ItemCallback<SlotData>() {
        override fun areItemsTheSame(oldItem: SlotData, newItem: SlotData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SlotData, newItem: SlotData): Boolean {
            return oldItem.toString() == newItem.toString()
        }

    }
) {

    inner class SlotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val vCentreName: TextView = itemView.findViewById(R.id.vCentreName)
        val vCentreAddress: TextView = itemView.findViewById(R.id.vCentreAddress)
        val vaccination: TextView = itemView.findViewById(R.id.vaccination)
        val vcPinCode: TextView = itemView.findViewById(R.id.vcPinCode)
        val vcstate: TextView = itemView.findViewById(R.id.vcstate)
        val vcAgeLimit: TextView = itemView.findViewById(R.id.vcAgeLimit)
        val vcType: TextView = itemView.findViewById(R.id.vcType)
        val vcDoseOne: TextView = itemView.findViewById(R.id.vcDoseOne)
        val vcDoseTwo: TextView = itemView.findViewById(R.id.vcDoseTwo)
        val vcDosetotal: TextView = itemView.findViewById(R.id.vcDosetotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotAdapter.SlotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.veccination_slot_layout, parent, false)
        val root = SlotViewHolder(
            view
        )
        return root
    }

    override fun onBindViewHolder(holder: SlotViewHolder, position: Int) {
        val slotinf = getItem(position)
        holder.vCentreName.text = slotinf.vCName
        holder.vCentreAddress.text = slotinf.vCAddress
        holder.vaccination.text = slotinf.vaccineName
        holder.vcPinCode.text = "Pin Code: " + slotinf.vCPincode
        holder.vcstate.text = "State: " + slotinf.vState
        holder.vcAgeLimit.text = "Age Limit: " + slotinf.vCAgeLimit
        holder.vcType.text = "Type: " + slotinf.VType
        holder.vcDoseOne.text = "Dose 01: " + slotinf.vDose1
        holder.vcDoseTwo.text = "Dose 02: " + slotinf.vDose2
        holder.vcDosetotal.text = "Total: " + slotinf.vTotal


    }

}