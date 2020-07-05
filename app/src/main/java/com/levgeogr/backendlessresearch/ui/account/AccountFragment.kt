package com.levgeogr.backendlessresearch.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.levgeogr.backendlessresearch.R

class AccountFragment : Fragment() {

    private val vm = AccountViewModel()

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_account, container, false)

        return root
    }
}