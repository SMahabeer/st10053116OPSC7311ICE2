package com.example.st10053116opsc7311ice2

import android.text.Editable

data class ProfileData(
    val userID: String,
    val username: String?,
    val qualification: String,
    val qualYear: Editable,
    val campus: String,
    val studNumber: String
    //val profilePic: Image
)
