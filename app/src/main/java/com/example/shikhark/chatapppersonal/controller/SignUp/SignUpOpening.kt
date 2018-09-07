package com.example.shikhark.chatapppersonal.controller.SignUp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shikhark.chatapppersonal.R
import kotlinx.android.synthetic.main.activity_sign_up_opening.*
import android.os.CountDownTimer
import android.preference.PreferenceManager
import com.example.shikhark.chatapppersonal.controller.MainActivity
import com.example.shikhark.chatapppersonal.utils.*
import org.jetbrains.anko.toast


class SignUpOpening : AppCompatActivity() {
    lateinit var state:SignUpStages
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_opening)
        this.loadFonts("Raleway-Light.ttf")

        val context = this
        proceed.setOnClickListener {
            val dialog = CustomDialog(context).getInstace()
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


    override fun onResume() {
        super.onResume()
        proceed.isClickable=true
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            continueRegistration()
        } else {
            toast("Some error occured, finsihing")
        }


    }


    fun continueRegistration() {
        state=getRegistationState()
        println("SignUpActivity: On continue called with state $state")
        proceed.isClickable=false
        when (state) {
            SignUpStages.Welcome -> goToEmailActivity()
            SignUpStages.Email -> goToPhoneActivity()
            SignUpStages.Phone -> goToMainActivity()
            else->finish()
        }
    }

    fun goToEmailActivity() {
        startActivityForResult(Intent(this, EmailSignUp::class.java), SignUpRequestCode)
    }

    fun goToPhoneActivity() {
        startActivityForResult(Intent(this, PhoneVerificationTesting::class.java), SignUpRequestCode)
    }

    fun goToMainActivity() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.putBoolean("LandingFinished", true)
        editor.apply()
        println("Starting Main-Activity")
        startActivityAsRoot(Intent(this, MainActivity::class.java))
    }

    fun getRegistationState():SignUpStages{
        val pref=PreferenceManager.getDefaultSharedPreferences(this)
        val stateString=pref.getString("SignUp","Welcome")
        return SignUpStages.convertStringToEnum(stateString)
    }


}



enum class SignUpStages {
    Welcome,
    Email,
    Phone,
    MainActivity;
    companion object {
        fun convertStringToEnum(value:String):SignUpStages{
            return when(value){
                "Welcome"->SignUpStages.Welcome
                "Email"->SignUpStages.Email
                "Phone"->SignUpStages.Phone
                "MainActivity"->SignUpStages.MainActivity
                else->SignUpStages.MainActivity
            }

        }

        fun convertEnumToString(obj:SignUpStages):String {
            return when (obj) {
                SignUpStages.Welcome -> "Welcome"
                SignUpStages.Email -> "Email"
                SignUpStages.Phone -> "Phone"
                SignUpStages.MainActivity -> "MainActivity"
            }
        }
    }
}



