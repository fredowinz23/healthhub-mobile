package com.capstone.healthhubnurse.ui.main

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.healthhubnurse.R
import com.capstone.healthhubnurse.api.ApiInterface
import com.capstone.healthhubnurse.api.RetrofitClient
import com.capstone.healthhubnurse.api.UserSession
import com.capstone.healthhubnurse.databinding.ActivityPatientFormBinding
import com.capstone.healthhubnurse.models.Patient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class NewPatientFormActivity : AppCompatActivity() {

    lateinit var binding: ActivityPatientFormBinding
    var dob = ""
    lateinit var builder: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatientFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.ivBack.setOnClickListener {
            finish()
        }


        val myCalendar = Calendar.getInstance()
        val startDateListener = datePickerListener(binding.tvDob)

        binding.llDob.setOnClickListener {
            DatePickerDialog(this, startDateListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }


        binding.btnSubmit.setOnClickListener {
            if (binding.etFullName.text.isEmpty() || dob=="" || binding.etAddress.text.isEmpty()){
                    Toast.makeText(
                    this@NewPatientFormActivity,
                    "Fields Must not be empty",
                    Toast.LENGTH_LONG
                ).show()
            }
            else{
                submitRequest(binding.etFullName.text.toString(), dob, binding.etAddress.text.toString())
            }
        }
    }

    private fun datePickerListener(dateInput: TextView): DatePickerDialog.OnDateSetListener {
        val myCalendar = Calendar.getInstance()
        return DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            dateInput.text = year.toString() +"-"+ (monthOfYear+1).toString() +"-"+ dayOfMonth.toString()
            dob = dateInput.text.toString()
        }
    }

    private fun submitRequest(fullName: String, dob: String, address: String) {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        binding.btnSubmit.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        val userSession = UserSession(this)
        val patient = Patient(0, fullName, dob, "", address)
        val call = retrofitAPI.submitPatientForm(patient)

        call.enqueue(object : Callback<Patient?> {
            override fun onResponse(call: Call<Patient?>, response: Response<Patient?>) {

                val responseFromAPI: Patient? = response.body()

                finish()
            }

            override fun onFailure(call: Call<Patient?>, t: Throwable) {

                Toast.makeText(
                    this@NewPatientFormActivity,
                    "Internet Connection Error",
                    Toast.LENGTH_LONG
                ).show()

                binding.progressBar.visibility = View.GONE
                binding.btnSubmit.visibility = View.VISIBLE
                Log.e("Login Error", t.message.toString())
            }

        })

    }

}