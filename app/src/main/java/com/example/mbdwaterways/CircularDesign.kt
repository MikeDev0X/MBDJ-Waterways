package com.example.mbdwaterways

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CircularDesign : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.circular_design)

        val nextbutton = findViewById<Button>(R.id.next_button)
        val circular_design_qr = findViewById<ImageView>(R.id.circular_design_qr)
        val circular_pdf_qr = findViewById<ImageView>(R.id.circular_pdf_qr)

        val S = findViewById<EditText>(R.id.S_dynamic)
        val n = findViewById<EditText>(R.id.n_dynamic)
        val D = findViewById<EditText>(R.id.D_dynamic)
        val Q = findViewById<EditText>(R.id.Q_dynamic)


        val decreaseDecimals = findViewById<Button>(R.id.decrease_decimals)
        val increaseDecimals = findViewById<Button>(R.id.increase_decimals)
        val currentDecimals = findViewById<TextView>(R.id.currentDecimals)
        var decimals = 1

        //image content redirect
        var circularUrl = "https://youtu.be/bP2a6UNFzgM?si=k2TbGvGThR8MlNNl"
        var circularPdf = "https://me-qr.com/mobile/pdf/18773201"

        circular_design_qr.setOnClickListener{
            if (!circularUrl.startsWith("http://") && !circularUrl.startsWith("https://")) {
                circularUrl = "http://$circularUrl"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(circularUrl))
            startActivity(browserIntent)
        }

        circular_pdf_qr.setOnClickListener {
            if (!circularPdf.startsWith("http://") && !circularPdf.startsWith("https://")) {
                circularPdf = "http://$circularPdf"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(circularPdf))
            startActivity(browserIntent)
        }


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

        nextbutton.setOnClickListener {
            if(S.text.toString() != "" && n.text.toString()!="" && Q.text.toString()!="" && D.text.toString()!=""){
                val intent = Intent(this@CircularDesign, CircularDesignOne::class.java)

                intent.putExtra("S", S.text.toString())
                intent.putExtra("n", n.text.toString())
                intent.putExtra("Q", Q.text.toString())
                intent.putExtra("D", D.text.toString())
                intent.putExtra("decimals", decimals.toString())

                startActivity(intent)
            }
        }

    }

}