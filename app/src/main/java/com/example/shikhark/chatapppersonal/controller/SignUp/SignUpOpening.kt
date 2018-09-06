package com.example.shikhark.chatapppersonal.controller.SignUp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.shikhark.chatapppersonal.R
import io.rmiri.buttonloading.ButtonLoading
import kotlinx.android.synthetic.main.activity_sign_up_opening.*
import android.os.CountDownTimer
import android.preference.PreferenceManager
import com.example.shikhark.chatapppersonal.controller.MainActivity
import com.example.shikhark.chatapppersonal.services.UserDataService.currentUser
import com.example.shikhark.chatapppersonal.utils.*
import org.jetbrains.anko.toast


class SignUpOpening : AppCompatActivity() {
    var state=-1
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
                    continueRegistration()
                }
            }
            timer.start()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            continueRegistration()
        }else{
            state-=1
            toast("Some error occured, finsihing")
        }




    }


    fun continueRegistration(){
        state+=1
        when(state){
            0->goToEmailActivity()
            1->goToPhoneActivity()
            2->goToMainActivity()
        }
    }
    fun goToEmailActivity(){
        startActivityForResult(Intent(this,EmailSignUp::class.java), SignUpRequestCode)
    }

    fun goToPhoneActivity(){
        if(currentUser!!.isEmailVerified)
            startActivityForResult(Intent(this,PhoneVerificationTesting::class.java), SignUpRequestCode)
        else {
            state -= 1
            continueRegistration()
        }
    }

    fun goToMainActivity(){
        val pref=PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()
        editor.putBoolean("LandingFinished",true)
        editor.apply()
        println("Starting Main-Activity")
        startActivityAsRoot(Intent(this,MainActivity::class.java))
    }
}
