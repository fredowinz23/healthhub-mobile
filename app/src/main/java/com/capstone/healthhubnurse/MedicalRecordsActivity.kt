package com.capstone.healthhubnurse

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.healthhubnurse.api.ApiInterface
import com.capstone.healthhubnurse.api.RetrofitClient
import com.capstone.healthhubnurse.api.UserSession
import com.capstone.healthhubnurse.databinding.ActivityMedicalRecordsBinding
import com.capstone.healthhubnurse.requests.MedicalRecordListRequest
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

        patientId = intent.extras?.getInt("patientId", 0)!!

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

    override fun onResume() {
        super.onResume()
        getMedicalRecords(patientId)
    }

    private fun getMedicalRecords(patientId: Int) {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        val userSession = UserSession(this)
        val mdRequest = MedicalRecordListRequest(patientId)
        val call = retrofitAPI.getMDRecords(mdRequest)

        call.enqueue(object : retrofit2.Callback<MedicalRecordListRequest?> {
            override fun onResponse(call: Call<MedicalRecordListRequest?>, response: Response<MedicalRecordListRequest?>) {
                binding.progressBar.visibility = View.GONE
                val responseFromAPI: MedicalRecordListRequest? = response.body()

                val groupLinear = LinearLayoutManager(this@MedicalRecordsActivity)
                binding.rvMDList.layoutManager = groupLinear
                val data = responseFromAPI?.md_list!!

                val adapter = MedicalRecordAdapter(this@MedicalRecordsActivity, data)
                binding.rvMDList.adapter = adapter

                }

            override fun onFailure(call: Call<MedicalRecordListRequest?>, t: Throwable) {
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