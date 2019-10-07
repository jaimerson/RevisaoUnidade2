package com.example.revisaounidade2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class UserRepository {
    companion object {
        const val USER_PREFERENCES_KEY = "user_preferences"

        fun getUser(activity: AppCompatActivity) : User? {
            val sharedPreferences = activity.getSharedPreferences(USER_PREFERENCES_KEY, Context.MODE_PRIVATE)
            val email = sharedPreferences?.getString("email", null)
            val password = sharedPreferences?.getString("password", null)
            if (email != null && password != null) {
                return User(email, password)
            }

            return null
        }

        fun createUser(activity: AppCompatActivity, user: User){
            val sharedPreferences = activity.getSharedPreferences(USER_PREFERENCES_KEY, Context.MODE_PRIVATE)
            with(sharedPreferences.edit()){
                putString("email", user.email)
                putString("password", user.password)
            }
        }
    }
}