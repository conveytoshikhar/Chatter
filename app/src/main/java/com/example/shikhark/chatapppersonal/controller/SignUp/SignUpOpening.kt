package com.example.shikhark.chatapppersonal.controller.SignUp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.utils.SPLASH_DELAY
import com.example.shikhark.chatapppersonal.utils.loadFonts
import io.rmiri.buttonloading.ButtonLoading
import kotlinx.android.synthetic.main.activity_sign_up_opening.*
import android.os.CountDownTimer
import com.example.shikhark.chatapppersonal.utils.CustomDialog


class SignUpOpening : AppCompatActivity() {
    val handler= Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_opening)
        this.loadFonts("Raleway-Light.ttf")
        val context=this
        proceed.setOnClickListener {
            val dialog=CustomDialog(context).getInstace()
            dialog.show()
            val timer = object : CountDownTimer(SPLASH_DELAY.toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    dialog.dismiss()
                    startActivity(Intent(context, EmailSignUp::class.java))
                    finish()
                }
            }
            timer.start()
        }

    }
}
