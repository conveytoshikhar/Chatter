package com.example.shikhark.chatapppersonal.controller

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shikhark.chatapppersonal.R
import com.example.shikhark.chatapppersonal.services.AuthService
import com.example.shikhark.chatapppersonal.utils.CustomDialog
import com.example.shikhark.chatapppersonal.utils.SignUpErrorDecode
import com.example.shikhark.chatapppersonal.utils.startActivityAsRoot
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.jetbrains.anko.toast
import java.util.*

class CreateUserActivity : AppCompatActivity() {
    private val random=Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        var userAvatar="profileDefault"
        var avatarColor="0 0 0 1"


        signUpBtn.setOnClickListener {
            val customDialog=CustomDialog(

                    context=this
            )
            val registerDialog=customDialog.dialog
            registerDialog.show()


            val userEmail=userEmail.text.toString()
            val userName=userName.text.toString()
            val password=userPassword.text.toString()


            var validated=false



            if( SignUpErrorDecode.checkEmailValidity(userEmail).first &&
                    SignUpErrorDecode.checkPasswod(password,password).first &&
                    SignUpErrorDecode.checkUserName(userName).first){
                validated=true
            }


            if(validated){
                AuthService.registerUser(this,userEmail,password) { registerSuccess->
                    if(registerSuccess){
                        AuthService.loginUser(this,userEmail,password){loginSuccess->
                            if(loginSuccess){
                                AuthService.createUser(this,userName,userEmail,userAvatar,avatarColor){createUserSuccess->
                                    if(createUserSuccess){
                                        val intent= Intent(this,MainActivity::class.java)
                                        intent.putExtra("login",true)
                                        startActivityAsRoot(intent)
                                        finish()
                                    }else{
                                        finish()
                                    }

                                }
                            }else{
                                finish()
                            }
                        }
                    }else{
                    }
                }
            }else{ //fix error after getting it
                registerDialog.dismiss()
                toast("There is some error ")
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
            val(red,green,blue)=generateRandomColor()
            avatarColor="$red $green $blue 1"
            val color = Color.argb(255, red, green, blue)
            userImage.setBackgroundColor(color)
        }


    }

    fun generateRandomColor():Triple<Int,Int,Int>{
        val red=random.nextInt(255)
        val green=random.nextInt(255)
        val blue=random.nextInt(255)
        return Triple(red,green,blue)
    }
}
