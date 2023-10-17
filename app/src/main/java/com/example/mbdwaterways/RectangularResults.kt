package com.example.mbdwaterways

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RectangularResults : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_rectangular_results)

        val rect_h_plain = findViewById<TextView>(R.id.h_static)
        val rect_b_plain = findViewById<TextView>(R.id.b_static)

        val rect_Q_plain = findViewById<TextView>(R.id.q_static)
        val rect_n_plain = findViewById<TextView>(R.id.n_static)

        //buttons
        val area_button = findViewById<Button>(R.id.rectangular_area)
        val wet_perimeter_button = findViewById<Button>(R.id.rectangular_wet_perimeter)
        val hidraulic_radious_button = findViewById<Button>(R.id.hidraulic_radious_button)
        val water_mirror_button = findViewById<Button>(R.id.water_mirror_button)

        //data from rectangular fragment
        rect_h_plain.text = intent.getStringExtra("rect_h")
        rect_b_plain.text = intent.getStringExtra("rect_b")
        rect_Q_plain.text = intent.getStringExtra("rect_Q")
        rect_n_plain.text = intent.getStringExtra("rect_n")

        //linear layouts
        val rectangular_area_layout = findViewById<LinearLayout>(R.id.rectangular_area_layout)
        val rectangular_area_layout_params : ViewGroup.LayoutParams = rectangular_area_layout.layoutParams

        val wet_perimeter_layout = findViewById<LinearLayout>(R.id.rectangular_wet_perimeter_layout)
        val wet_perimeter_layout_params : ViewGroup.LayoutParams = wet_perimeter_layout.layoutParams

        val rectangular_hidraulic_radious_layout = findViewById<LinearLayout>(R.id.rectangular_hidraulic_radious_layout)
        val rectangular_hidraulic_radious_layout_params : ViewGroup.LayoutParams = rectangular_hidraulic_radious_layout.layoutParams

        val rectangular_water_mirror_layout = findViewById<LinearLayout>(R.id.rectangular_water_mirror_layout)
        val rectangular_water_mirror_layout_params : ViewGroup.LayoutParams = rectangular_water_mirror_layout.layoutParams

        //main variables
        val h = Integer.parseInt(rect_h_plain.text.toString())
        val b = Integer.parseInt(rect_b_plain.text.toString())

        val bText = rect_b_plain.text.toString()
        val hText = rect_h_plain.text.toString()

        //rectangular_area_results
        val result_area = h*b

        //rectangular_wet_perimeter_results
        val result_wet_perimeter = b + (2 * h)

        //rectangular_hidraulic_radious_results
        val result_hidraulic_radious = (b*h)/(b+ (2*h))



        // results text views
        val middle_area_result = findViewById<TextView>(R.id.middle_area_result)
        val final_area_result = findViewById<TextView>(R.id.final_area_result)

        val middle_wet_perimeter_result = findViewById<TextView>(R.id.middle_wet_perimeter_result)
        val final_wet_perimeter_result = findViewById<TextView>(R.id.final_wet_perimeter_result)

        val middle_hidraulic_radious_result = findViewById<TextView>(R.id.middle_hidraulic_radious_result)
        val penultimate_hidraulic_radious_result = findViewById<TextView>(R.id.penultimate_hidraulic_radious_result)
        val final_hidraulic_radious_result = findViewById<TextView>(R.id.final_hidraulic_radious_result)

        val final_water_mirror_result = findViewById<TextView>(R.id.final_water_mirror_result)

        //results strings
            // area
            middle_area_result.text = "(" + bText + " m)(" + hText + " m)"
            final_area_result.text = result_area.toString() + " m^2"
            // wet perimeter
            middle_wet_perimeter_result.text = bText + " m + " + "2(" + hText + " m)"
            final_wet_perimeter_result.text = result_wet_perimeter.toString() + " m"
            //hidraulic radious
            middle_hidraulic_radious_result.text = "(" + bText + "m) (" + hText + "m) / (" + bText + "m + 2(" + hText + "m))"
            final_hidraulic_radious_result.text = result_hidraulic_radious.toString() + "m"
            penultimate_hidraulic_radious_result.text = "(" + (b*h).toString() + "m) / (" + (b + (2*h)).toString() + "m)"
            //water mirror
            final_water_mirror_result.text = bText.toString() + " m"


        area_button.setOnClickListener{
            if(rectangular_area_layout_params.height == 0){
                rectangular_area_layout_params.height = 500
                rectangular_area_layout.layoutParams = rectangular_area_layout_params
            }
            else{
                rectangular_area_layout_params.height = 0
                rectangular_area_layout.layoutParams = rectangular_area_layout_params
            }
        }

        wet_perimeter_button.setOnClickListener{
            if(wet_perimeter_layout_params.height == 0){
                wet_perimeter_layout_params.height = 500
                wet_perimeter_layout.layoutParams = wet_perimeter_layout_params
            }
            else{
                wet_perimeter_layout_params.height = 0
                wet_perimeter_layout.layoutParams = wet_perimeter_layout_params
            }
        }

        hidraulic_radious_button.setOnClickListener{
            if(rectangular_hidraulic_radious_layout_params.height == 0){
                rectangular_hidraulic_radious_layout_params.height = 600
                rectangular_hidraulic_radious_layout.layoutParams = rectangular_hidraulic_radious_layout_params
            }
            else{
                rectangular_hidraulic_radious_layout_params.height = 0
                rectangular_hidraulic_radious_layout.layoutParams = rectangular_hidraulic_radious_layout_params
            }
        }

        water_mirror_button.setOnClickListener{
            if(rectangular_water_mirror_layout_params.height == 0){
                rectangular_water_mirror_layout_params.height = 350
                rectangular_water_mirror_layout.layoutParams = rectangular_water_mirror_layout_params
            }
            else{
                rectangular_water_mirror_layout_params.height = 0
                rectangular_water_mirror_layout.layoutParams = rectangular_water_mirror_layout_params
            }
        }


    }

}