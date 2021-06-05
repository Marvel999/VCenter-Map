package com.WrapX.vcentremap.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.WrapX.vcentremap.R
import com.WrapX.vcentremap.repo.model.VCentre

class VCentreAdapter(val onnavigationclick: (link: String) -> Unit) :
    ListAdapter<VCentre, VCentreAdapter.VCentreViewHolder>(
        object : DiffUtil.ItemCallback<VCentre>() {

            override fun areItemsTheSame(oldItem: VCentre, newItem: VCentre): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: VCentre, newItem: VCentre): Boolean {
                return oldItem.toString() == oldItem.toString()
            }


        }
    ) {

    inner class VCentreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vcPinCode: TextView = itemView.findViewById(R.id.vcPinCode)
        val vCName: TextView = itemView.findViewById(R.id.vcentre)
        val vCAddress: TextView = itemView.findViewById(R.id.vcAddress)
        val navigateBtn: ImageButton = itemView.findViewById(R.id.navigate_btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VCentreViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        val view = inflater.inflate(R.layout.find_vcentre_layout, parent, false);
        val root = VCentreViewHolder(
            view
        )
        return root;
    }

    override fun onBindViewHolder(holder: VCentreViewHolder, position: Int) {
        val vCentre = getItem(position);
        holder.vCAddress.text = vCentre.vCAddress
        holder.vCName.text = vCentre.vCName
        holder.vcPinCode.text = vCentre.vCPinCode
        holder.navigateBtn.setOnClickListener {
            onnavigationclick(vCentre.vCMapLink)
        }
    }

}