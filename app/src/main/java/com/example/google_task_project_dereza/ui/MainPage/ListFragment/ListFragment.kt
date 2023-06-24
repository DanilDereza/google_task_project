package com.example.google_task_project_dereza.ui.MainPage.ListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.google_task_project_dereza.databinding.FragmentListBinding
import java.util.UUID

class ListFragment:Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListFragmentViewModel by lazy {
        ViewModelProvider(this)[ListFragmentViewModel::class.java]
    }

    private val adapter: ListAdapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        when(requireArguments().getInt(TASKS_TYPE)){
            0 -> viewModel.favoriteTask.observe(viewLifecycleOwner){
                adapter.updateData(it)
            }
            1 -> viewModel.allTasks.observe(viewLifecycleOwner){
                adapter.updateData(it)
            }
            2->viewModel.completedTask.observe(viewLifecycleOwner){
                adapter.updateData(it)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }

    companion object{

        private const val TASKS_TYPE = "taskType"

        fun newInstance(type: Int):ListFragment{
            val arg = bundleOf(
                TASKS_TYPE to type
            )

            return ListFragment().apply {
                arguments = arg
            }
        }
    }
}