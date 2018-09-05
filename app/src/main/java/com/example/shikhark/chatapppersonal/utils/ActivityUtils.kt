package com.example.shikhark.chatapppersonal.utils

import android.app.Activity
import me.anwarshahriar.calligrapher.Calligrapher

fun Activity.loadFonts(fontName:String){
    val calligrapher=Calligrapher(this)
    calligrapher.setFont(this,"fonts/$fontName",true)
}