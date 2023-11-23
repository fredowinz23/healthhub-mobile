//package com.capstone.healthhubnurse
//
//import android.annotation.SuppressLint
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatDelegate
//import com.capstone.healthhubnurse.api.ApiInterface
//import com.capstone.healthhubnurse.api.RetrofitClient
//import com.capstone.healthhubnurse.api.UserSession
//import com.capstone.healthhubnurse.databinding.ActivityMyProfileBinding
//import com.capstone.healthhubnurse.models.ProfileInfo
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MyProfileActivity : AppCompatActivity() {
//    lateinit var binding: ActivityMyProfileBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_my_profile)
//
//        binding = ActivityMyProfileBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//
//        binding.btnBack.setOnClickListener {
//            finish()
//        }
//        getProfile()
//    }
//
//    private fun getProfile() {
//        val retrofit = RetrofitClient.getInstance(this)
//        val retrofitAPI = retrofit.create(ApiInterface::class.java)
//
//        val userSession = UserSession(this)
//        val profile = ProfileInfo(userSession.username!!)
//        val call = retrofitAPI.getProfile(profile)
//
//        call.enqueue(object : Callback<ProfileInfo?> {
//            @SuppressLint("SetTextI18n")
//            override fun onResponse(call: Call<ProfileInfo?>, response: Response<ProfileInfo?>) {
//
//                // we are getting response from our body
//                // and passing it to our modal class.
//                val responseFromAPI: ProfileInfo? = response.body()
//
//                binding.tvFullName.text = "${responseFromAPI?.profile!!.firstName} ${responseFromAPI.profile!!.lastName}"
//                binding.tvUsername.text = responseFromAPI.profile!!.username
//                binding.tvPresent.text = responseFromAPI.present
//                binding.tvLate.text = responseFromAPI.late
//                binding.tvAbsent.text = responseFromAPI.absent
//                binding.tvEmail.text = responseFromAPI.profile!!.email
//                binding.tvPhone.text = responseFromAPI.profile!!.phone
//                binding.tvDepartment.text = responseFromAPI.profile!!.department
//            }
//
//            override fun onFailure(call: Call<ProfileInfo?>, t: Throwable) {
//                // setting text to our text view when
//                // we get error response from API.
//
//                Toast.makeText(
//                    this@MyProfileActivity,
//                    t.message.toString(),
//                    Toast.LENGTH_LONG
//                ).show()
//                Log.e("Login Error", t.message.toString())
//            }
//
//        })
//    }
//}