package com.github.albertopeam.spoktify.ui.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.albertopeam.spoktify.R
import com.github.albertopeam.spoktify.app.extensions.items
import com.github.albertopeam.spoktify.ui.items.model.Section

class SectionsRecyclerViewAdapter(var sections: List<Section> = listOf()): RecyclerView.Adapter<SectionRecyclerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SectionRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return SectionRecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SectionRecyclerViewHolder, position: Int) {
        holder.title.text = sections[position].title
        holder.recyclerView.items(sections[position].items)
    }

    override fun getItemCount(): Int {
        return sections.count()
    }
}

class SectionRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.item_recycler_view_title_id)
    val recyclerView: RecyclerView = itemView.findViewById(R.id.item_recycler_view_recycler_id)

    init {
        recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = ItemsAdapter()
    }

}