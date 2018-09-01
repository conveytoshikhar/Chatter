package com.example.shikhark.chatapppersonal.controller

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.services.AuthService
import com.example.shikhark.chatapppersonal.utils.CustomDialog
import com.example.shikhark.chatapppersonal.utils.startActivityAsRoot
import kotlinx.android.synthetic.main.activity_create_user.*
import org.jetbrains.anko.toast
import java.util.*

class CreateUserActivity : AppCompatActivity() {
    private val random=Random()
    lateinit var authToken:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        var userAvatar="profileDefault"


        signUpBtn.setOnClickListener {
            val customDialog=CustomDialog(
                    title = "Creating User",
                    subtext="Please Wait",
                    context=this,
                    cancellable = false
            )
            customDialog.init_dialog()
            val registerDialog=customDialog.dialog
            registerDialog.show()
            AuthService.registerUser(this,userEmail.text.toString(),userPassword.text.toString()) { complete->
                if(complete){
                    AuthService.loginUser(this,userEmail.text.toString(),userPassword.text.toString()){
                        if(complete){
                            val intent= Intent(this,MainActivity::class.java)
                            startActivityAsRoot(intent)
                        }else{
                            toast("bad request")
                        }
                    }
                    registerDialog.dismiss()
                }else{
                    registerDialog.dismiss()
                }
            }
        }



        userImage.setOnClickListener {
            val typeOfImage=random.nextInt(2)
            val imageNumber=random.nextInt(28)

            userAvatar=if(typeOfImage==0) "light$imageNumber" else "dark$imageNumber"

            val resourceId=resources.getIdentifier(userAvatar,"drawable",packageName)
            userImage.setImageResource(resourceId)




        }

        generateBackgroundColor.setOnClickListener {
            toast("Background Changed")
            val(red,green,blue)=generateRandomColor()
            val color = Color.argb(255, red, green, blue)
            userImage.setBackgroundColor(color)
            toast("Red:$red Green $green Blue $blue")
        }


    }

    fun generateRandomColor():Triple<Int,Int,Int>{
        val red=random.nextInt(255)
        val green=random.nextInt(255)
        val blue=random.nextInt(255)
        return Triple(red,green,blue)
    }
}
