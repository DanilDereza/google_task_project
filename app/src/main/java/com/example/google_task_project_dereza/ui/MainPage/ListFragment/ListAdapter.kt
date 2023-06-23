package com.example.google_task_project_dereza.ui.MainPage.ListFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.google_task_project_dereza.R
import com.example.google_task_project_dereza.databinding.ItemTaskBinding

class ListAdapter():RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding = ItemTaskBinding.bind(view)
        fun execute(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.execute()
    }
}