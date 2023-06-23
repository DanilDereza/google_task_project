package com.example.google_task_project_dereza.ui.MainPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.google_task_project_dereza.databinding.FragmentMainPageBinding

class MainPageFragment:Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }

    companion object{
        fun newInstance():MainPageFragment{
            return MainPageFragment()
        }
    }
}