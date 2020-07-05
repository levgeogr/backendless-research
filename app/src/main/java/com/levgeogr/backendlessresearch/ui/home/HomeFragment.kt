package com.levgeogr.backendlessresearch.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.levgeogr.backendlessresearch.R
import com.levgeogr.backendlessresearch.api.models.Person
import com.levgeogr.backendlessresearch.utils.DoneButtonStates
import kotlinx.android.synthetic.main.add_person_form.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), PersonClickListener {

    private var vm = PersonsViewModel()
    private var adapter = PersonsAdapter(this)
    private var doneButtonState = DoneButtonStates.CREATE

    private lateinit var root: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    private lateinit var addPersonForm: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)

        initViews()

        return root
    }

    override fun personClicked(person: Person) {
        doneButtonState = DoneButtonStates.UPDATE
        vm.selectedPerson = person
        addPersonForm.name_et.setText(person.name)
        addPersonForm.age_et.setText(person.age.toString())
        addPersonForm.show()
    }

    override fun personDelete(person: Person) {
        vm.deletePerson(person)
    }

    private fun doneButtonHandler() {
        val name = addPersonForm.name_et.text.toString()
        val ageString = addPersonForm.age_et.text.toString()
        val age = if (ageString.isEmpty()) 0 else ageString.toInt()

        when (doneButtonState) {
            DoneButtonStates.CREATE -> {
                vm.addPerson(name, age)
            }
            DoneButtonStates.UPDATE -> {
                vm.updatePerson(name, age)
            }
        }
        addPersonForm.hide()
        addPersonForm.name_et.setText("")
        addPersonForm.age_et.setText("")
    }

    private fun initViews() {
        recyclerView = root.recyclerView
        fab = root.floatingActionButton

        addPersonForm = BottomSheetDialog(requireContext())
        addPersonForm.setContentView(
            LayoutInflater.from(context).inflate(R.layout.add_person_form, null)
        )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fab.visibility == View.VISIBLE) {
                    fab.hide()
                } else if (dy < 0 && fab.visibility != View.VISIBLE) {
                    fab.show()
                }
            }
        })
        vm.persons.observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
        })

        root.swipeRefresh.setOnRefreshListener { vm.load() }
        vm.isRefreshing.observe(
            viewLifecycleOwner,
            Observer { root.swipeRefresh.isRefreshing = it })

        root.floatingActionButton.setOnClickListener {
            doneButtonState = DoneButtonStates.CREATE
            addPersonForm.show()
        }

        addPersonForm.done_button.setOnClickListener { doneButtonHandler() }

    }
}