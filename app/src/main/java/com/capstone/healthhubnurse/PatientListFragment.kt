package com.capstone.healthhubnurse

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.healthhubnurse.api.ApiInterface
import com.capstone.healthhubnurse.api.RetrofitClient
import com.capstone.healthhubnurse.api.UserSession
import com.capstone.healthhubnurse.databinding.FragmentPatientListBinding
import com.capstone.healthhubnurse.requests.PatientRequest
import retrofit2.Call
import retrofit2.Response

class PatientListFragment(private val position: Int) : Fragment() {
    private var _binding: FragmentPatientListBinding? = null

    private val binding get() = _binding!!

    private var status = "Admitted"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPatientListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        status = when (position) {
            0 -> "Admitted"
            1 -> "Discharged"
            else -> "Admitted"
        }

        getPatientList(status)

        return root
    }

    private fun getPatientList(status: String) {
        val retrofit = RetrofitClient.getInstance(context)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        val userSession = UserSession(context)
        val patientRequest = PatientRequest(userSession.username!!, status)
        val call = retrofitAPI.getPatientList(patientRequest)

        call.enqueue(object : retrofit2.Callback<PatientRequest?> {
            override fun onResponse(call: Call<PatientRequest?>, response: Response<PatientRequest?>) {

                binding.progressBar.visibility = View.GONE

                val responseFromAPI: PatientRequest? = response.body()

                val groupLinear = LinearLayoutManager(context)
                binding.rvStoreList.layoutManager = groupLinear
                val data = responseFromAPI?.patient_list!!

                val adapter = PatientAdapter(context, data)
                binding.rvStoreList.adapter = adapter
            }

            override fun onFailure(call: Call<PatientRequest?>, t: Throwable) {

                binding.progressBar.visibility = View.GONE

                Log.e("Login Error", t.message.toString())

                Toast.makeText(
                    context,
                    "Internet Connection Error",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    override fun onResume() {
        getPatientList(status)
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}