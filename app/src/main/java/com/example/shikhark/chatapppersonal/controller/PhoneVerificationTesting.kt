package com.example.shikhark.chatapppersonal.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.services.UserDataService
import com.example.shikhark.chatapppersonal.utils.SPLASH_DELAY
import com.example.shikhark.chatapppersonal.utils.loadFonts
import com.example.shikhark.chatapppersonal.utils.startActivityAsRoot
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import io.rmiri.buttonloading.ButtonLoading
import kotlinx.android.synthetic.main.activity_phone_verification_testing.*
import org.jetbrains.anko.act
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit

class PhoneVerificationTesting : AppCompatActivity() {
    lateinit var phoneNumber:String
    lateinit var mCallbacks: OnVerificationStateChangedCallbacks
    val activity=this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification_testing)

        loadFonts("Raleway-Light.ttf")




        countryCode.registerCarrierNumberEditText(number)
        proceed.setOnButtonLoadingListener(object : ButtonLoading.OnButtonLoadingListener {
            override fun onClick() {
                phoneNumber=countryCode.fullNumberWithPlus
                toast("Button Clicked. $phoneNumber ")
                val button=this
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        activity,               // Activity (for callback binding)
                        object:OnVerificationStateChangedCallbacks(){
                            override fun onVerificationCompleted(phoneCredential: PhoneAuthCredential?) {
                                toast("Successfully verified")
                                button.onFinish()
                            }

                            override fun onVerificationFailed(p0: FirebaseException?) {
                                toast("Not verified,please try again")
                                button.onFinish()

                            }

                        })       // OnVerificationStateChangedCallbacks


            }

            override fun onStart() {
            }

            override fun onFinish() {
                val intent=Intent(activity, MainActivity::class.java)
                startActivityAsRoot(intent)
                finish()
            }
        })


    }


}


