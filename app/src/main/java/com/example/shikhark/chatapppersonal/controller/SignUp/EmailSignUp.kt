package com.example.shikhark.chatapppersonal.controller.SignUp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.services.UserDataService.currentUser
import com.example.shikhark.chatapppersonal.services.UserDataService.mAuth
import com.example.shikhark.chatapppersonal.utils.CustomDialog
import com.example.shikhark.chatapppersonal.utils.loadFonts
import com.example.shikhark.chatapppersonal.utils.startActivityAsRoot
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.android.synthetic.main.activity_email_sign_up.*
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.toast

class EmailSignUp : AppCompatActivity() {
    lateinit var email: String
    lateinit var password: String
    lateinit var dialog:AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_sign_up)
        this.loadFonts("Raleway-Light.ttf")
        dialog=CustomDialog(this).getInstace()

        proceed.setOnClickListener {
            email = userEmail.text.toString()
            password = userPassword.text.toString()
            dialog.show()
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {//new user
                            currentUser= mAuth.currentUser
                            dialog.dismiss()
                            startActivityForResult(Intent(this,NameSignIn::class.java),1000)

                        }else {
                            when {
                                it.exception is FirebaseAuthUserCollisionException -> sendConfirmationEmail(email,password)
                                it.exception is FirebaseAuthWeakPasswordException -> {
                                    toast("Password too weak, should be 5 or more characters "+". Please try again. ")
                                    dialog.dismiss()
                                }

                                else -> {
                                    toast("Some other exception"+". Please try again. ")
                                    dialog.dismiss()
                                }
                            }

                        }
                    }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== Activity.RESULT_OK){
            sendConfirmationEmail(email,password)
        }else{
            toast("An Error occured in registering user with the email. "+". Please try again. ")
            dialog.dismiss()
        }
    }

    fun sendConfirmationEmail(email:String,password: String){
        mAuth.currentUser!!.reload()
                .addOnCompleteListener {
                    currentUser=mAuth.currentUser
                    val isEmailVerified= currentUser?.isEmailVerified
                    currentUser?.let {
                        if(isEmailVerified!!) {
                            signInWithEmailAndPassword(email,password)
                        }else {//email not verified
                            dialog.dismiss()
                            currentUser!!.sendEmailVerification()
                                    .addOnCompleteListener {
                                        if(it.isSuccessful)
                                            toast("Verification email sent to ${currentUser?.email}")
                                        else{
                                            toast("Some unknown error occured"+". Please try again. ")
                                            dialog.dismiss()
                                        }
                                    }
                        }

                    }
                }

    }


    fun signInWithEmailAndPassword(email:String,password: String){
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        val pref=PreferenceManager.getDefaultSharedPreferences(this)
                        val editor=pref.edit()
                        editor.putString("SignUp",SignUpStages.convertEnumToString(SignUpStages.Email))
                        editor.apply()
                        toast("Email verified")
                        setResult(Activity.RESULT_OK)
                        finish()
                    }else{
                        toast(it.exception!!.message.toString()+". Please try again. ")
                        dialog.dismiss()

                    }
                }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }




}


