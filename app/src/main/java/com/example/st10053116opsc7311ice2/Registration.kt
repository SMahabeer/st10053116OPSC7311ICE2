package com.example.st10053116opsc7311ice2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.ktx.Firebase

class Registration : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    private lateinit var btnSignUp: Button
    private lateinit var tvRedirectLogin: TextView

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        etEmail = findViewById(R.id.etSEmailAddress)
        etConfPass = findViewById(R.id.etSConfPassword)
        etPass = findViewById(R.id.etSPassword)
        btnSignUp = findViewById(R.id.btnSSigned)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

        // Initialising auth object
        auth = Firebase.auth
        val db = com.google.firebase.Firebase.firestore

        // switching from signUp Activity to Login Activity
        /*tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/

        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPass.text.toString()
            val confirmPassword = etConfPass.text.toString()

            // check pass
            if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()

            }

            if (password != confirmPassword) {
                Toast.makeText(
                    this,
                    "Password and Confirm Password do not match",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            if (password.equals(confirmPassword)) {
                signUpUser(email, password)
            }
        }
    }

    private fun signUpUser(email: String, password: String) {

        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, CreateProfile::class.java)
                intent.putExtra("username", email)

                startActivity(intent)
            } else {
                Toast.makeText(this, "Sign Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}