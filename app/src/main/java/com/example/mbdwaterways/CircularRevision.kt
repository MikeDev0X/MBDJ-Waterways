package com.example.mbdwaterways

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CircularRevision : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.circular_revision)


        val circ_Y = findViewById<EditText>(R.id.Y_dynamic)
        val circ_D = findViewById<EditText>(R.id.D_dynamic)
        val circ_Q = findViewById<EditText>(R.id.q_dynamic)

        var decimals = 1

        val circular_design_qr = findViewById<ImageView>(R.id.circular_design_qr)
        val circular_pdf_qr = findViewById<ImageView>(R.id.circular_pdf_qr)

        val nextButton = findViewById<Button>(R.id.next_button)
        val decreaseDecimals = findViewById<Button>(R.id.decrease_decimals)
        val increaseDecimals = findViewById<Button>(R.id.increase_decimals)
        val currentDecimals = findViewById<TextView>(R.id.currentDecimals)



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

        nextButton.setOnClickListener{
            if(circ_D.text.toString()!="" && circ_Y.text.toString()!="" && circ_Q.text.toString()!=""){

                val intent = Intent(this@CircularRevision, CircularRevisionOne::class.java)

                intent.putExtra("circ_D", circ_D.text.toString())
                intent.putExtra("circ_Y", circ_Y.text.toString())
                intent.putExtra("circ_Q", circ_Q.text.toString())
                intent.putExtra("decimals", decimals.toString())

                startActivity(intent)


            }
        }

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

    }
}




