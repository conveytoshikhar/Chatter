package com.example.shikhark.chatapppersonal.controller.SignUp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.controller.MainActivity
import com.example.shikhark.chatapppersonal.services.UserDataService.currentUser
import com.example.shikhark.chatapppersonal.services.UserDataService.mAuth
import com.example.shikhark.chatapppersonal.utils.CustomDialog
import com.example.shikhark.chatapppersonal.utils.loadFonts
import com.example.shikhark.chatapppersonal.utils.startActivityAsRoot
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_phone_verification_testing.*


class PhoneVerificationTesting : AppCompatActivity() {
    var phoneNumber="+16505554567"
    lateinit var dialog:android.app.AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification_testing)
        loadFonts("Raleway-Light.ttf")

        dialog=CustomDialog(this).getInstace()



        countryCode.registerCarrierNumberEditText(number)
        proceed.setOnClickListener {
            dialog.show()
            phoneNumber = countryCode.fullNumberWithPlus
            val smsCode = "123456"
            dialog.show()
            val firebaseAuth = FirebaseAuth.getInstance()
            val firebaseAuthSetting = firebaseAuth.firebaseAuthSettings


            firebaseAuthSetting.setAutoRetrievedSmsCodeForPhoneNumber(phoneNumber, smsCode)



            val phoneAuthProvider = PhoneAuthProvider.getInstance()
            phoneAuthProvider.verifyPhoneNumber(
                    phoneNumber,
                    60L,
                    TimeUnit.SECONDS,
                    this, /* activity */
                    object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        override fun onVerificationFailed(p0: FirebaseException?) {
                            toast("Failed,try again")
                            dialog.dismiss()
                            setResult(Activity.RESULT_CANCELED)
                        }

                        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                            toast("Verified")
                            linkCredential(credential)
                        }

                        // ...
                    })
        }

            // OnVerificationStateChangedCallbacks

    }

    fun linkCredential(phoneCredential:PhoneAuthCredential){
        mAuth.currentUser!!.linkWithCredential(phoneCredential)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        currentUser=it.result.user
                        signInWithCredential(phoneCredential)
                    }else{
                        toast(it.exception!!.message.toString())
                        toast("Please try again. ")
                        dialog.dismiss()
                    }
                }
    }


    fun signInWithCredential(credential: PhoneAuthCredential){
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        dialog.dismiss()
                        val pref=PreferenceManager.getDefaultSharedPreferences(this)
                        val editor=pref.edit()
                        editor.putString("SignUp",SignUpStages.convertEnumToString(SignUpStages.Phone))
                        editor.apply()
                        println("Successful sign in using ${currentUser?.email} and ${currentUser?.phoneNumber}")
                        setResult(Activity.RESULT_OK)
                        finish()
                    }else{
                        toast("Please try again. ")
                        dialog.dismiss()
                    }
                }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}


