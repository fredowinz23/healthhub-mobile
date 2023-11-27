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
import com.capstone.healthhubnurse.models.FollowUp
import com.capstone.healthhubnurse.models.MedicalRecord

class MonitoringAdapter(private var context: Context?, private val mList: List<FollowUp>) : RecyclerView.Adapter<MonitoringAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_monitoring, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]

        holder.date.text = item.dateAdded
        holder.temperature.text = item.temperature
        holder.bloodPressure.text = item.bloodPressure
        holder.respiratoryRate.text = item.respiratoryRate
        holder.oxygen.text = item.oxygen
        holder.cardiacRate.text = item.cardiacRate
        holder.medications.text = item.medications
        holder.observations.text = item.observations
        holder.recommendations.text = item.recommendations
        holder.monitoredBy.text = "${item.monitoredBy!!.firstName} ${item.monitoredBy!!.lastName}"
//        holder.item.setOnClickListener {
//            val builder = AlertDialog.Builder(context)
//            builder.setTitle("Alert!")
//            builder.setMessage("Would you like to view monitoring detail?")
//            builder.setPositiveButton("Yes") { dialog, which ->
//                val intent = Intent(context, MonitoringListActivity::class.java)
//                intent.putExtra("mrId", item.id)
//                (context as PatientListActivity).startActivity(intent)
//            }
//
//            builder.setNegativeButton("No") { dialog, which ->
//            }
//
//            builder.show()
//        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val respiratoryRate: TextView = itemView.findViewById(R.id.tvRespiratoryRate)
        val oxygen: TextView = itemView.findViewById(R.id.tvOxygen)
        val cardiacRate: TextView = itemView.findViewById(R.id.tvCardiacRate)
        val medications: TextView = itemView.findViewById(R.id.tvMedications)
        val observations: TextView = itemView.findViewById(R.id.tvObservations)
        val recommendations: TextView = itemView.findViewById(R.id.tvRecommendations)
        val monitoredBy: TextView = itemView.findViewById(R.id.tvMonitoredBy)
        val bloodPressure: TextView = itemView.findViewById(R.id.tvBloodPressure)
        val temperature: TextView = itemView.findViewById(R.id.tvTemperature)
        val date: TextView = itemView.findViewById(R.id.tvDate)
        val item: LinearLayout = itemView.findViewById(R.id.llItem)
    }
}
