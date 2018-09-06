package com.example.shikhark.chatapppersonal.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.shikhark.chatapppersonal.R
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.custom_dialog.view.*


class CustomDialog (val context:Context){

    lateinit var dialog:AlertDialog

    fun getInstace():AlertDialog{
        val inflater = (context as Activity).layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog, null)
        dialog = AlertDialog.Builder(context)
                        .setView(dialogView)
                        .create()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        return dialog

    }
}

