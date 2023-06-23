package com.example.google_task_project_dereza.ui.MainPage.MainPageFragment

import androidx.fragment.app.Fragment
import com.example.google_task_project_dereza.ui.MainPage.ListFragment.ListFragment
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val ITEM_COUNT = 3

class PagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return ListFragment.newInstance(position)
    }
}