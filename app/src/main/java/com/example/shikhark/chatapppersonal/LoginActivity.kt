package com.example.shikhark.chatapppersonal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        loginBtn.setOnClickListener{
            toast("Login Clicked")
        }
        signUpBtn.setOnClickListener {
            toast("Sign up Clicked!")
        }
    }
}
