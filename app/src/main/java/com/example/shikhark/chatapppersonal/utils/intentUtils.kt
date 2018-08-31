package com.example.shikhark.chatapppersonal.utils

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity



fun Activity.startActivityAsRoot(intent: Intent){
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
}