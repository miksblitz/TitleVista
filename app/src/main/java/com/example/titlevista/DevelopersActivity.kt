package com.example.titlevista

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView


class DevelopersActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developers)

        // Initializing the Back Button
        val backBtn: ImageView = findViewById(R.id.backButton)

        // Setting the onClickListener for the Back Button
        backBtn.setOnClickListener {
            val intent = Intent(this, LandingPageActivity::class.java)
            startActivity(intent)
            finish() // This will close the DevelopersActivity then go back to the LandingPageActivity
        }
    }
}