package com.example.mbdwaterways

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TriangularResults : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_triangular_results)

        fun cropDecimals(num: Double, n: Int): String {
            val finalString = String.format("%.${n}f", num)
            return finalString
        }

        //buttons
        val triangular_area_button = findViewById<Button>(R.id.triangular_area_button)
        val triangular_wet_perimeter_button = findViewById<Button>(R.id.triangular_wet_perimeter_button)
        val triangular_hidraulic_radious_button = findViewById<Button>(R.id.triangular_hidraulic_radious_button)
        val triangular_water_mirror_button = findViewById<Button>(R.id.triangular_water_mirror_button)
        val triangular_critical_tension_button = findViewById<Button>(R.id.triangular_critical_tension_button)
        val triangular_velocity_button = findViewById<Button>(R.id.triangular_velocity_button)
        val triangular_slope_button = findViewById<Button>(R.id.triangular_slope_button)


        //main variables
        val Y = intent.getStringExtra("Y_dynamic")
        val z = intent.getStringExtra("z_dynamic")
        val Q = intent.getStringExtra("q_dynamic")
        val n = intent.getStringExtra("n_dynamic")

        val decimals = intent.getStringExtra("decimals")

            //static text in ovals
            val Y_static = findViewById<TextView>(R.id.Y_static)
                Y_static.text = Y
            val z_static = findViewById<TextView>(R.id.z_static)
                z_static.text = z
            val Q_static = findViewById<TextView>(R.id.q_static)
                Q_static.text = Q
            val n_static = findViewById<TextView>(R.id.n_static)
                n_static.text = n


        // results text views
        val middle_area_result = findViewById<TextView>(R.id.middle_area_result)
        val final_area_result = findViewById<TextView>(R.id.final_area_result)

        val middle_wet_perimeter_result = findViewById<TextView>(R.id.middle_wet_perimeter_result)
        val final_wet_perimeter_result = findViewById<TextView>(R.id.final_wet_perimeter_result)

        val middle_hidraulic_radious_result = findViewById<TextView>(R.id.middle_hidraulic_radious_result)
        val final_hidraulic_radious_result = findViewById<TextView>(R.id.final_hidraulic_radious_result)

        val final_water_mirror_result = findViewById<TextView>(R.id.final_water_mirror_result)

        val middle_critical_tension_result = findViewById<TextView>(R.id.middle_critical_tension_result)

        val middle_velocity_result = findViewById<TextView>(R.id.middle_velocity_result)
        val final_velocity_result = findViewById<TextView>(R.id.final_velocity_result)

        val middle_slope_result = findViewById<TextView>(R.id.middle_slope_result)
        val final_slope_result = findViewById<TextView>(R.id.final_slope_result)


        //results strings
            //area
            middle_area_result.text = z + " * " + Y + "^2"
        if (z != null && Y != null && decimals != null) {
            final_area_result.text = cropDecimals((z.toDouble()*Math.pow(Y.toDouble(),2.0)),decimals.toInt()) + " m^2"
        }

            //wet perimeter
            middle_wet_perimeter_result.text = "2 * " + Y.toString() + "(1 + " + z.toString() + "^2)^1/2"
        if (decimals != null && Y != null && z != null) {
            final_wet_perimeter_result.text = cropDecimals((2*Y.toDouble()*Math.pow((1+Math.pow(z.toDouble(),2.0)),0.5)), decimals.toInt()) + " m"
        }

            //hidraulic radious
            middle_hidraulic_radious_result.text = "(" + z.toString() + " * " + Y.toString() + ") / (2 * (1+" + z.toString() + "^2)^1/2 )"
        if (z != null && Y != null && decimals != null) {
            final_hidraulic_radious_result.text = "(" + cropDecimals((z.toDouble()*Y.toDouble()), decimals.toInt()) + ") / (" + cropDecimals((2*Math.pow((1+Math.pow(z.toDouble(),2.0)),0.5)), decimals.toInt()) + ") = " + cropDecimals(((z.toDouble()*Y.toDouble()) / (2*Math.pow((1+Math.pow(z.toDouble(),2.0)),0.5))), decimals.toInt()) + " m"
        }

            //water mirror
        if (z != null && Y != null && decimals != null) {
            final_water_mirror_result.text = cropDecimals((2*z.toDouble()*Y.toDouble()),decimals.toInt()) + " m"
        }

            //critical tension
        if (Y != null && decimals != null) {
            middle_critical_tension_result.text = cropDecimals((Y.toDouble()/2), decimals.toInt()) + " m"
        }

        if (z != null && Y!=null && decimals!=null && Q!=null) {
            middle_velocity_result.text = Q.toString() + " / " + cropDecimals((z.toDouble()*Math.pow(Y.toDouble(),2.0)),decimals.toInt())
            final_velocity_result.text = cropDecimals((Q.toDouble()/(z.toDouble()*Math.pow(Y.toDouble(),2.0))), decimals.toInt()) + " m/s"
        }

        if (decimals != null && Q!=null && n!=null && Y != null && z!=null) {
            middle_slope_result.text = "((" + cropDecimals((Q.toDouble()*n.toDouble()), decimals.toInt()) + ") / (" + cropDecimals((z.toDouble()*Math.pow(Y.toDouble(),2.0)),decimals.toInt()) + "(" + cropDecimals(((z.toDouble()*Y.toDouble()) / (2*Math.pow((1+Math.pow(z.toDouble(),2.0)),0.5))), decimals.toInt()) + ")) ^2/3) ^2"
            final_slope_result.text = cropDecimals(Math.pow(Math.pow(((Q.toDouble()*n.toDouble())/(z.toDouble()*Math.pow(Y.toDouble(),2.0) * ((z.toDouble()*Y.toDouble()) / (2*Math.pow((1+Math.pow(z.toDouble(),2.0)),0.5))))),0.66666),2.0), decimals.toInt()) + " m"
        }






        //linear layouts

        val triangular_area_layout = findViewById<LinearLayout>(R.id.triangular_area_layout)
        val triangular_area_layout_params : ViewGroup.LayoutParams = triangular_area_layout.layoutParams

        val wet_perimeter_layout = findViewById<LinearLayout>(R.id.triangular_wet_perimeter_layout)
        val wet_perimeter_layout_params : ViewGroup.LayoutParams = wet_perimeter_layout.layoutParams

        val triangular_hidraulic_radious_layout = findViewById<LinearLayout>(R.id.triangular_hidraulic_radious_layout)
        val triangular_hidraulic_radious_layout_params : ViewGroup.LayoutParams = triangular_hidraulic_radious_layout.layoutParams

        val triangular_water_mirror_layout = findViewById<LinearLayout>(R.id.triangular_water_mirror_layout)
        val triangular_water_mirror_layout_params : ViewGroup.LayoutParams = triangular_water_mirror_layout.layoutParams

        val triangular_critical_tension_layout = findViewById<LinearLayout>(R.id.triangular_critical_tension_layout)
        val triangular_critical_tension_layout_params : ViewGroup.LayoutParams = triangular_critical_tension_layout.layoutParams

        val triangular_velocity_layout = findViewById<LinearLayout>(R.id.triangular_velocity_layout)
        val triangular_velocity_layout_params : ViewGroup.LayoutParams = triangular_velocity_layout.layoutParams

        val triangular_slope_layout = findViewById<LinearLayout>(R.id.triangular_slope_layout)
        val triangular_slope_layout_params : ViewGroup.LayoutParams = triangular_slope_layout.layoutParams


        //layout resizing
        triangular_area_button.setOnClickListener{
            if(triangular_area_layout_params.height == 0){
                triangular_area_layout_params.height = 470
                triangular_area_layout.layoutParams = triangular_area_layout_params
            }
            else{
                triangular_area_layout_params.height = 0
                triangular_area_layout.layoutParams = triangular_area_layout_params
            }
        }

        triangular_wet_perimeter_button.setOnClickListener{
            if(wet_perimeter_layout_params.height == 0){
                wet_perimeter_layout_params.height = 480
                wet_perimeter_layout.layoutParams = wet_perimeter_layout_params
            }
            else{
                wet_perimeter_layout_params.height = 0
                wet_perimeter_layout.layoutParams = wet_perimeter_layout_params
            }
        }

        triangular_hidraulic_radious_button.setOnClickListener{
            if(triangular_hidraulic_radious_layout_params.height == 0){
                triangular_hidraulic_radious_layout_params.height = 500
                triangular_hidraulic_radious_layout.layoutParams = triangular_hidraulic_radious_layout_params
            }
            else{
                triangular_hidraulic_radious_layout_params.height = 0
                triangular_hidraulic_radious_layout.layoutParams = triangular_hidraulic_radious_layout_params
            }
        }

        triangular_water_mirror_button.setOnClickListener{
            if(triangular_water_mirror_layout_params.height == 0){
                triangular_water_mirror_layout_params.height = 330
                triangular_water_mirror_layout.layoutParams = triangular_water_mirror_layout_params
            }
            else{
                triangular_water_mirror_layout_params.height = 0
                triangular_water_mirror_layout.layoutParams = triangular_water_mirror_layout_params
            }
        }

        triangular_critical_tension_button.setOnClickListener{
            if(triangular_critical_tension_layout_params.height == 0){
                triangular_critical_tension_layout_params.height = 300
                triangular_critical_tension_layout.layoutParams = triangular_critical_tension_layout_params
            }
            else{
                triangular_critical_tension_layout_params.height = 0
                triangular_critical_tension_layout.layoutParams = triangular_critical_tension_layout_params
            }
        }

        triangular_velocity_button.setOnClickListener{
            if(triangular_velocity_layout_params.height == 0){
                triangular_velocity_layout_params.height = 470
                triangular_velocity_layout.layoutParams = triangular_velocity_layout_params
            }
            else{
                triangular_velocity_layout_params.height = 0
                triangular_velocity_layout.layoutParams = triangular_velocity_layout_params
            }
        }

        triangular_slope_button.setOnClickListener{
            if(triangular_slope_layout_params.height == 0){
                triangular_slope_layout_params.height = 500
                triangular_slope_layout.layoutParams = triangular_slope_layout_params
            }
            else{
                triangular_slope_layout_params.height = 0
                triangular_slope_layout.layoutParams = triangular_slope_layout_params
            }
        }

    }
}