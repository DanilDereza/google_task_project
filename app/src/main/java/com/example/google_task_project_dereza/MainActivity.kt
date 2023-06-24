package com.example.google_task_project_dereza

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.google_task_project_dereza.ui.MainPage.MainPageFragment.MainPageFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null){
            val fragment = MainPageFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit()
        }
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
