package com.example.google_task_project_dereza.ui.MainPage.MainPageFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.google_task_project_dereza.data.models.TaskDataModel
import com.example.google_task_project_dereza.databinding.FragmentMainPageBinding
import com.google.android.material.tabs.TabLayout

private const val REQUEST_ADD_TASK = 0
private const val DIALOG_ADD_TASK = "addTaskDialog"

class MainPageFragment:Fragment(), AddTaskListener {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainPageFragmentViewModel by lazy {
        ViewModelProvider(this)[MainPageFragmentViewModel::class.java]
    }

    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagerAdapter = PagerAdapter(this)

        binding.viewPager.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tabLayout.getTabAt(position)!!.select()
                }
            })
        }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.addButton.setOnClickListener{
            AddTaskDialog(this).apply {
                setTargetFragment(this@MainPageFragment, REQUEST_ADD_TASK)
                show(this@MainPageFragment.requireFragmentManager(), DIALOG_ADD_TASK)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }

    companion object{
        fun newInstance(): MainPageFragment {
            return MainPageFragment()
        }
    }

    override fun addTask(task: TaskDataModel) {
        viewModel.addNewTask(task)
    }
}