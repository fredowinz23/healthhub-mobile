package com.capstone.healthhubnurse

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.healthhubnurse.api.ApiInterface
import com.capstone.healthhubnurse.api.RetrofitClient
import com.capstone.healthhubnurse.databinding.ActivityMedicalRecordFormBinding
import com.capstone.healthhubnurse.models.Patient
import com.capstone.healthhubnurse.models.Symptom
import com.capstone.healthhubnurse.models.User
import com.capstone.healthhubnurse.requests.MedicalRecordFormOptionRequest
import com.capstone.healthhubnurse.requests.NewMedicalRecordRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedicalRecordFormActivity : AppCompatActivity() {

    lateinit var binding: ActivityMedicalRecordFormBinding
    lateinit var builder: Dialog
    var doctorList: List<User>? = null
    var symptomList: List<Symptom>? = null
    var doctorId = 0
    var symptomId = 0
    var patientId = 0
    var bloodType = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMedicalRecordFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        patientId = intent.extras?.getInt("patientId", 0)!!

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.llDoctor.setOnClickListener {
            openDoctorDialog()
        }

        binding.llSymptoms.setOnClickListener {
            openSymptomDialog()
        }

        binding.rgBloodType.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            bloodType = radio.text.toString()
//            Toast.makeText(
//                this@MedicalRecordFormActivity,
//                bloodType,
//                Toast.LENGTH_LONG
//            ).show()
        }

        binding.btnSubmit.setOnClickListener {
            if (doctorId==0 ||
                binding.etReason.text.isEmpty() ||
                binding.etAllergies.text.isEmpty() ||
                binding.etMedication.text.isEmpty() ||
                bloodType=="" ||
                symptomId==0 ||
                binding.etDoctorsOrder.text.isEmpty()
            ){
                Toast.makeText(
                    this@MedicalRecordFormActivity,
//                    "Fields Must not be empty: $doctorId, $bloodType, $symptomId, ${binding.etReason.text} , ${binding.etAllergies.text} , ${binding.etMedication.text} , ${binding.etDoctorsOrder.text}",
                    "Fields Must not be empty",
                    Toast.LENGTH_LONG
                ).show()
            }
            else{
                submitRequest(
                    NewMedicalRecordRequest(
                        patientId,
                        doctorId,
                        binding.etReason.text.toString(),
                        binding.etAllergies.text.toString(),
                        binding.etMedication.text.toString(),
                        bloodType,
                        symptomId,
                        binding.etDoctorsOrder.text.toString()
                    )
                )
            }
        }
        
        getOptions()
    }

    private fun submitRequest(mdRequest: NewMedicalRecordRequest) {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        binding.btnSubmit.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        val call = retrofitAPI.submitMdForm(mdRequest)

        call.enqueue(object : Callback<NewMedicalRecordRequest?> {
            override fun onResponse(call: Call<NewMedicalRecordRequest?>, response: Response<NewMedicalRecordRequest?>) {

                val responseFromAPI: NewMedicalRecordRequest? = response.body()

                finish()
            }

            override fun onFailure(call: Call<NewMedicalRecordRequest?>, t: Throwable) {

                Toast.makeText(
                    this@MedicalRecordFormActivity,
                    "Internet Connection Error",
                    Toast.LENGTH_LONG
                ).show()

                binding.progressBar.visibility = View.GONE
                binding.btnSubmit.visibility = View.VISIBLE
                Log.e("Login Error", t.message.toString())
            }

        })
    }

    private fun openSymptomDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_option, null)
        val itemList = view.findViewById(R.id.rvList) as RecyclerView
        builder = Dialog(this)

        builder.setContentView(view)

        builder.show()
        builder.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val groupLinear = LinearLayoutManager(this@MedicalRecordFormActivity)
        itemList.layoutManager = groupLinear
        val adapter = SymptomOptionAdapter(this@MedicalRecordFormActivity, symptomList!!)
        itemList.adapter = adapter
    }

    private fun openDoctorDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_option, null)
        val itemList = view.findViewById(R.id.rvList) as RecyclerView
        builder = Dialog(this)

        builder.setContentView(view)

        builder.show()
        builder.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val groupLinear = LinearLayoutManager(this@MedicalRecordFormActivity)
        itemList.layoutManager = groupLinear
        val adapter = DoctorOptionAdapter(this@MedicalRecordFormActivity, doctorList!!)
        itemList.adapter = adapter
    }

    private fun getOptions() {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        val apiRequest = MedicalRecordFormOptionRequest()
        val call = retrofitAPI.getMdFormOptions(apiRequest)

        call.enqueue(object : Callback<MedicalRecordFormOptionRequest?> {
            override fun onResponse(call: Call<MedicalRecordFormOptionRequest?>, response: Response<MedicalRecordFormOptionRequest?>) {

                // we are getting response from our body
                // and passing it to our modal class.
                val responseFromAPI: MedicalRecordFormOptionRequest? = response.body()
                doctorList = responseFromAPI?.doctor_list
                symptomList = responseFromAPI?.symptom_list

            }

            override fun onFailure(call: Call<MedicalRecordFormOptionRequest?>, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.

                Toast.makeText(
                    this@MedicalRecordFormActivity,
                    t.message.toString(),
                    Toast.LENGTH_LONG
                ).show()

                Log.e("Login Error", t.message.toString())
            }

        })
    }
}