package com.levgeogr.backendlessresearch.ui.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.backendless.Backendless
import com.levgeogr.backendlessresearch.R
import kotlinx.android.synthetic.main.fragment_account.view.*

class AccountFragment : Fragment() {

    private val vm = AccountViewModel()

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_account, container, false)

        val currentUser = Backendless.UserService.CurrentUser()

        if (currentUser == null) {
            Log.d("Account Fragment", "No current user")
        } else {
            Log.d(
                "Account Fragment",
                "User email = ${currentUser.email},\n userId = ${currentUser.userId}"
            )
            root.userName.text = currentUser.userId
            root.userEMail.text = currentUser.email
        }

        if (currentUser == null) {
            root.loginButton.setOnClickListener { view: View? ->
                view?.findNavController()?.navigate(R.id.loginFragment)
            }
        } else {
            root.loginButton.visibility = View.GONE
            root.logRegNotification.visibility = View.GONE
        }
        return root
    }
}