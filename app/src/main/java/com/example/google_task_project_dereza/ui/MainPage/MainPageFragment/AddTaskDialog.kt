package com.example.google_task_project_dereza.ui.MainPage.MainPageFragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.google_task_project_dereza.data.models.TaskDataModel
import com.example.google_task_project_dereza.databinding.DialogAddTaskBinding

class AddTaskDialog(private val hostListener: AddTaskListener) : DialogFragment() {

    private var _binding: DialogAddTaskBinding? = null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.addButton.setOnClickListener{
            if (binding.taskTitle.text.isNotBlank()){
                hostListener.addTask(
                    TaskDataModel(
                        taskName = binding.taskTitle.text.toString(),
                        taskDescription = binding.taskDescription.text.toString(),
                        isFavorite = false,
                        isDone = false
                    )
                )
                dismiss()
            }else{
                Toast.makeText(
                    this.context,
                    "Add task title",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }
}