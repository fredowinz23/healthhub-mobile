package com.capstone.healthhubnurse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.capstone.healthhubnurse.api.UserSession
import com.capstone.healthhubnurse.auth.LoginActivity
import com.capstone.healthhubnurse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        binding.cvPatientList.setOnClickListener {
            startActivity(Intent(this, PatientListActivity::class.java))
        }

        binding.cvAdmittedPatient.setOnClickListener {
            startActivity(Intent(this, PatientListActivity::class.java))
        }

        binding.cvPatientHistory.setOnClickListener {
            startActivity(Intent(this, PatientListActivity::class.java))
        }


        binding.cvLogout.setOnClickListener {
            val userSession = UserSession(this)
            userSession.edit?.clear()
            userSession.edit?.apply()
            Log.e("Activity reached", "logout")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}