package com.example.shikhark.chatapppersonal.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shikhark.chatapppersonal.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import kotlinx.android.synthetic.main.activity_phone_verification_testing.*
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit

class PhoneVerificationTesting : AppCompatActivity() {
    lateinit var phoneNumber:String
    lateinit var mCallbacks: OnVerificationStateChangedCallbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification_testing)




        mCallbacks= object:OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
                toast("Verified")
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
                toast("Not verified")
            }

        }
        send.setOnClickListener {
            phoneNumber=number.text.toString()
            toast("Button Clicked. ")
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks)       // OnVerificationStateChangedCallbacks


        }


    }
}
