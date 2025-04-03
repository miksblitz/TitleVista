package com.example.titlevista

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var emailEmptyTextView: TextView
    private lateinit var passEmptyTextView: TextView
    private lateinit var passLengthTextView: TextView
    private lateinit var msgCheckTextView: TextView
    private lateinit var msgErrorTextView: TextView
    private lateinit var tvRegisterLink: TextView
    private lateinit var forgetPasswordTextView: TextView
    private lateinit var registerLinkTextView: TextView
    private lateinit var showPassword: ImageView
    private var isPasswordVisible = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize views
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.loginBtn)
        emailEmptyTextView = findViewById(R.id.Emailempty)
        passEmptyTextView = findViewById(R.id.PassEmpty)
        passLengthTextView = findViewById(R.id.PassLength)
        msgCheckTextView = findViewById(R.id.msgCheck)
        msgErrorTextView = findViewById(R.id.msgError)
        tvRegisterLink = findViewById(R.id.tvRegisterLink)
        registerLinkTextView = findViewById(R.id.RegisterLink)
        showPassword = findViewById(R.id.showPassword)

        // Hide all error messages initially
        hideAllErrorMessages()

        // Set click listeners
        loginButton.setOnClickListener { validateAndLogin() }
        tvRegisterLink.setOnClickListener { navigateToSignUp() }
        showPassword.setOnClickListener { togglePasswordVisibility() }
    }

    private fun hideAllErrorMessages() {
        emailEmptyTextView.visibility = View.GONE
        passEmptyTextView.visibility = View.GONE
        passLengthTextView.visibility = View.GONE
        msgCheckTextView.visibility = View.GONE
        msgErrorTextView.visibility = View.GONE
    }


    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
            showPassword.setImageResource(R.drawable.show_password_icon)
        } else {
            // Show password
            passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            showPassword.setImageResource(R.drawable.hide_password_icon)
        }
        isPasswordVisible = !isPasswordVisible
        // Move cursor to end
        passwordEditText.setSelection(passwordEditText.text.length)
    }

    private fun validateAndLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        hideAllErrorMessages()

        if (email.isEmpty()) {
            emailEmptyTextView.visibility = View.VISIBLE
            return
        }

        if (password.isEmpty()) {
            passEmptyTextView.visibility = View.VISIBLE
            return
        }

        if (password.length < 6) {
            passLengthTextView.visibility = View.VISIBLE
            return
        }

        // Simulate login validation
        if (email == "user@example.com" && password == "password123") {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            msgErrorTextView.visibility = View.VISIBLE
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Information")
            .setMessage("Are you sure you want to go back?")
            .setPositiveButton("Yes") { dialog, which ->
                Toast.makeText(this, "Get started now!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LandingPageActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

}