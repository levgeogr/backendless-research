package com.levgeogr.backendlessresearch.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.levgeogr.backendlessresearch.R
import kotlinx.android.synthetic.main.add_person_form.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private var homeViewModel = HomeViewModel()

    private lateinit var addPersonForm: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        addPersonForm = BottomSheetDialog(requireContext())
        addPersonForm.setContentView(
            LayoutInflater.from(context).inflate(R.layout.add_person_form, null)
        )

        root.floatingActionButton.setOnClickListener { addPersonForm.show() }

        addPersonForm.done_button.setOnClickListener {
            val name = addPersonForm.name_et.text.toString()
            val ageString = addPersonForm.age_et.text.toString()
            val age = if (ageString.isEmpty()) 0 else ageString.toInt()
            homeViewModel.addUser(name, age)
            addPersonForm.hide()
        }


        return root
    }
}