package com.example.mbdwaterways

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class CircularRevisionTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.circular_revision_two)

        fun cropDecimals(num: Double, n: Int): String {
            val finalString = String.format("%.${n}f", num)
            return finalString
        }

        val Area : Double? = intent.getStringExtra("Area")?.toDouble()
        val WaterMirror : Double? = intent.getStringExtra("WaterMirror")?.toDouble()
        val Angle : Double? = intent.getStringExtra("Angle")?.toDouble()
        val Yc : Double? = intent.getStringExtra("Yc")?.toDouble()
        val D : Double? = intent.getStringExtra("D")?.toDouble()
        val Q : Double? = intent.getStringExtra("Q")?.toDouble()
        val decimals : Int? = intent.getStringExtra("decimals")?.toInt()

        val AreaStatic = findViewById<TextView>(R.id.area_static)
            AreaStatic.text = Area.toString()
        val WaterMirrorStatic = findViewById<TextView>(R.id.watermirror_static)
            WaterMirrorStatic.text = WaterMirror.toString()
        val AngleStatic = findViewById<TextView>(R.id.Angle_static)
            AngleStatic.text = Angle.toString()
        val YcStatic = findViewById<TextView>(R.id.Yc_static)
            YcStatic.text = Yc.toString()
        val Qstatic = findViewById<TextView>(R.id.Q_static)
            Qstatic.text = Q.toString()
        val Dstatic = findViewById<TextView>(R.id.D_static)
            Dstatic.text = D.toString()


        val middle_wet_perimeter_result = findViewById<TextView>(R.id.middle_wet_perimeter_result)
        val final_wet_perimeter_result = findViewById<TextView>(R.id.final_wet_perimeter_result)

        val middle_hidraulic_radious_result = findViewById<TextView>(R.id.middle_hidraulic_radious_result)
        val final_hidraulic_radious_result = findViewById<TextView>(R.id.final_hidraulic_radious_result)

        val middle_velocity_result = findViewById<TextView>(R.id.middle_velocity_result)
        val final_velocity_result = findViewById<TextView>(R.id.final_velocity_result)

        val middle_energy_result = findViewById<TextView>(R.id.middle_energy_result)
        val final_energy_result = findViewById<TextView>(R.id.final_energy_result)

        val middle_froude_result = findViewById<TextView>(R.id.middle_froude_result)
        val final_froude_result = findViewById<TextView>(R.id.final_froude_result)

        //buttons
        val circular_wet_perimeter_button = findViewById<Button>(R.id.circular_wet_perimeter_button)
        val circular_hidraulic_radious_button = findViewById<Button>(R.id.circular_hidraulic_radious_button)
        val circular_velocity_button = findViewById<Button>(R.id.circular_velocity_button)
        val circular_energy_button = findViewById<Button>(R.id.circular_energy_button)
        val circular_froude_button = findViewById<Button>(R.id.circular_froude_button)


        //layouts
        val circular_wet_perimeter_layout = findViewById<LinearLayout>(R.id.circular_wet_perimeter_layout)
        val circular_wet_perimeter_layout_params : ViewGroup.LayoutParams = circular_wet_perimeter_layout.layoutParams

        val circular_hidraulic_radious_layout = findViewById<LinearLayout>(R.id.circular_hidraulic_radious_layout)
        val circular_hidraulic_radious_layout_params : ViewGroup.LayoutParams = circular_hidraulic_radious_layout.layoutParams

        val circular_velocity_layout = findViewById<LinearLayout>(R.id.circular_velocity_layout)
        val circular_velocity_layout_params : ViewGroup.LayoutParams = circular_velocity_layout.layoutParams

        val circular_energy_layout = findViewById<LinearLayout>(R.id.circular_energy_layout)
        val circular_energy_layout_params : ViewGroup.LayoutParams = circular_energy_layout.layoutParams

        val circular_froude_layout = findViewById<LinearLayout>(R.id.circular_froude_layout)
        val circular_froude_layout_params : ViewGroup.LayoutParams = circular_froude_layout.layoutParams


        //textviews results

            //wet perimeter
        if (Angle != null && D != null) {
            middle_wet_perimeter_result.text = decimals?.let { cropDecimals((Angle * D), it) } + " m / 2"
        }

        if (Angle != null && D != null) {
            final_wet_perimeter_result.text = decimals?.let { cropDecimals(((Angle * D)/2), it) }
        }

            //hidraulic radious
        if (Angle != null && D != null) {
            middle_hidraulic_radious_result.text = "( " + decimals?.let { cropDecimals((1 - (Math.sin(Angle) / Angle)), it) } + ") * (" + D + " / 4)"
        }

        if (Angle != null && D != null) {
            final_hidraulic_radious_result.text = decimals?.let { cropDecimals(((1 - (Math.sin(Angle) / Angle)) * (D/4)), it) } + " m"
        }


            //velocity
        if (Angle != null && D != null) {
            middle_velocity_result.text = Q.toString() + " / " + Area
        }

        if (Angle != null && D != null && Q!=null && Area!=null) {
            final_velocity_result.text = decimals?.let { cropDecimals((Q / Area), it) } + " m"
        }


            //energy
        if (Angle != null && D != null && Q != null && Area!=null) {
                middle_energy_result.text = Yc.toString() + " + [(" + Math.pow(Q.toDouble(),2.0) + ") / (" + decimals?.let{cropDecimals(2*9.81*Math.pow(Area,2.0), it)} + ")]"
        }

        if (Angle != null && D != null && Q!=null && Area!=null && Yc!=null) {
            final_energy_result.text =
                decimals?.let {
                    cropDecimals(Yc + (Math.pow(Q,2.0) / (2*9.81*Math.pow(Area,2.0))),
                        it
                    )
                }.toString() + "Kj"
        }

            //froude
        if (Angle != null && D != null && Q!=null && Area!=null && Yc!=null) {
            middle_froude_result.text = "(" + (decimals?.let { cropDecimals((Q/Area), it) }) + ") / (" + Math.pow((9.81*Yc),2.0) + ")"
        }

        if (Angle != null && D != null && Q!=null && Area!=null && Yc!=null) {
            final_froude_result.text = (decimals?.let { cropDecimals(((Q/Area) / (Math.pow(9.81*Yc,0.5))), it) })
        }


        //layouts resizing
        circular_wet_perimeter_button.setOnClickListener {
            if(circular_wet_perimeter_layout_params.height == 0){
                circular_wet_perimeter_layout_params.height = 500
                circular_wet_perimeter_layout.layoutParams = circular_wet_perimeter_layout_params
            }
            else{
                circular_wet_perimeter_layout_params.height = 0
                circular_wet_perimeter_layout.layoutParams = circular_wet_perimeter_layout_params
            }
        }

        circular_hidraulic_radious_button.setOnClickListener {
            if(circular_hidraulic_radious_layout_params.height == 0){
                circular_hidraulic_radious_layout_params.height = 500
                circular_hidraulic_radious_layout.layoutParams = circular_hidraulic_radious_layout_params
            }
            else{
                circular_hidraulic_radious_layout_params.height = 0
                circular_hidraulic_radious_layout.layoutParams = circular_hidraulic_radious_layout_params
            }
        }

        circular_velocity_button.setOnClickListener {
            if(circular_velocity_layout_params.height == 0){
                circular_velocity_layout_params.height = 500
                circular_velocity_layout.layoutParams = circular_velocity_layout_params
            }
            else{
                circular_velocity_layout_params.height = 0
                circular_velocity_layout.layoutParams = circular_velocity_layout_params
            }
        }

        circular_energy_button.setOnClickListener {
            if(circular_energy_layout_params.height == 0){
                circular_energy_layout_params.height = 500
                circular_energy_layout.layoutParams = circular_energy_layout_params
            }
            else{
                circular_energy_layout_params.height = 0
                circular_energy_layout.layoutParams = circular_energy_layout_params
            }
        }

        circular_froude_button.setOnClickListener {
            if(circular_froude_layout_params.height == 0){
                circular_froude_layout_params.height = 500
                circular_froude_layout.layoutParams = circular_froude_layout_params
            }
            else{
                circular_froude_layout_params.height = 0
                circular_froude_layout.layoutParams = circular_froude_layout_params
            }
        }

    }

}
