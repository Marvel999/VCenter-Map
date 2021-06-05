package com.WrapX.vcentremap.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.WrapX.vcentremap.R
import com.WrapX.vcentremap.repo.model.ListItem

class CustomListAdapter(
    private val context: Context,
    private val onitemClick: onItemClick,
    private val items: ArrayList<ListItem>
) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var txtName: TextView = row?.findViewById(R.id.firstLine) as TextView
        var ivImage: ImageView = row?.findViewById(R.id.icon) as ImageView
        var mainlayout: ConstraintLayout = row?.findViewById(R.id.Mainlayout) as ConstraintLayout
    }

    interface onItemClick {
        fun onClick(name: String)
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        if (convertView == null) {
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.list_iteam, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var listItem: ListItem = getItem(position) as ListItem
        viewHolder.txtName.text = listItem.name
        viewHolder.ivImage.setImageResource(listItem.link)
        viewHolder.mainlayout.setOnClickListener {

            onitemClick.onClick(viewHolder.txtName.text.toString())
        }
        return view as View
    }
}