package com.example.shikhark.chatapppersonal.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.example.shikhark.chatapppersonal.R
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.custom_dialog.view.*


class CustomDialog (val title:String,val subtext:String, val context: Context,val cancellable:Boolean=false){

    lateinit var dialog:AlertDialog

    fun init_dialog(){
        val inflater = (context as Activity).layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog, null)
        dialog = AlertDialog.Builder(context)
                        .setView(dialogView)
                        .create()
        dialog.setCancelable(cancellable)

        dialogView.title.text=title
        dialogView.subtext.text=subtext
    }
}

