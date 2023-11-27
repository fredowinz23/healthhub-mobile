package com.capstone.healthhubnurse

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.healthhubnurse.models.MedicalRecord

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

        holder.date.text = item.dateAdded
        holder.doctor.text = "Dr. ${item.doctor!!.firstName} ${item.doctor!!.lastName}"
        holder.reason.text = item.reasonForAdmission
        holder.item.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Alert!")
            builder.setMessage("Would you like to view monitoring detail?")
            builder.setPositiveButton("Yes") { dialog, which ->
                val intent = Intent(context, MonitoringListActivity::class.java)
                intent.putExtra("mrId", item.id)
                (context as MedicalRecordsActivity).startActivity(intent)
            }

            builder.setNegativeButton("No") { dialog, which ->
            }

            builder.show()
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val doctor: TextView = itemView.findViewById(R.id.tvDoctor)
        val reason: TextView = itemView.findViewById(R.id.tvReason)
        val date: TextView = itemView.findViewById(R.id.tvDate)
        val item: LinearLayout = itemView.findViewById(R.id.llItem)
    }
}
