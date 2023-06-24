package com.example.google_task_project_dereza.ui.DetailPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.google_task_project_dereza.data.models.TaskDataModel
import com.example.google_task_project_dereza.databinding.FragmentDetailBinding
import java.util.UUID

class DetailFragment: Fragment(), AddSubtaskListener {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailFragmentViewModel by lazy {
        ViewModelProvider(this)[DetailFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel.setTaskId(requireArguments().getSerializable(TASK_ID) as UUID)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onStart()
        viewModel.task.observe(viewLifecycleOwner){
            binding.apply {
                taskTitle.setText(it.taskName)
                taskDescription.setText(it.taskDescription)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }

    companion object{

        private const val TASK_ID = "taskId"

        fun newInstance(taskId: UUID):DetailFragment{
            val arg = bundleOf(
                TASK_ID to taskId
            )

            return DetailFragment().apply {
                arguments = arg
            }
        }
    }

    override fun addSubtask(subtask: TaskDataModel) {
        viewModel.addSubtask(subtask)
    }

}