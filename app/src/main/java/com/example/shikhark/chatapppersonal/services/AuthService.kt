package com.example.shikhark.chatapppersonal.services

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.shikhark.chatapppersonal.utils.CustomDialog
import com.example.shikhark.chatapppersonal.utils.URL_REGISTER
import org.jetbrains.anko.toast
import org.json.JSONObject

object AuthService {

    fun registerUser(context: Context, email:String,password:String,complete:(Boolean)->Unit){
        val url= URL_REGISTER
        //creating JSON body
        val jsonBody=JSONObject()
        jsonBody.put("email",email)
        jsonBody.put("password",password)

        val requestBody=jsonBody.toString()
        val registerRequest=object:StringRequest(Request.Method.POST,url, Response.Listener {_->
            println("SUCCESS: User successfully created")
            complete(true)
        },Response.ErrorListener{error->
            complete(false)
            println("Error: Could not register User $error")
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        Volley.newRequestQueue(context).add(registerRequest)

    }

}