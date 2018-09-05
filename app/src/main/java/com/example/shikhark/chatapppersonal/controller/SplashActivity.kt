package com.example.shikhark.chatapppersonal.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.daimajia.androidanimations.library.specials.`in`.DropOutAnimator
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.services.UserDataService.currentUser
import com.example.shikhark.chatapppersonal.utils.SPLASH_DELAY
import com.example.shikhark.chatapppersonal.utils.startActivityAsRoot
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        YoYo.with(Techniques.Bounce)
                .duration(2000)
                .playOn(logo)
        Handler().postDelayed({
            if(currentUser==null) startActivityAsRoot(Intent(this,PhoneVerificationTesting::class.java))
            else {
                startActivityAsRoot(Intent(this,MainActivity::class.java))
            }

        }, (SPLASH_DELAY).toLong())
    }
}
