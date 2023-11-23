package com.example.mbdwaterways

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.nio.DoubleBuffer

class CircularDesignOne : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.circular_design_one)

        fun cropDecimals(num: Double, n: Int): String {
            val finalString = String.format("%.${n}f", num)
            return finalString
        }

        val S = intent.getStringExtra("S")
        val n = intent.getStringExtra("n")
        val Q = intent.getStringExtra("Q")
        val D = intent.getStringExtra("D")
        val decimals = intent.getStringExtra("decimals")

        val S_static = findViewById<TextView>(R.id.S_static)
        val n_static = findViewById<TextView>(R.id.n_static)
        val Q_static = findViewById<TextView>(R.id.Q_static)
        val D_static = findViewById<TextView>(R.id.D_static)
        val radians_angle = findViewById<TextView>(R.id.radians_angle)

        S_static.text = S
        n_static.text = n
        Q_static.text = Q
        D_static.text = D


        //buttons
        val circular_diameter_button = findViewById<Button>(R.id.circular_diameter_button)
        val circular_pipeline_button = findViewById<Button>(R.id.circular_pipeline_button)
        val circular_capacity_button = findViewById<Button>(R.id.circular_capacity_button)
        val circular_grades_button = findViewById<Button>(R.id.circular_grades_button)
        val circular_area_button = findViewById<Button>(R.id.circular_area_button)
        val circular_perimeter_button = findViewById<Button>(R.id.circular_perimeter_button)
        val circular_radious_button = findViewById<Button>(R.id.circular_radious_button)

        val alcantarillado_button = findViewById<Button>(R.id.alcantarilladoButton)

        //layouts

        val circular_diameter_layout = findViewById<LinearLayout>(R.id.circular_diameter_layout)
        val circular_diameter_layout_params : ViewGroup.LayoutParams = circular_diameter_layout.layoutParams

        val circular_pipeline_layout = findViewById<LinearLayout>(R.id.circular_pipeline_layout)
        val circular_pipeline_layout_params : ViewGroup.LayoutParams = circular_pipeline_layout.layoutParams

        val circular_capacity_layout = findViewById<LinearLayout>(R.id.circular_capacity_layout)
        val circular_capacity_layout_params: ViewGroup.LayoutParams = circular_capacity_layout.layoutParams

        val circular_grades_layout = findViewById<LinearLayout>(R.id.circular_grades_layout)
        val circular_grades_layout_params: ViewGroup.LayoutParams = circular_grades_layout.layoutParams

        val circular_area_layout = findViewById<LinearLayout>(R.id.circular_area_layout)
        val circular_area_layout_params: ViewGroup.LayoutParams = circular_area_layout.layoutParams

        val circular_perimeter_layout = findViewById<LinearLayout>(R.id.circular_perimeter_layout)
        val circular_perimeter_layout_params: ViewGroup.LayoutParams = circular_perimeter_layout.layoutParams

        val circular_radious_layout = findViewById<LinearLayout>(R.id.circular_radious_layout)
        val circular_radious_layout_params: ViewGroup.LayoutParams = circular_radious_layout.layoutParams



        //results
        val middle_diameter_result = findViewById<TextView>(R.id.middle_diameter_result)
        val final_diameter_result = findViewById<TextView>(R.id.final_diameter_result)

        val middle_pipeline_result = findViewById<TextView>(R.id.middle_pipeline_result)
        val final_pipeline_result = findViewById<TextView>(R.id.final_pipeline_result)

        val middle_capacity_result = findViewById<TextView>(R.id.middle_capacity_result)
        val final_capacity_result = findViewById<TextView>(R.id.final_capacity_result)

        val final_grades_result = findViewById<TextView>(R.id.final_grades_result)

        val middle_area_result = findViewById<TextView>(R.id.middle_area_result)
        val final_area_result = findViewById<TextView>(R.id.final_area_result)

        val middle_perimeter_result = findViewById<TextView>(R.id.middle_perimeter_result)
        val final_perimeter_result = findViewById<TextView>(R.id.final_perimeter_result)

        val middle_radious_result = findViewById<TextView>(R.id.middle_radious_result)
        val final_radious_result = findViewById<TextView>(R.id.final_radious_result)



        // results variables
        var initialAngle = 3.1416
        var f = 0.0
        var fPrime = 0.0

        var Area = 0.0
        var Rh = 0.0

        var newAngle = 0.0
        var flooredInitial = 0.0
        var flooredFinal = 0.0

        var counter = 0

        fun calculateNewAngle() : Double{

            while(true){

                // calculate Rh
                if (D != null) {
                    Rh = ((D.toDouble() / 4) - ((D.toDouble() * Math.sin(initialAngle)) / (4 * initialAngle)))
                }


                if (Q != null && n != null && D != null && S != null) {
                    f = ((8*Q.toDouble()*n.toDouble()) / (Math.pow(D.toDouble(),2.0) * Math.pow(S.toDouble(),0.5) * Math.pow(Rh,0.66666))) + Math.sin(initialAngle) - initialAngle
                }

                if (Q != null && n != null && D != null && S != null) {
                    fPrime = ((4 * Q.toDouble() * n.toDouble()) / (3 * D.toDouble() * Math.pow(S.toDouble(),0.5) * Math.pow(Rh,1.66666))) * ((initialAngle * Math.cos(initialAngle) - Math.sin(initialAngle)) / (Math.pow(initialAngle,2.0))) + Math.cos(initialAngle) - 1
                }

                newAngle = initialAngle - (f / fPrime)

                flooredInitial = Math.round(initialAngle * 1000000.0) / 1000000.0
                flooredFinal = Math.round(newAngle * 1000000.0) / 1000000.0

                if(flooredInitial == flooredFinal)
                    return newAngle
                else{
                    initialAngle = newAngle
                    counter ++
                }


            }

        }


        //textview results

            //diameter
        middle_diameter_result.text = "[(3.2084 * " + Q + "* " + n + ") / ( " + S + " ^ 1/2 ) ] ^ 3/8"
        if (Q != null && n != null && S != null && decimals != null) {
            final_diameter_result.text = cropDecimals(Math.pow((3.2084*Q.toDouble()*n.toDouble())/(Math.pow(S.toDouble(),2.0)),0.375), decimals.toInt()) + " m"
        }

            //pipeline
        middle_pipeline_result.text = "(" + D + " ^ 8/3 * " + S + " ^ 1/2) / (3.2084 *" + n +")"
        if (D != null && S != null && n != null && decimals != null) {
            final_pipeline_result.text = cropDecimals(((Math.pow(D.toDouble(),2.666666) * Math.pow(S.toDouble(),0.5) ) / (3.2084 * n.toDouble())),decimals.toInt())
        }

            //capacity
        if(D != null && S!=null && n!=null && decimals!=null && Q!=null){
            middle_capacity_result.text = Q + " / " + cropDecimals(((Math.pow(D.toDouble(),2.666666) * Math.pow(S.toDouble(),0.5) ) / (3.2084 * n.toDouble())),decimals.toInt())
            final_capacity_result.text = cropDecimals((Q.toDouble()/((Math.pow(D.toDouble(),2.666666) * Math.pow(S.toDouble(),0.5) ) / (3.2084 * n.toDouble()))), decimals.toInt())
        }

        val resultantAngle = calculateNewAngle()

        if (decimals != null) {
            radians_angle.text = cropDecimals(resultantAngle,decimals.toInt())
        }

            //grades
        if (decimals!=null) {
            final_grades_result.text = cropDecimals(((resultantAngle * 180) / Math.PI),decimals.toInt()) + " °"
        }

            //area
        if (decimals != null && D != null) {
            middle_area_result.text = "[(" + cropDecimals(resultantAngle,decimals.toInt()) + " - Sen" + cropDecimals(resultantAngle,decimals.toInt()) + ") * " + D.toString() + "^2] / 8"
            final_area_result.text = cropDecimals(((resultantAngle - Math.sin(resultantAngle)*Math.pow(D.toDouble(),2.0) / 8) ),decimals.toInt()) + " m^2"
        }

            //perimeter
        if (decimals != null && D != null) {
            middle_perimeter_result.text = "(" + cropDecimals((resultantAngle), decimals.toInt()) + " * " + D.toDouble() + ") / 2"
            final_perimeter_result.text = cropDecimals(((resultantAngle * D.toDouble())/2), decimals.toInt()) + " m"
        }

            //radious
        if (decimals != null && D != null) {
            middle_radious_result.text = "(1 - (Sen" +  cropDecimals((resultantAngle), decimals.toInt()) + " / " + cropDecimals((resultantAngle), decimals.toInt()) + ")) * (" + D.toDouble() + "/4)"
            final_radious_result.text = cropDecimals(((1 - (Math.sin(resultantAngle) / resultantAngle ))*(D.toDouble()/4)),decimals.toInt()) + " m"
        }


        //layouts resizing

        circular_diameter_button.setOnClickListener {
            if(circular_diameter_layout_params.height == 0){
                circular_diameter_layout_params.height = 500
                circular_diameter_layout.layoutParams = circular_diameter_layout_params
            }
            else{
                circular_diameter_layout_params.height = 0
                circular_diameter_layout.layoutParams = circular_diameter_layout_params
            }
        }

        circular_pipeline_button.setOnClickListener {
            if(circular_pipeline_layout_params.height == 0){
                circular_pipeline_layout_params.height = 500
                circular_pipeline_layout.layoutParams = circular_pipeline_layout_params
            }
            else{
                circular_pipeline_layout_params.height = 0
                circular_pipeline_layout.layoutParams = circular_pipeline_layout_params
            }
        }

        circular_capacity_button.setOnClickListener {
            if (circular_capacity_layout_params.height == 0) {
                circular_capacity_layout_params.height = 500
                circular_capacity_layout.layoutParams = circular_capacity_layout_params
            } else {
                circular_capacity_layout_params.height = 0
                circular_capacity_layout.layoutParams = circular_capacity_layout_params
            }
        }


        circular_grades_button.setOnClickListener {
            if (circular_grades_layout_params.height == 0) {
                circular_grades_layout_params.height = 300
                circular_grades_layout.layoutParams = circular_grades_layout_params
            } else {
                circular_grades_layout_params.height = 0
                circular_grades_layout.layoutParams = circular_grades_layout_params
            }
        }

        circular_area_button.setOnClickListener {
            if (circular_area_layout_params.height == 0) {
                circular_area_layout_params.height = 480
                circular_area_layout.layoutParams = circular_area_layout_params
            } else {
                circular_area_layout_params.height = 0
                circular_area_layout.layoutParams = circular_area_layout_params
            }
        }

        circular_perimeter_button.setOnClickListener {
            if (circular_perimeter_layout_params.height == 0) {
                circular_perimeter_layout_params.height = 480
                circular_perimeter_layout.layoutParams = circular_perimeter_layout_params
            } else {
                circular_perimeter_layout_params.height = 0
                circular_perimeter_layout.layoutParams = circular_perimeter_layout_params
            }
        }

        circular_radious_button.setOnClickListener {
            if (circular_radious_layout_params.height == 0) {
                circular_radious_layout_params.height = 480
                circular_radious_layout.layoutParams = circular_radious_layout_params
            } else {
                circular_radious_layout_params.height = 0
                circular_radious_layout.layoutParams = circular_radious_layout_params
            }
        }


        alcantarillado_button.setOnClickListener {
            val intent = Intent(this@CircularDesignOne,CircularAlcantarillado::class.java)

            startActivity(intent)
        }


    }
}