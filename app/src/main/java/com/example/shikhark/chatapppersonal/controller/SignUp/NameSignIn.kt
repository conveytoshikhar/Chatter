package com.example.shikhark.chatapppersonal.controller.SignUp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.services.UserDataService
import com.example.shikhark.chatapppersonal.utils.loadFonts
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_name_sign_in.*
import com.google.firebase.auth.UserProfileChangeRequest
import org.jetbrains.anko.toast


class NameSignIn : AppCompatActivity() {
    lateinit var firstName:String
    lateinit var lastName:String
    lateinit var mAuthListener:FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_sign_in)
        this.loadFonts("Raleway-Light.ttf")

        proceed.setOnClickListener {
            firstName=firstNameUser.text.toString()

            toast(firstName)
            val profile=UserProfileChangeRequest.Builder()
                    .setDisplayName(firstName)
                    .build()
            UserDataService.currentUser!!.updateProfile(profile)
                    .addOnCompleteListener {
                        if(it.isSuccessful)toast("New name of $firstName added to user")
                        else toast("Nah not  name of $firstName added to user")
                        val intent=Intent()
                        val text="Blah returned "
                        intent.data = Uri.parse(text)
                        setResult(Activity.RESULT_OK)
                        finish()
                    }





        }
    }





}
