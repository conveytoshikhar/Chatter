package com.example.shikhark.chatapppersonal.controller.SignUp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.services.UserDataService.currentUser
import com.example.shikhark.chatapppersonal.services.UserDataService.mAuth
import com.example.shikhark.chatapppersonal.utils.loadFonts
import com.example.shikhark.chatapppersonal.utils.startActivityAsRoot
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.android.synthetic.main.activity_email_sign_up.*
import org.jetbrains.anko.toast

class EmailSignUp : AppCompatActivity() {
    lateinit var email: String
    lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_sign_up)
        this.loadFonts("Raleway-Light.ttf")


        proceed.setOnClickListener {
            email = userEmail.text.toString()
            password = userPassword.text.toString()
            toast("Sending email")

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {//new user
                            currentUser= mAuth.currentUser
                            startActivityForResult(Intent(this,NameSignIn::class.java),1000)

                        }else {
                            when {
                                it.exception is FirebaseAuthUserCollisionException -> sendConfirmationEmail(email,password)
                                it.exception is FirebaseAuthWeakPasswordException -> toast("Password too weak, should be blah digits ")
                                else -> toast("Some other exception ")
                            }

                        }
                    }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== Activity.RESULT_OK){
            sendConfirmationEmail(email,password)
        }else{
            toast("An Error occured in registering user with the email. ")
        }
    }

    fun sendConfirmationEmail(email:String,password: String){
        mAuth.currentUser!!.reload()
                .addOnCompleteListener {
                    currentUser=mAuth.currentUser
                    val isEmailVerified= currentUser?.isEmailVerified
                    currentUser?.let {user->
                        if(isEmailVerified!!) {
                            signInWithEmailAndPassword(email,password)
                        }else {//email not verified
                            currentUser!!.sendEmailVerification()
                                    .addOnCompleteListener {
                                        if(it.isSuccessful)
                                            toast("Verification email sent to ${currentUser?.email}")
                                        else{
                                            toast("Some unknown error occured")
                                        }
                                    }
                        }

                    }
                }

    }


    fun signInWithEmailAndPassword(email:String,password:String) {
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        currentUser= mAuth.currentUser
                        startActivityAsRoot(Intent(this,PhoneVerificationTesting::class.java))
                    }else{
                        toast(it.exception!!.message.toString())
                    }
                }

    }




}


