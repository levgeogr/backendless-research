package com.levgeogr.backendlessresearch.ui.account.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.levgeogr.backendlessresearch.R
import com.levgeogr.backendlessresearch.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment(), ILoginView {

    val vm = LoginVM(this)
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.apply {
            vm = this@LoginFragment.vm
            lifecycleOwner = this@LoginFragment
        }

        return binding.root
    }

    override fun incorrectForm() {
        binding.root.errorTextView.text = getString(R.string.fill_all_fields)
//        Toast.makeText(context, R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
    }

    override fun closeLoginView() {
        binding.root.errorTextView.text = ""
        findNavController().popBackStack()
    }

    override fun showErrors(error: String) {
        binding.root.errorTextView.text = error
    }
}