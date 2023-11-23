package com.example.mbdwaterways

import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class RectangularFillFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.rectangular_fragment,container, false)

        val rect_h = view.findViewById<EditText>(R.id.h_dynamic)
        val rect_b = view.findViewById<EditText>(R.id.b_dynamic)

        val rect_Q = view.findViewById<EditText>(R.id.q_dynamic)
        val rect_n = view.findViewById<EditText>(R.id.n_dynamic)

        val rectangular_qr = view.findViewById<ImageView>(R.id.rectangular_qr)
        val rectangular_pdf_qr = view.findViewById<ImageView>(R.id.rectangular_pdf_qr)

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



        var calculateButton = view.findViewById<Button>(R.id.calculate_button)

        calculateButton?.setOnClickListener{


            if(rect_h.text.toString() != "" && rect_b.text.toString() != ""){
                val intent = Intent(context,RectangularResults::class.java)

                intent.putExtra("rect_h",rect_h.text.toString())
                intent.putExtra("rect_b",rect_b.text.toString())
                intent.putExtra("rect_Q", rect_Q.text.toString())
                intent.putExtra("rect_n", rect_n.text.toString())
                intent.putExtra("decimals", decimals.toString())

                startActivity(intent)
            }
        }

        //image content redirect
        var rectangularUrl = "https://youtu.be/VVlquWIVtVg?si=jEVqfruHX71WVSTY"
        var rectangularPdf = "https://me-qr.com/mobile/pdf/18765319"

        rectangular_qr.setOnClickListener{
            if (!rectangularUrl.startsWith("http://") && !rectangularUrl.startsWith("https://")) {
                rectangularUrl = "http://$rectangularUrl"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(rectangularUrl))
            startActivity(browserIntent)
        }

        rectangular_pdf_qr.setOnClickListener {
            if (!rectangularPdf.startsWith("http://") && !rectangularPdf.startsWith("https://")) {
                rectangularPdf = "http://$rectangularPdf"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(rectangularPdf))
            startActivity(browserIntent)
        }


        return view;
    }
}
