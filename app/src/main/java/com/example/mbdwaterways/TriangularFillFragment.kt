package com.example.mbdwaterways

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class TriangularFillFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = layoutInflater.inflate(R.layout.triangular_fragment,container, false)

        //image content redirect
        var triangularUrl = "https://youtu.be/_VXknTu9niI?si=79cPX4kdfZYWP4VV"
        var triangularPdf = "https://me-qr.com/mobile/pdf/18765381"

        val triangular_qr = view.findViewById<ImageView>(R.id.triangular_qr)
        val triangular_pdf_qr = view.findViewById<ImageView>(R.id.triangular_pdf_qr)

        val calculate_button = view.findViewById<Button>(R.id.calculate_button)

        val Y_dynamic = view.findViewById<EditText>(R.id.Y_dynamic)
        val z_dynamic = view.findViewById<EditText>(R.id.z_dynamic)

        val q_dynamic = view.findViewById<EditText>(R.id.q_dynamic)
        val n_dynamic = view.findViewById<EditText>(R.id.n_dynamic)

        triangular_qr.setOnClickListener{
            if (!triangularUrl.startsWith("http://") && !triangularUrl.startsWith("https://")) {
                triangularUrl = "http://$triangularUrl"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(triangularUrl))
            startActivity(browserIntent)
        }

        triangular_pdf_qr.setOnClickListener{
            if (!triangularPdf.startsWith("http://") && !triangularPdf.startsWith("https://")) {
                triangularPdf = "http://$triangularPdf"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(triangularPdf))
            startActivity(browserIntent)
        }

        var decimals = 1

        val decreaseDecimals = view.findViewById<Button>(R.id.decrease_decimals)
        val increaseDecimals = view.findViewById<Button>(R.id.increase_decimals)
        val currentDecimals = view.findViewById<TextView>(R.id.currentDecimals)

        decreaseDecimals.setOnClickListener {
            if(decimals > 1)
                decimals -= 1

            currentDecimals.text = decimals.toString()
        }

        increaseDecimals.setOnClickListener {
            if (decimals <= 9)
                decimals += 1

            currentDecimals.text = decimals.toString()
        }

        calculate_button.setOnClickListener{
            if(Y_dynamic.text.toString() != "" && z_dynamic.text.toString() != ""){
                val intent = Intent(context,TriangularResults::class.java)

                intent.putExtra("Y_dynamic",Y_dynamic.text.toString())
                intent.putExtra("z_dynamic",z_dynamic.text.toString())
                intent.putExtra("q_dynamic", q_dynamic.text.toString())
                intent.putExtra("n_dynamic", n_dynamic.text.toString())
                intent.putExtra("decimals", decimals.toString())

                startActivity(intent)
            }

        }


        return view
    }
}