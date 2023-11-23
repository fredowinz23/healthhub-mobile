package com.capstone.healthhubnurse.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.healthhubnurse.NurseMainActivity
import com.capstone.healthhubnurse.PatientListActivity
import com.capstone.healthhubnurse.R
import com.capstone.healthhubnurse.models.MedicalRecord
import com.capstone.healthhubnurse.models.Patient

class MedicalRecordAdapter(private var context: Context?, private val mList: List<MedicalRecord>) : RecyclerView.Adapter<MedicalRecordAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medical_records, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]

        holder.reasonForAdmission.text = item.reasonForAdmission
//        holder.item.setOnClickListener {
//            val intent = Intent(context, MedicalRecordsActivity::class.java)
//            intent.putExtra("patientId", item.id)
//            (context as PatientListActivity).startActivity(intent)
//        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val reasonForAdmission: TextView = itemView.findViewById(R.id.tvReasonForAdmission)
        val item: LinearLayout = itemView.findViewById(R.id.llItem)
    }
}
