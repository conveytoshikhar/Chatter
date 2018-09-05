package com.example.shikhark.chatapppersonal.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.utils.SPLASH_DELAY
import com.example.shikhark.chatapppersonal.utils.loadFonts
import com.example.shikhark.chatapppersonal.utils.startActivityAsRoot
import io.rmiri.buttonloading.ButtonLoading
import kotlinx.android.synthetic.main.activity_sign_up_opening.*
import kotlinx.android.synthetic.main.activity_splash.*
import android.os.CountDownTimer




class SignUpOpening : AppCompatActivity() {
    val handler= Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_opening)
        this.loadFonts("Raleway-Light.ttf")
        val context=this
        proceed.setOnButtonLoadingListener(object : ButtonLoading.OnButtonLoadingListener {
            override fun onClick() {


            }

            override fun onStart() {
                val timer = object : CountDownTimer(SPLASH_DELAY.toLong(), 1000) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        startActivity(Intent(context,PhoneVerificationTesting::class.java))
                        finish()
                    }
                }
                timer.start()
            }

            override fun onFinish() {

            }
        })
    }
}
