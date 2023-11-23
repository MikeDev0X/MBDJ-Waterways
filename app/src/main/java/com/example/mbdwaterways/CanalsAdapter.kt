package com.example.mbdwaterways
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView

class CanalsAdapter(private val context: Context, private val canals: Array<String>) : BaseAdapter(), SpinnerAdapter {
    override fun getCount(): Int {
        return canals.size
    }

    override fun getItem(position: Int): Any {
        return canals[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var v = convertView

        /*if (v == null) {
            val vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = vi.inflate(R.layout.your_custom_layout, null)
        }
        val tv = v?.findViewById<TextView>(R.id.yourTextViewFromYourLayout)
        if (tv != null) {
            tv.text = canals.getItem(position)
        }*/

        when (position) {
            0 -> {
                // set tv's color here...
            }
            1 -> {
                // set tv's color here...
            }
            else -> {
                // set default color or whatever...
            }
        }

        return v
    }
}
