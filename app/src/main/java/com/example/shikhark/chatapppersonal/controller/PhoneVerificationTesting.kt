package com.example.shikhark.chatapppersonal.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shikhark.chatapppersonal.R
import kotlinx.android.synthetic.main.activity_phone_verification_testing.*
import org.jetbrains.anko.toast

class PhoneVerificationTesting : AppCompatActivity() {
    lateinit var phoneNumber:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification_testing)

        phoneNumber=number.text.toString()


        send.setOnClickListener {
            toast("Button Clicked. ")
        }


    }
}
