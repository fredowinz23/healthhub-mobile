package com.capstone.healthhubnurse.api

import com.capstone.healthhubnurse.auth.LoginInfo
import com.capstone.healthhubnurse.models.Patient
import com.capstone.healthhubnurse.models.ProfileInfo
import com.capstone.healthhubnurse.requests.MedicalRecordFormOptionRequest
import com.capstone.healthhubnurse.requests.MedicalRecordListRequest
import com.capstone.healthhubnurse.requests.MonitoringListRequest
import com.capstone.healthhubnurse.requests.NewMedicalRecordRequest
import com.capstone.healthhubnurse.requests.PatientRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Headers


interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("api/login.php")
    fun loginUser(@Body loginInfo: LoginInfo): Call<LoginInfo>

    @Headers("Content-Type: application/json")
    @POST("api/profile.php")
    fun getProfile(@Body profileInfo: ProfileInfo): Call<ProfileInfo>

    @Headers("Content-Type: application/json")
    @POST("api/change-password.php")
    fun changePassword(@Body loginInfo: LoginInfo): Call<LoginInfo>

    @Headers("Content-Type: application/json")
    @POST("api/get-patient-list.php")
    fun getPatientList(@Body patientRequest: PatientRequest): Call<PatientRequest>

    @Headers("Content-Type: application/json")
    @POST("api/get-md-records.php")
    fun getMDRecords(@Body mdRequest: MedicalRecordListRequest): Call<MedicalRecordListRequest>

    @Headers("Content-Type: application/json")
    @POST("api/get-monitoring-list.php")
    fun getMonitoringList(@Body monitoringListRequest: MonitoringListRequest): Call<MonitoringListRequest>

    @Headers("Content-Type: application/json")
    @POST("api/submit-patient-form.php")
    fun submitPatientForm(@Body patient: Patient): Call<Patient>

    @Headers("Content-Type: application/json")
    @POST("api/submit-md-form.php")
    fun submitMdForm(@Body newMedicalRecordRequest: NewMedicalRecordRequest): Call<NewMedicalRecordRequest>

    @Headers("Content-Type: application/json")
    @POST("api/get-md-form-options.php")
    fun getMdFormOptions(@Body medicalRecordFormOptionRequest: MedicalRecordFormOptionRequest): Call<MedicalRecordFormOptionRequest>


}