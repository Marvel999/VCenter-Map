package com.WrapX.vcentremap.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.WrapX.vcentremap.R
import com.WrapX.vcentremap.repo.model.VCentrePdf

class VCentrePdfAdapter(val onReadClicked: (link: String) -> Unit) :
    ListAdapter<VCentrePdf, VCentrePdfAdapter.VCentrePdfViewHolder>(

        object : DiffUtil.ItemCallback<VCentrePdf>() {
            override fun areItemsTheSame(oldItem: VCentrePdf, newItem: VCentrePdf) = oldItem == newItem

            override fun areContentsTheSame(oldItem: VCentrePdf, newItem: VCentrePdf) = oldItem.toString() == newItem.toString()
        }
    ) {

    inner class VCentrePdfViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stateTv = itemView.findViewById<TextView>(R.id.vState)
        val readBtn = itemView.findViewById<Button>(R.id.read_btn)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VCentrePdfAdapter.VCentrePdfViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.pdf_rv_layout, parent, false)
        return VCentrePdfViewHolder(view)
    }

    override fun onBindViewHolder(holder: VCentrePdfViewHolder, position: Int) {
        val vCentrePdf = getItem(position)
        holder.stateTv.text = vCentrePdf.stateName
        holder.readBtn.setOnClickListener {
            onReadClicked(vCentrePdf.link)
        }
    }
}


