package org.srnd.workshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val cities = mutableListOf(
        "San Diego",
        "Seattle",
        "New York City",
        "Cupertino",
        "San Francisco"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewManager = LinearLayoutManager(this)
        viewManager.orientation = RecyclerView.VERTICAL

        val listAdapter = CityListAdapter(cities)

        recycler.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, viewManager.orientation))
            layoutManager = viewManager
            adapter = listAdapter
        }

        addCityButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Add a city")

            val editText = EditText(this)
            builder.setView(editText)

            builder.setPositiveButton("Add") { _, _ ->
                cities.add(editText.text.toString())
                listAdapter.notifyDataSetChanged()
            }

            builder.setNegativeButton("Cancel", null)

            builder.show()
        }
    }
}
