package com.capstone.healthhubnurse.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.healthhubnurse.PatientListActivity
import com.capstone.healthhubnurse.api.ApiInterface
import com.capstone.healthhubnurse.api.RetrofitClient
import com.capstone.healthhubnurse.api.UserSession
import com.capstone.healthhubnurse.databinding.ActivityMedicalRecordsBinding
import retrofit2.Call
import retrofit2.Response

class MedicalRecordsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMedicalRecordsBinding
    var patientId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMedicalRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        getMedicalRecords(patientId)

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnNewMedicalRecord.setOnClickListener{
            val intent = Intent(this, MedicalRecordFormActivity::class.java)
            intent.putExtra("patientId", patientId)
            startActivity(intent)

        }

    }

    private fun getMedicalRecords(patientId: Int) {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        val userSession = UserSession(this)
        val mdRequest = MedicalRecordRequest(patientId)
        val call = retrofitAPI.getMDRecords(mdRequest)

        call.enqueue(object : retrofit2.Callback<MedicalRecordRequest?> {
            override fun onResponse(call: Call<MedicalRecordRequest?>, response: Response<MedicalRecordRequest?>) {
                binding.progressBar.visibility = View.GONE
                val responseFromAPI: MedicalRecordRequest? = response.body()

                val groupLinear = LinearLayoutManager(this@MedicalRecordsActivity)
                binding.rvMDList.layoutManager = groupLinear
                val data = responseFromAPI?.md_list!!

                val adapter = MedicalRecordAdapter(this@MedicalRecordsActivity, data)
                binding.rvMDList.adapter = adapter

                }

            override fun onFailure(call: Call<MedicalRecordRequest?>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Log.e("Login Error", t.message.toString())
                Toast.makeText(
                    this@MedicalRecordsActivity,
                    "Internet Connection Error",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}