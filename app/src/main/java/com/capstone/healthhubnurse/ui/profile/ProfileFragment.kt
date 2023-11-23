package com.capstone.healthhubnurse.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.healthhubnurse.MainActivity
import com.capstone.healthhubnurse.databinding.FragmentProfileBinding
import com.capstone.healthhubnurse.api.UserSession
import com.capstone.healthhubnurse.auth.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val userSession = UserSession(context)

        binding.llLogout.setOnClickListener {
            userSession!!.edit?.clear()
            userSession!!.edit?.apply()
            Log.e("Activity reached", "logout")
            startActivity(Intent(context, LoginActivity::class.java))
            (context as MainActivity).finish()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}