package com.example.retrofit_exercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewNewsAdapter(private val dataSet: List<Article>) : RecyclerView.Adapter<RecyclerViewNewsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView
        val textViewDescription: TextView

        init {
            textViewTitle = view.findViewById(R.id.title)
            textViewDescription = view.findViewById(R.id.description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_tile, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewTitle.text = dataSet[position].title
        holder.textViewDescription.text = dataSet[position].description
    }

    override fun getItemCount() = dataSet.size


}