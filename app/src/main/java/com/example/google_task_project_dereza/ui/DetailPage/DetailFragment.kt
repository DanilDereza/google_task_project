package com.example.google_task_project_dereza.ui.DetailPage

import android.app.ProgressDialog.show
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.google_task_project_dereza.data.models.TaskDataModel
import com.example.google_task_project_dereza.databinding.FragmentDetailBinding
import com.example.google_task_project_dereza.ui.MainPage.ListFragment.AdapterListener
import java.util.UUID

private const val REQUEST_ADD_TASK = 0
private const val DIALOG_ADD_SUBTASK = "addSubtaskDialog"

class DetailFragment: Fragment(), AddSubtaskListener, AdapterListener {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val adapter = SubtaskAdapter(this)
    private lateinit var taskId: UUID
    private lateinit var task: TaskDataModel
    private val viewModel: DetailFragmentViewModel by lazy {
        ViewModelProvider(this)[DetailFragmentViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        taskId = requireArguments().getSerializable(TASK_ID) as UUID
        viewModel.setTaskId(taskId)
        binding.recyclerView.adapter = adapter

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onStart()
        viewModel.task.observe(viewLifecycleOwner){
            binding.apply {
                task = it
                taskTitle.setText(it.taskName)
                taskDescription.setText(it.taskDescription)
            }
        }

        binding.addButton.setOnClickListener{
            AddSubtaskDialog.newInstance(this, taskId).apply {
            setTargetFragment(this@DetailFragment, REQUEST_ADD_TASK)
            show(this@DetailFragment.requireFragmentManager(), DIALOG_ADD_SUBTASK)
            }
        }

        viewModel.subtasks.observe(viewLifecycleOwner){
            adapter.updateData(it)
        }

        binding.apply {
            taskTitle.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    task.taskName = s.toString()
                }
            })

            taskDescription.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    task.taskDescription = s.toString()
                }
            })
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.onChangeTask(task)
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

    override fun onChanged(task: TaskDataModel) {
        viewModel.onChangeTask(task)
    }

    override fun onDelete(task: TaskDataModel) {
        viewModel.onDeleteTask(task)
    }

}