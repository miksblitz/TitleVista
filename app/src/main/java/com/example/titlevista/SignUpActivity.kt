package com.example.titlevista

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class SignUpActivity : AppCompatActivity() {

    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var showPassword: ImageView
    private lateinit var showConfirmPassword: ImageView
    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize views
        passwordEditText = findViewById(R.id.passwordInput)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordInput)
        showPassword = findViewById(R.id.showPassword)
        showConfirmPassword = findViewById(R.id.showPassword2)

        // Set click listeners for toggling visibility
        showPassword.setOnClickListener { togglePasswordVisibility() }
        showConfirmPassword.setOnClickListener { toggleConfirmPasswordVisibility() }


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

    private fun toggleConfirmPasswordVisibility() {
        if (isConfirmPasswordVisible) {
            // Hide confirm password
            confirmPasswordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
            showConfirmPassword.setImageResource(R.drawable.show_password_icon)
        } else {
            // Show confirm password
            confirmPasswordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            showConfirmPassword.setImageResource(R.drawable.hide_password_icon)
        }
        isConfirmPasswordVisible = !isConfirmPasswordVisible
        // Move cursor to end
        confirmPasswordEditText.setSelection(confirmPasswordEditText.text.length)
    }

    private fun validateAndSignUp() {
        val usernameEditText: EditText = findViewById(R.id.usernameInput)
        val emailEditText: EditText = findViewById(R.id.emailInput)
        val username = usernameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val confirmPassword = confirmPasswordEditText.text.toString().trim()

        // Validation
        if (username.isEmpty()) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show()
            return
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // Save to internal storage
        val userData = "$username,$email,$password\n"
        try {
            val fileOutputStream = openFileOutput("user_data_internal.txt", MODE_APPEND)
            fileOutputStream.write(userData.toByteArray())
            fileOutputStream.close()
            Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show()
            finish() // Close the activity
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error saving user data", Toast.LENGTH_SHORT).show()
        }
    }
}