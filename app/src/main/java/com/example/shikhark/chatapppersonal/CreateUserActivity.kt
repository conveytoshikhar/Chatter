package com.example.shikhark.chatapppersonal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_user.*
import org.jetbrains.anko.toast

class CreateUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)


        signUpBtn.setOnClickListener {
            toast("Sign up btn clicked")
        }
        userImage.setOnClickListener {
            toast("Image clicked")
        }

        generateBackgroundColor.setOnClickListener {
            toast("Background Changed")
        }


    }
}
