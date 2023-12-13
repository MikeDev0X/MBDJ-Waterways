package com.example.mbdwaterways

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Integer.parseInt
import kotlin.math.pow

class CircularRevisionOne : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.circular_revision_one)

        fun cropDecimals(num: Double, n: Int): String {
            val finalString = String.format("%.${n}f", num)
            return finalString
        }

        val q_static = findViewById<TextView>(R.id.q_static)
        val d_static = findViewById<TextView>(R.id.D_static)
        val y_static = findViewById<TextView>(R.id.Y_static)
        val Yc_static = findViewById<TextView>(R.id.Yc_static)

        q_static.text = intent.getStringExtra("circ_Q")
        d_static.text = intent.getStringExtra("circ_D")
        y_static.text = intent.getStringExtra("circ_Y")

        val decimals : Int = intent.getStringExtra("decimals")?.toInt() ?: -1

        val Q = q_static.text.toString().toDouble()
        val D = d_static.text.toString().toDouble()
        val Y = y_static.text.toString().toDouble()

        val angleButton = findViewById<Button>(R.id.circular_angle_button)
        val areaButton = findViewById<Button>(R.id.circular_area_button)
        val waterMirrorButton = findViewById<Button>(R.id.circular_water_mirror_button)
        val funcionFcButton = findViewById<Button>(R.id.circular_fc_button)
        val derivateButton = findViewById<Button>(R.id.derivate_fc_button)
        val ycplusoneButton = findViewById<Button>(R.id.circular_ycplusone_button)

        val continueButton = findViewById<Button>(R.id.continueButton)


        var finalAngle = 0.0
        var finalArea = 0.0
        var finalWaterMirror = 0.0
        var finalFc = 0.0
        var finalFcP = 0.0

        var counter = 0

        fun calculateCriticalTight(initialTight : Double): Double {
            val smallest : Int = (Y.toInt() / 10) * 10
            val largest : Int = (smallest + 10)

            var tempYc : Double = 0.0
            var decidingFactor : Int = 0

            if(Y.toInt() - smallest > largest - Y.toInt()){
                decidingFactor = largest
            }
            else{
                decidingFactor = smallest
            }

            //Initial critical tight based on its rounding
            tempYc = Y - (Y*(0.15*(decidingFactor/10)))


            var tempAngle = 0.0
            var tempArea = 0.0
            var tempWaterMirror = 0.0

            var tempFc = 0.0
            var tempFcp = 0.0

            var tempYc_plus_one = 0.0

            var flooredYc = 0.0
            var flooredYcPlusOne = 0.0

            while (true){

                tempAngle = 2*(Math.acos((1 - (2*tempYc / D))))
                tempArea = ((tempAngle - Math.sin(tempAngle)) * Math.pow(D,2.0)) / 8
                tempWaterMirror = (Math.sin(tempAngle/2)) * D
                tempFc = (tempArea.pow(3.0) / tempWaterMirror) - (Q.pow(2.0) / 9.81)

                tempFcp = (3 * Math.pow(tempArea,2.0) - ((Math.pow(tempArea,3.0) / Math.pow(tempWaterMirror,2.0)   ) * (( 1 - ((2*tempYc)/D)) / (Math.pow(((tempYc/D) * (1 - (tempYc / D))),0.5))))       )

                tempYc_plus_one = (tempYc - ((tempFc)/(tempFcp)))

                flooredYc = Math.round(tempYc * 1000000.0) / 1000000.0
                flooredYcPlusOne = Math.round(tempYc_plus_one * 1000000.0) / 1000000.0

                if(flooredYc == flooredYcPlusOne){
                    //store final values
                    finalAngle = tempAngle
                    finalArea = tempArea
                    finalWaterMirror = tempWaterMirror
                    finalFc = tempFc
                    finalFcP = tempFcp

                    return tempYc_plus_one
                }
                else{
                    tempYc = tempYc_plus_one
                }

                counter = counter + 1

            }
        }

        val finalCriticalTight = calculateCriticalTight(Y)
        Yc_static.text = finalCriticalTight.toString()

        //linear layouts

        val circular_angle_layout = findViewById<LinearLayout>(R.id.circular_angle_layout)
        val circular_angle_layout_params : ViewGroup.LayoutParams = circular_angle_layout.layoutParams

        val circular_area_layout = findViewById<LinearLayout>(R.id.circular_area_layout)
        val circular_area_layout_params : ViewGroup.LayoutParams = circular_area_layout.layoutParams

        val circular_water_mirror_layout = findViewById<LinearLayout>(R.id.circular_water_mirror_layout)
        val circular_water_mirror_layout_params : ViewGroup.LayoutParams = circular_water_mirror_layout.layoutParams

        val circular_fc_layout = findViewById<LinearLayout>(R.id.circular_fc_layout)
        val circular_fc_layout_params : ViewGroup.LayoutParams = circular_fc_layout.layoutParams

        val circular_derivate_layout = findViewById<LinearLayout>(R.id.circular_derivate_layout)
        val circular_derivate_layout_params : ViewGroup.LayoutParams = circular_derivate_layout.layoutParams

        val circular_ycplusone_layout = findViewById<LinearLayout>(R.id.circular_ycplusone_layout)
        val circular_ycplusone_layout_params : ViewGroup.LayoutParams = circular_ycplusone_layout.layoutParams


        //results text views
        val middle_angle_result = findViewById<TextView>(R.id.middle_angle_result)
        val final_angle_result = findViewById<TextView>(R.id.final_angle_result)

        val middle_area_result = findViewById<TextView>(R.id.middle_area_result)
        val final_area_result = findViewById<TextView>(R.id.final_area_result)

        val middle_water_mirror_result = findViewById<TextView>(R.id.middle_water_mirror_result)
        val final_water_mirror_result = findViewById<TextView>(R.id.final_water_mirror_result)

        val middle_fc_result = findViewById<TextView>(R.id.middle_fc_result)
        val final_fc_result = findViewById<TextView>(R.id.final_fc_result)

        val second_derivate_result = findViewById<TextView>(R.id.second_derivate_result)
        val third_derivate_result = findViewById<TextView>(R.id.third_derivate_result)

        val middle_ycplusone_result = findViewById<TextView>(R.id.middle_ycplusone_result)
        val final_ycplusone_result = findViewById<TextView>(R.id.final_ycplusone_result)


        //result strings
        middle_angle_result.text = "2 arccos (" + cropDecimals((1 - ((2*finalCriticalTight) / D)),decimals) + ")"
        final_angle_result.text = cropDecimals( finalAngle,decimals) + " °"

        middle_area_result.text = "[ (" + cropDecimals(finalAngle,decimals) + " - Sen(" + cropDecimals(finalAngle,decimals) + ") * " + (D.toString()) + " ^ 2 ] / 8"
        final_area_result.text = cropDecimals(finalArea,decimals) + " m"

        middle_water_mirror_result.text = "(Sen(" + cropDecimals(finalAngle,decimals) + "/2)) * " + D.toString()
        final_water_mirror_result.text = cropDecimals(finalWaterMirror,decimals) + " m"

        middle_fc_result.text = "(" + cropDecimals(finalArea,3) + "^3 / " + cropDecimals(finalWaterMirror,3) + ") - (" + Q + "^2 / 9.81)"
        final_fc_result.text = cropDecimals(finalFc,decimals)

        second_derivate_result.text = "3 * " + cropDecimals(finalArea,3) + "^2 - (" + cropDecimals(finalArea,3) + "^3 / " + cropDecimals(finalWaterMirror,3) + "^2) * [(1 - (2" + cropDecimals(finalCriticalTight,3) + "/" + D + ")) / ((" + cropDecimals(finalCriticalTight,3) + "/" + D + ")(1 - (" + cropDecimals(finalCriticalTight,3) + "/" + D  + ")) ^ 1/2]"
        third_derivate_result.text = cropDecimals(finalFcP,decimals)

        middle_ycplusone_result.text = cropDecimals(finalCriticalTight,decimals) + " - (" + cropDecimals(finalFc,decimals) + " / " + cropDecimals(finalFcP,decimals) + ")"
        final_ycplusone_result.text = cropDecimals(finalCriticalTight, decimals)

        //layouts expand
        angleButton.setOnClickListener{
            if(circular_angle_layout_params.height == 0){
                circular_angle_layout_params.height = 480
                circular_angle_layout.layoutParams = circular_angle_layout_params
            }
            else{
                circular_angle_layout_params.height = 0
                circular_angle_layout.layoutParams = circular_angle_layout_params
            }
        }

        areaButton.setOnClickListener {
            if(circular_area_layout_params.height == 0){
                circular_area_layout_params.height = 480
                circular_area_layout.layoutParams = circular_area_layout_params
            }
            else{
                circular_area_layout_params.height = 0
                circular_area_layout.layoutParams = circular_area_layout_params
            }
        }

        waterMirrorButton.setOnClickListener{
            if(circular_water_mirror_layout_params.height == 0){
                circular_water_mirror_layout_params.height = 480
                circular_water_mirror_layout.layoutParams = circular_water_mirror_layout_params
            }
            else{
                circular_water_mirror_layout_params.height = 0
                circular_water_mirror_layout.layoutParams = circular_water_mirror_layout_params
            }
        }

        funcionFcButton.setOnClickListener{
            if(circular_fc_layout_params.height == 0){
                circular_fc_layout_params.height = 480
                circular_fc_layout.layoutParams = circular_fc_layout_params
            }
            else{
                circular_fc_layout_params.height = 0
                circular_fc_layout.layoutParams = circular_fc_layout_params
            }
        }

        derivateButton.setOnClickListener {
            if(circular_derivate_layout_params.height == 0){
                circular_derivate_layout_params.height = 720
                circular_derivate_layout.layoutParams = circular_derivate_layout_params
            }
            else{
                circular_derivate_layout_params.height = 0
                circular_derivate_layout.layoutParams = circular_derivate_layout_params
            }
        }

        ycplusoneButton.setOnClickListener {
            if(circular_ycplusone_layout_params.height == 0){
                circular_ycplusone_layout_params.height = 500
                circular_ycplusone_layout.layoutParams = circular_ycplusone_layout_params
            }
            else{
                circular_ycplusone_layout_params.height = 0
                circular_ycplusone_layout.layoutParams = circular_ycplusone_layout_params
            }
        }

        continueButton.setOnClickListener {
            val intent = Intent(this@CircularRevisionOne, CircularRevisionTwo::class.java)

            intent.putExtra("Area", cropDecimals(finalArea, decimals))
            intent.putExtra("WaterMirror", cropDecimals(finalWaterMirror, decimals))
            intent.putExtra("Angle", cropDecimals(finalAngle, decimals))
            intent.putExtra("Yc",cropDecimals(finalCriticalTight, decimals))
            intent.putExtra("D", D.toString())
            intent.putExtra("Q", Q.toString())
            intent.putExtra("decimals", decimals.toString())

            startActivity(intent)
        }

    }

}