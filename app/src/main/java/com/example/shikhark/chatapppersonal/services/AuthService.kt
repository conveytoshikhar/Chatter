package com.example.shikhark.chatapppersonal.services

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.shikhark.chatapppersonal.utils.CustomDialog
import com.example.shikhark.chatapppersonal.utils.URL_ADD_USER
import com.example.shikhark.chatapppersonal.utils.URL_LOGIN
import com.example.shikhark.chatapppersonal.utils.URL_REGISTER
import org.jetbrains.anko.toast
import org.json.JSONObject

object AuthService {


    var isLoggedIn=false
    var authToken:String="0"
    var userEmail:String=""
    fun registerUser(context: Context, email:String,password:String,complete:(Boolean)->Unit){
        userEmail=email
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

    fun loginUser(context:Context,email:String,password:String,complete:(Boolean)->Unit){
        val url= URL_LOGIN
        //creating JSON body
        val jsonBody=JSONObject()
        jsonBody.put("email",email)
        jsonBody.put("password",password)

        val loginBody=jsonBody.toString()

        val loginRequest=object:JsonObjectRequest(Request.Method.POST,url,null,Response.Listener {jsonObject->
            println("Response: $jsonObject")


            try{
                authToken=jsonObject.getString("token")
                userEmail=jsonObject.getString("user")
                isLoggedIn=true


                complete(true)
            }catch(e:Exception){
                val intent=(context as Activity).intent
                (context).finish()
                (context).startActivity(intent)
            }


        },Response.ErrorListener {error->
            println("Error: Failed to retrieve information $error")
            complete(false)
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return loginBody.toByteArray()
            }
        }
        Volley.newRequestQueue(context).add(loginRequest)
    }



    fun  createUser(context:Context, name:String,email:String,avatarName:String,avatarColor:String,complete: (Boolean) -> Unit){
        val url= URL_ADD_USER
        //creating JSON body
        val jsonBody=JSONObject()
        jsonBody.put("name",name)
        jsonBody.put("email",email)
        jsonBody.put("avatarName",avatarName)
        jsonBody.put("avatarColor",avatarColor)
        val addUserBody=jsonBody.toString()

        val addUserRequest=object:JsonObjectRequest(Method.POST,url,null,Response.Listener {jsonObject->


            try{
                context.toast(jsonObject.toString())
                UserDataService.name=jsonObject.getString("name")
                UserDataService.email=jsonObject.getString("email")
                UserDataService.avatarName=jsonObject.getString("avatarName")
                UserDataService.avatarColor=jsonObject.getString("avatarColor")
                UserDataService.id=jsonObject.getString("_id")
                complete(true)
            }catch (e:Exception){
                println("Error With request ")
            }


        },Response.ErrorListener {error->
            context.toast("Could not add user")
            println("Error: Could not add user $error")
            complete(false)

        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return addUserBody.toByteArray()
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers=HashMap<String,String>()
                headers["Authorization"] = "Bearer $authToken"
                return headers

            }
        }

        Volley.newRequestQueue(context).add(addUserRequest)




    }
}