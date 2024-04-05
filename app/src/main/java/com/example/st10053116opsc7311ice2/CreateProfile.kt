package com.example.st10053116opsc7311ice2

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class CreateProfile : AppCompatActivity() {

    private lateinit var etQual: EditText
    private lateinit var etStudentNumber: EditText
    private lateinit var etQualificationYear: EditText
    private lateinit var etCampusName: EditText
    private lateinit var btnCreateProfile: Button
    private val db = Firebase.firestore

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        etQual = findViewById(R.id.etQualification)
        etStudentNumber = findViewById(R.id.etStudentNumber)
        etQualificationYear = findViewById(R.id.etQualificationYear)
        etCampusName = findViewById(R.id.etCampusName)
        btnCreateProfile = findViewById(R.id.btnCreateMyProfile)

        btnCreateProfile.setOnClickListener{
            //val userID = auth.uid.toString()
            val username = intent.getStringExtra("username")
            val qualification = etQual.text
            val qualYear = etQualificationYear.text
            val campus = etCampusName.text
            val studNum = etStudentNumber.text

            //Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show()

            if (username != null && qualification != null && qualYear != null && campus != null && studNum != null) {
                createUser(username, qualification.toString(), qualYear.toString(), campus.toString(), studNum.toString())
                val intent = Intent(this, ViewProfile::class.java)
                startActivity(intent)
            }


        }
    }

    private fun createUser(username: String, qualification: String, qualYear: String, campus: String, studNum: String)
    {
        val user = hashMapOf(
            //"userID" to userID,
            "username" to username,
            "qualification" to qualification,
            "qualYear" to qualYear,
            "campus" to campus,
            "studNumber" to studNum
        )

        intent.getStringExtra("username")?.let {
            db.collection(it)
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }

}