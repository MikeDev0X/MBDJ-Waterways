package com.example.mbdwaterways

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity (){

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val oneSectionCanal = findViewById<Button>(R.id.one_section)
        val twoSectionCanal = findViewById<Button>(R.id.two_section)

        //image content redirect
        val main_pdf_qr = findViewById<ImageView>(R.id.main_pdf_qr)
        var mainPdf = "https://me-qr.com/mobile/pdf/18772717"

        main_pdf_qr.setOnClickListener{
            if (!mainPdf.startsWith("http://") && !mainPdf.startsWith("https://")) {
                mainPdf = "http://$mainPdf"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mainPdf))
            startActivity(browserIntent)
        }






        oneSectionCanal.setOnClickListener{
            val intent = Intent(this,Selection::class.java)
            startActivity(intent)
        }

        twoSectionCanal.setOnClickListener {
            val intent = Intent(this,Selection2::class.java)
            startActivity(intent)
        }

    }
}