package com.example.google_task_project_dereza.ui.MainPage.MainPageFragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.google_task_project_dereza.databinding.DialogAddTaskBinding

class AddTaskDialog:DialogFragment() {

    private var _bundle: DialogAddTaskBinding? = null
    private val bundle get()= _bundle!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bundle = DialogAddTaskBinding.inflate(inflater, container, false)



        return bundle.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }



    override fun onDetach() {
        super.onDetach()
        _bundle = null
    }

    companion object{
        fun newInstance():AddTaskDialog{
            return AddTaskDialog()
        }
    }
}