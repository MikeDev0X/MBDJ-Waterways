package com.example.mbdwaterways

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RectangularResults : AppCompatActivity() {

    fun cropDecimals(s: String, n: Int): String {
        return String.format("%.${n}")
    }

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
        val critical_tension_button = findViewById<Button>(R.id.critical_tension_button)
        val velocity_button = findViewById<Button>(R.id.rectangular_velocity_button)
        val slope_button = findViewById<Button>(R.id.rectangular_slope_button)

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

        val rectangular_critical_tension_layout = findViewById<LinearLayout>(R.id.rectangular_critical_tension_layout)
        val rectangular_critical_tension_layout_params : ViewGroup.LayoutParams = rectangular_critical_tension_layout.layoutParams

        val rectangular_velocity_layout = findViewById<LinearLayout>(R.id.rectangular_velocity_layout)
        val rectangular_velocity_layout_params : ViewGroup.LayoutParams = rectangular_velocity_layout.layoutParams

        val rectangular_slope_layout = findViewById<LinearLayout>(R.id.rectangular_slope_layout)
        val rectangular_slope_layout_params : ViewGroup.LayoutParams = rectangular_slope_layout.layoutParams


        //main variables
        val h = (rect_h_plain.text.toString()).toFloat()
        val b = (rect_b_plain.text.toString()).toFloat()
        val Q = (rect_Q_plain.text.toString()).toFloat()
        val n = (rect_n_plain.text.toString()).toFloat()


        val bText = rect_b_plain.text.toString()
        val hText = rect_h_plain.text.toString()
        val QText = rect_Q_plain.text.toString()
        val nText = rect_n_plain.text.toString()


        //rectangular_area_results
        val result_area = h*b

        //rectangular_wet_perimeter_results
        val result_wet_perimeter = b + (2 * h)

        //rectangular_hidraulic_radious_results
        val result_hidraulic_radious = (b*h)/(b+ (2*h))

        //rectangular_critical_tension_results
        val result_critical_tension = Math.pow(((Math.pow(Q.toDouble(),2.0)) / (9.81 * b)),(0.333333))

        //rectangular_velocity_results
        val result_velocity = Q / (b*h)

        //rectangular_slope_results
        val result_slope = Math.pow(((Q * n) / ((b*h)*(Math.pow(((b*h) / (b + 2*h)).toDouble(),0.66666)))), 2.0)


        // results text views
        val middle_area_result = findViewById<TextView>(R.id.middle_area_result)
        val final_area_result = findViewById<TextView>(R.id.final_area_result)

        val middle_wet_perimeter_result = findViewById<TextView>(R.id.middle_wet_perimeter_result)
        val final_wet_perimeter_result = findViewById<TextView>(R.id.final_wet_perimeter_result)

        val middle_hidraulic_radious_result = findViewById<TextView>(R.id.middle_hidraulic_radious_result)
        val penultimate_hidraulic_radious_result = findViewById<TextView>(R.id.penultimate_hidraulic_radious_result)
        val final_hidraulic_radious_result = findViewById<TextView>(R.id.final_hidraulic_radious_result)

        val final_water_mirror_result = findViewById<TextView>(R.id.final_water_mirror_result)

        val second_critical_tension_result = findViewById<TextView>(R.id.second_critical_tension_result)
        val third_critical_tension_result = findViewById<TextView>(R.id.third_critical_tension_result)
        val penultimate_critical_tension_result = findViewById<TextView>(R.id.penultimate_critical_tension_result)
        val final_critical_tension_result = findViewById<TextView>(R.id.final_critical_tension_result)

        val middle_velocity_result = findViewById<TextView>(R.id.middle_velocity_result)
        val final_velocity_result = findViewById<TextView>(R.id.final_velocity_result)

        val middle_slope_result = findViewById<TextView>(R.id.middle_slope_result)
        val penultimate_slope_result = findViewById<TextView>(R.id.penultimate_slope_result)
        val final_slope_result = findViewById<TextView>(R.id.final_slope_result)


            //results strings
            // area
            middle_area_result.text = "(" + bText + " m)(" + hText + " m)"
            final_area_result.text = result_area.toString() + " m^2"
            // wet perimeter
            middle_wet_perimeter_result.text = bText + " m + " + "2(" + hText + " m)"
            final_wet_perimeter_result.text = result_wet_perimeter.toString() + " m"
            //hidraulic radious
            middle_hidraulic_radious_result.text = "(" + bText + "m) (" + hText + "m) / (" + bText + "m + 2(" + hText + "m))"
            penultimate_hidraulic_radious_result.text = "(" + (b*h).toString() + "m) / (" + (b + (2*h)).toString() + "m)"
            final_hidraulic_radious_result.text = result_hidraulic_radious.toString() + " m"
            //water mirror
            final_water_mirror_result.text = bText.toString() + " m"
            //critical tension
            second_critical_tension_result.text = "[" + QText + "^2 / (9.81)(" + bText + ") ] ^(1/3)"
            third_critical_tension_result.text = "[" + (Math.pow(Q.toDouble(), 2.0)) + " / (" + (9.81*b).toString() + ") ] ^(1/3)"
            penultimate_critical_tension_result.text = "[" + ((Math.pow(Q.toDouble(),2.0))/(9.81*b)).toString() + "] ^(1/3)"
            final_critical_tension_result.text =result_critical_tension.toString() + " m"
            //velocity
            middle_velocity_result.text = QText + " / " + (b*h).toString()
            final_velocity_result.text = result_velocity.toString() + " m/s"
            //slope
            middle_slope_result.text = "((" + QText + " * " + nText + ") / (" + (b*h).toString() + " * " + Math.pow(((b*h) / (b + 2*h)).toDouble(),0.66666).toString() + ") ^ 2"
            penultimate_slope_result.text = ((Q * n) / ((b*h)*(Math.pow(((b*h) / (b + 2*h)).toDouble(),0.66666)))).toString() + " ^2"
            final_slope_result.text = result_slope.toString() + " m"


        area_button.setOnClickListener{
            if(rectangular_area_layout_params.height == 0){
                rectangular_area_layout_params.height = 470
                rectangular_area_layout.layoutParams = rectangular_area_layout_params
            }
            else{
                rectangular_area_layout_params.height = 0
                rectangular_area_layout.layoutParams = rectangular_area_layout_params
            }
        }

        wet_perimeter_button.setOnClickListener{
            if(wet_perimeter_layout_params.height == 0){
                wet_perimeter_layout_params.height = 480
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
                rectangular_water_mirror_layout_params.height = 330
                rectangular_water_mirror_layout.layoutParams = rectangular_water_mirror_layout_params
            }
            else{
                rectangular_water_mirror_layout_params.height = 0
                rectangular_water_mirror_layout.layoutParams = rectangular_water_mirror_layout_params
            }
        }

        critical_tension_button.setOnClickListener{
            if(rectangular_critical_tension_layout.height == 0){
                rectangular_critical_tension_layout_params.height = 725
                rectangular_critical_tension_layout.layoutParams = rectangular_critical_tension_layout_params
            }
            else {
                rectangular_critical_tension_layout_params.height = 0
                rectangular_critical_tension_layout.layoutParams = rectangular_critical_tension_layout_params
            }
        }

        velocity_button.setOnClickListener{
            if(rectangular_velocity_layout.height == 0){
                rectangular_velocity_layout_params.height = 480
                rectangular_velocity_layout.layoutParams = rectangular_velocity_layout_params
            }
            else {
                rectangular_velocity_layout_params.height = 0
                rectangular_velocity_layout.layoutParams = rectangular_velocity_layout_params
            }
        }

        slope_button.setOnClickListener{
            if(rectangular_slope_layout.height == 0){
                rectangular_slope_layout_params.height = 725
                rectangular_slope_layout.layoutParams = rectangular_slope_layout_params
            }
            else {
                rectangular_slope_layout_params.height = 0
                rectangular_slope_layout.layoutParams = rectangular_slope_layout_params
            }
        }



    }

}