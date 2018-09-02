package com.example.shikhark.chatapppersonal.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.utils.SPLASH_DELAY
import com.example.shikhark.chatapppersonal.utils.startActivityAsRoot

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            startActivityAsRoot(Intent(this,MainActivity::class.java))
        }, SPLASH_DELAY.toLong())
    }
}
