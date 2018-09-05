package com.example.shikhark.chatapppersonal.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object UserDataService {
    var mAuth:FirebaseAuth=FirebaseAuth.getInstance()
    var currentUser:FirebaseUser?=null
    init{
        currentUser= mAuth.currentUser
    }
    override fun toString(): String {
        return "${currentUser.toString()}"

    }
}