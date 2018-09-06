package com.example.shikhark.chatapppersonal.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.controller.SignUp.EmailSignUp
import com.example.shikhark.chatapppersonal.controller.SignUp.NameSignIn
import com.example.shikhark.chatapppersonal.controller.SignUp.SignUpOpening
import com.example.shikhark.chatapppersonal.services.UserDataService.currentUser
import com.example.shikhark.chatapppersonal.utils.SPLASH_DELAY
import com.example.shikhark.chatapppersonal.utils.startActivityAsRoot
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        YoYo.with(Techniques.Bounce)
                .duration(SPLASH_DELAY.toLong())
                .playOn(logo)
        Handler().postDelayed({

            if(currentUser!=null) startActivityAsRoot(Intent(this, SignUpOpening::class.java))
            else {
                startActivityAsRoot(Intent(this,MainActivity::class.java))
            }

        }, (SPLASH_DELAY).toLong())
    }
}
