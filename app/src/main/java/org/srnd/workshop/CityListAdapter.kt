package org.srnd.workshop

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityListAdapter(private val cities: MutableList<String>) : RecyclerView.Adapter<CityListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false) as LinearLayout
        return ViewHolder(root)
    }

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = cities[position]

        holder.root.setOnClickListener {
            val builder = AlertDialog.Builder(holder.root.context)

            builder.setTitle("Delete this city?")
            builder.setMessage("Confirm that you want to delete ${cities[position]}.")

            builder.setPositiveButton("Delete") { _, _ ->
                cities.removeAt(position)
                notifyDataSetChanged()
            }

            builder.setNegativeButton("Cancel", null)

            builder.show()
        }
    }

    class ViewHolder(val root: LinearLayout) : RecyclerView.ViewHolder(root) {
        var textView: TextView = root.findViewById(R.id.cityName)
    }
}