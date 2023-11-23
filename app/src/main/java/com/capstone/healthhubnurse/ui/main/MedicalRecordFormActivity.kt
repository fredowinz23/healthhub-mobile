package com.capstone.healthhubnurse.ui.main

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.healthhubnurse.R
import com.capstone.healthhubnurse.api.ApiInterface
import com.capstone.healthhubnurse.api.RetrofitClient
import com.capstone.healthhubnurse.api.UserSession
import com.capstone.healthhubnurse.databinding.ActivityMedicalRecordFormBinding
import retrofit2.Call
import retrofit2.Response

class MedicalRecordFormActivity : AppCompatActivity() {

    lateinit var binding: ActivityMedicalRecordFormBinding
    val patientId = 0
    val doctorId = 0
    lateinit var builder: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMedicalRecordFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.llDoctor.setOnClickListener {
//            openDoctorDialog()
        }
    }

//    private fun openDoctorDialog(){
//        val view = layoutInflater.inflate(R.layout.dialog_leave_type, null)
//        val leaveTypeList = view.findViewById(R.id.rvleaveTypeList) as RecyclerView
//        builder = Dialog(this)
//
//        builder.setContentView(view)
////        dialogCancel.setOnClickListener {
////            builder.dismiss()
////        }
//        builder.show()
//        builder.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//
//        val retrofit = RetrofitClient.getInstance(this)
//        val retrofitAPI = retrofit.create(ApiInterface::class.java)
//
//        val userSession = UserSession(this)
//        val leaveInfo = LeaveTypeInfo(userSession.username!!)
//        val call = retrofitAPI.getLeaveTypeList(leaveInfo)
//
//        call.enqueue(object : retrofit2.Callback<LeaveTypeInfo?> {
//            override fun onResponse(call: Call<LeaveTypeInfo?>, response: Response<LeaveTypeInfo?>) {
//
//                val responseFromAPI: LeaveTypeInfo? = response.body()
//
//                val groupLinear = LinearLayoutManager(this@LeaveRequestFormActivity)
//                leaveTypeList.layoutManager = groupLinear
//                val data = responseFromAPI?.leave_list!!
//
//                val adapter = LeaveTypeAdapter(this@LeaveRequestFormActivity, data)
//                leaveTypeList.adapter = adapter
//            }
//
//            override fun onFailure(call: Call<LeaveTypeInfo?>, t: Throwable) {
//
//                Log.e("Login Error", t.message.toString())
//
//                Toast.makeText(
//                    this@LeaveRequestFormActivity,
//                    "Internet Connection Error",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//
//        })
//    }
}