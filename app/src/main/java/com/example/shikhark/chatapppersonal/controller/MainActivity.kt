package com.example.shikhark.chatapppersonal.controller

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.services.UserDataService
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(){
    var loginSuccess=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        loginSuccess=intent.getBooleanExtra("login",false)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()



        initUI()

        loginBtnNavHeader.setOnClickListener {
            val loginIntent= Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        addChannelButton.setOnClickListener {
            toast("Working second button")
        }

        sendMessageButton.setOnClickListener{
            toast("Message Btn Clicked.")
        }

    }


    fun initUI(){
        if (loginSuccess){
            mainChannelName.text=UserDataService.toString()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
