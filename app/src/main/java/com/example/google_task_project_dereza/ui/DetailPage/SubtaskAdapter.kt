package com.example.google_task_project_dereza.ui.DetailPage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.google_task_project_dereza.R
import com.example.google_task_project_dereza.data.models.TaskDataModel
import com.example.google_task_project_dereza.databinding.ItemTaskBinding

class SubtaskAdapter(private val hostListener: ):RecyclerView.Adapter<SubtaskAdapter.ViewHolder>() {

    private var data: MutableList<TaskDataModel> = mutableListOf()

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding = ItemTaskBinding.bind(view)
        fun execute(task:TaskDataModel){
            binding.apply {
                textView.text = task.taskName
                val favoriteImg = if (task.isFavorite) R.drawable.baseline_white_star_24 else R.drawable.baseline_white_star_border_24
                favoriteButton.setImageResource(favoriteImg)
                checkBox.isChecked = task.isDone

                checkBox.setOnClickListener{
                    task.isDone = !task.isDone
                    hostListener.onChanged(task)
                }

                favoriteButton.setOnClickListener{
                    task.isFavorite = !task.isFavorite
                    hostListener.onChanged(task)
                }

                deleteButton.setOnClickListener{
                    hostListener.onDelete(task)
                }

                root.setOnClickListener{
                    hostListener.onClick(task.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.execute(data[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData:List<TaskDataModel>){
        data = newData.toMutableList()
        notifyDataSetChanged()
    }
}