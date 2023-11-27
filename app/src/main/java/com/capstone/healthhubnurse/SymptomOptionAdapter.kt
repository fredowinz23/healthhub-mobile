package com.capstone.healthhubnurse

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.healthhubnurse.models.Patient
import com.capstone.healthhubnurse.models.Symptom
import com.capstone.healthhubnurse.models.User

class SymptomOptionAdapter(private var context: Context?, private val mList: List<Symptom>) : RecyclerView.Adapter<SymptomOptionAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_option, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]

        holder.name.text = item.name
        holder.item.setOnClickListener {
            val mdForm = context as MedicalRecordFormActivity
            mdForm.symptomId = item.id
            mdForm.binding.tvSymptoms.text = item.name
            mdForm.builder.dismiss()
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val item: LinearLayout = itemView.findViewById(R.id.llItem)
    }
}
