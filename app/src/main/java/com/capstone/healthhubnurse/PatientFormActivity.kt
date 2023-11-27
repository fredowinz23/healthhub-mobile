package com.capstone.healthhubnurse

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.capstone.healthhubnurse.api.ApiInterface
import com.capstone.healthhubnurse.api.RetrofitClient
import com.capstone.healthhubnurse.databinding.ActivityPatientFormBinding
import com.capstone.healthhubnurse.models.Patient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PatientFormActivity : AppCompatActivity() {

    lateinit var binding: ActivityPatientFormBinding
    var dob = ""
    var gender = ""
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
        
        binding.rgGender.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            gender = radio.text.toString()
        }


        binding.btnSubmit.setOnClickListener {
            if (binding.etFullName.text.isEmpty() ||
                dob=="" ||
                gender=="" ||
                binding.etAddress.text.isEmpty() ||
                binding.etCity.text.isEmpty() ||
                binding.etPhone.text.isEmpty() ||
                binding.etEmail.text.isEmpty() ||
                binding.etEcName.text.isEmpty() ||
                binding.etEcPhoneNumber.text.isEmpty() ||
                binding.etEcRelationship.text.isEmpty()
                ){
                    Toast.makeText(
                    this@PatientFormActivity,
                    "Fields Must not be empty",
                    Toast.LENGTH_LONG
                ).show()
            }
            else{
                submitRequest(
                    binding.etFullName.text.toString(),
                    dob,
                    gender,
                    binding.etAddress.text.toString(),
                    binding.etCity.text.toString(),
                    binding.etPhone.text.toString(),
                    binding.etEmail.text.toString(),
                    binding.etEcName.text.toString(),
                    binding.etEcPhoneNumber.text.toString(),
                    binding.etEcRelationship.text.toString()
                )
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

    private fun submitRequest(
        fullName: String,
        dob: String,
        gender: String,
        address: String,
        city: String,
        phone: String,
        email: String,
        ecName: String,
        ecPhoneNumber: String,
        ecRelationship: String
    ) {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        binding.btnSubmit.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        val patient = Patient(0, fullName, dob, gender, address, city, phone, email, ecName, ecPhoneNumber, ecRelationship)
        val call = retrofitAPI.submitPatientForm(patient)

        call.enqueue(object : Callback<Patient?> {
            override fun onResponse(call: Call<Patient?>, response: Response<Patient?>) {

                val responseFromAPI: Patient? = response.body()

                finish()
            }

            override fun onFailure(call: Call<Patient?>, t: Throwable) {

                Toast.makeText(
                    this@PatientFormActivity,
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