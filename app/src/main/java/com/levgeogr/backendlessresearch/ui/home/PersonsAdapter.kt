package com.levgeogr.backendlessresearch.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.levgeogr.backendlessresearch.R
import com.levgeogr.backendlessresearch.api.models.Person
import kotlinx.android.synthetic.main.person_item_list.view.*

class PersonsAdapter : RecyclerView.Adapter<PersonsAdapter.VH>() {

    private val items = arrayListOf<Person>()

    fun setList(persons: List<Person>) {
        this.items.clear()
        this.items.addAll(persons)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.person_item_list, parent, false)

        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(person: Person) {
            itemView.person_name_item.text = person.name
            itemView.person_age_item.text = person.age.toString()

        }
    }
}