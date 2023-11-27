package com.capstone.healthhubnurse

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
import com.capstone.healthhubnurse.databinding.ActivityMonitoringBinding
import com.capstone.healthhubnurse.requests.MedicalRecordListRequest
import com.capstone.healthhubnurse.requests.MonitoringListRequest
import retrofit2.Call
import retrofit2.Response

class MonitoringListActivity : AppCompatActivity() {

    lateinit var binding: ActivityMonitoringBinding
    var mrId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMonitoringBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        mrId = intent.extras?.getInt("mrId", 0)!!

        getMonitoringList(mrId)

        binding.ivBack.setOnClickListener {
            finish()
        }

//        binding.btnNewMedicalRecord.setOnClickListener{
//            val intent = Intent(this, MedicalRecordFormActivity::class.java)
//            intent.putExtra("mdId", mdId)
//            startActivity(intent)
//
//        }

    }

    override fun onResume() {
        super.onResume()
        getMonitoringList(mrId)
    }

    private fun getMonitoringList(mrId: Int) {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        val apiRequest = MonitoringListRequest(mrId)
        val call = retrofitAPI.getMonitoringList(apiRequest)

        call.enqueue(object : retrofit2.Callback<MonitoringListRequest?> {
            override fun onResponse(call: Call<MonitoringListRequest?>, response: Response<MonitoringListRequest?>) {
                binding.progressBar.visibility = View.GONE
                val responseFromAPI: MonitoringListRequest? = response.body()

                val groupLinear = LinearLayoutManager(this@MonitoringListActivity)
                binding.rvMDList.layoutManager = groupLinear
                val data = responseFromAPI?.monitoring_list!!

                val adapter = MonitoringAdapter(this@MonitoringListActivity, data)
                binding.rvMDList.adapter = adapter

                }

            override fun onFailure(call: Call<MonitoringListRequest?>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Log.e("Login Error", t.message.toString())
                Toast.makeText(
                    this@MonitoringListActivity,
                    "Internet Connection Error",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}