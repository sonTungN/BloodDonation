package com.sontung.blooddonation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sontung.blooddonation.views.OnBoardingItem
import com.sontung.blooddonation.R

class OnBoardingItemAdapter (
    private val pageItems: List<OnBoardingItem>
    ) : RecyclerView.Adapter<OnBoardingItemAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.onboarding_page_item,
                parent,
                false
            )
        
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPageItems = pageItems[position]
        holder.imageView.setImageResource(currentPageItems.imageResId)
        holder.title.text = currentPageItems.title
        holder.description.text = currentPageItems.description
    }
    
    override fun getItemCount(): Int {
        return pageItems.size
    }
    
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
    }
}