package com.example.google_task_project_dereza.ui.DetailPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.google_task_project_dereza.data.models.TaskDataModel
import com.example.google_task_project_dereza.databinding.DialogAddSubtaskBinding
import java.util.UUID

class AddSubtaskDialog(): DialogFragment(){
    private var _binding: DialogAddSubtaskBinding? = null
    private val binding get()= _binding!!

    private lateinit var hostListener: AddSubtaskListener
    private lateinit var parentId: UUID

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddSubtaskBinding.inflate(inflater, container, false)

        hostListener = requireArguments().getSerializable(PARENT_LISTENER) as AddSubtaskListener
        parentId = requireArguments().getSerializable(PARENT_TASK_ID) as UUID

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.addButton.setOnClickListener{
            if (binding.taskTitle.text.isNotBlank()){
                hostListener.addSubtask(
                    TaskDataModel(
                        taskName = binding.taskTitle.text.toString(),
                        taskDescription = "",
                        isFavorite = false,
                        isDone = false,
                        subtask = parentId
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

    companion object{

        private const val PARENT_TASK_ID = "parentTaskId"
        private const val PARENT_LISTENER = "parentListener"

        fun newInstance(hostListener: AddSubtaskListener, id:UUID):AddSubtaskDialog{
            val args = bundleOf(
                PARENT_TASK_ID to id,
                PARENT_LISTENER to hostListener
            )

            return AddSubtaskDialog().apply {

            }
        }
    }
}