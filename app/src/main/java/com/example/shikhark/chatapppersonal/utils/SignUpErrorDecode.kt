package com.example.shikhark.chatapppersonal.utils

object SignUpErrorDecode {


    fun checkEmailValidity(email:String):Pair<Boolean,String>{
        return if(email.length==0) Pair(false,"Email field cannot be left blank")
        else if(!email.contains("@")) Pair(false,"Please enter a valid email.")
        else Pair(true,"Valid Email")
    }


    fun checkUserName(userName:String):Pair<Boolean,String>{
        return if (userName.length==0) Pair(false,"UserName field cannot be left blank")
        else if(userName.length<=5) Pair(false,"User Name should at least have 6 characters")
        else Pair(true,"Valid User Name")

    }

    fun checkPasswod(password1:String,password2:String):Pair<Boolean,String>{
        if(password1!=password2) return Pair(false,"Passwords do not match")
        else return Pair(true,"Valid Passwords")
    }
}