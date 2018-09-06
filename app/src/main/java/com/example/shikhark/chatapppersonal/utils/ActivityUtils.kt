package com.example.shikhark.chatapppersonal.utils

import android.app.Activity
import android.content.Context
import me.anwarshahriar.calligrapher.Calligrapher

fun Activity.loadFonts(fontName:String){
    val calligrapher=Calligrapher(this)
    calligrapher.setFont(this,"fonts/$fontName",true)
}

fun Activity.storePreferenceBoolean(key:String,value:Boolean){
    val sharedPref=this.getPreferences(Context.MODE_PRIVATE)
    with (sharedPref.edit()) {
        putBoolean(key, value)
        commit()
    }

}

fun Activity.editPreferenceBoolean(key:String,value:Boolean) {
    val pref = this.getSharedPreferences(key, Context.MODE_PRIVATE)
    pref.edit().putBoolean(key, value).apply()
}

fun Activity.readPreferenceBoolean(key:String):Boolean{
    val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
    return sharedPref.getBoolean(key, false)
}