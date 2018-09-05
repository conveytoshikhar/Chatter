package com.example.shikhark.chatapppersonal.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.utils.loadFonts
import io.rmiri.buttonloading.ButtonLoading
import kotlinx.android.synthetic.main.activity_sign_up_opening.*


class SignUpOpening : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_opening)
        this.loadFonts("Raleway-Light.ttf")

        proceed.setOnButtonLoadingListener(object : ButtonLoading.OnButtonLoadingListener {
            override fun onClick() {
                //...
            }

            override fun onStart() {
                //...
            }

            override fun onFinish() {
                //...
            }
        })
    }
}
