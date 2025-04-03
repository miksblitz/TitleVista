package com.example.titlevista

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class LandingPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        // Initializing the Get Started Button
        val getStartedBtn: LinearLayout = findViewById(R.id.getStartedButton)

        // Setting the onClickListener for the Get Started Button
        getStartedBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Initializing the Developers Button
        val learnMoreBtn: LinearLayout = findViewById(R.id.learnMoreButton)

        // Setting the onClickListener for the Developers Button
        learnMoreBtn.setOnClickListener {
            val intent = Intent(this, DevelopersActivity::class.java)
            startActivity(intent)
        }
    }
}