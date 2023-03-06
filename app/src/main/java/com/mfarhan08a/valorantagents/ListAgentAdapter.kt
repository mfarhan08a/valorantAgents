package com.mfarhan08a.valorantagents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfarhan08a.valorantagents.databinding.ItemRowAgentBinding

class ListAgentAdapter(private val listAgent: ArrayList<Agent>) : RecyclerView.Adapter<ListAgentAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Agent)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowAgentBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listAgent.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name,role,biography,icon) = listAgent[position]
        Glide.with(holder.itemView.context).load(icon).into(holder.binding.imgItemIcon)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemRole.text = role
        holder.binding.tvItemBiography.text = biography
        holder.itemView.setOnClickListener {onItemClickCallback.onItemClicked(listAgent[holder.adapterPosition])}

    }

    class ListViewHolder(var binding: ItemRowAgentBinding) : RecyclerView.ViewHolder(binding.root)
}