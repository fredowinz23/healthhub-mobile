//package com.capstone.healthhubnurse.ui.main
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.capstone.healthhubnurse.R
//import com.capstone.healthhubnurse.models.LeaveType
//
//class LeaveTypeAdapter(private var context: Context?, private val mList: List<LeaveType>) : RecyclerView.Adapter<LeaveTypeAdapter.ViewHolder>() {
//
//    // create new views
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        // inflates the card_view_design view
//        // that is used to hold list item
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_leave_type_list, parent, false)
//
//        return ViewHolder(view)
//    }
//
//    // binds the list items to a view
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        val item = mList[position]
//
//        holder.name.text = item.name
//        holder.description.text = item.description
//        holder.leaveItem.setOnClickListener {
//            val leaveForm = context as NewPatientFormActivity
//            leaveForm.leaveTypeId = item.id
//            leaveForm.leaveTypeName = item.name
//            leaveForm.binding.tvLeaveType.text = item.name
//            leaveForm.builder.dismiss()
//        }
//    }
//
//    // return the number of the items in the list
//    override fun getItemCount(): Int {
//        return mList.size
//    }
//
//    // Holds the views for adding it to image and text
//    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
//        val name: TextView = itemView.findViewById(R.id.tvName)
//        val description: TextView = itemView.findViewById(R.id.tvDescription)
//        val leaveItem: LinearLayout = itemView.findViewById(R.id.llItem)
//    }
//}
