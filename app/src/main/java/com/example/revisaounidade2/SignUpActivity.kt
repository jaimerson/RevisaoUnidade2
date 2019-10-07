package com.example.revisaounidade2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_sign_up.setOnClickListener {
            val email = txt_sign_up_email.text.toString()
            val password = txt_sign_up_pwd.text.toString()

            if (email.isNotBlank() && password.isNotBlank()){
                createUser(email, password)
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT)
            }
        }
    }

    private fun createUser(email: String, password: String): User {
        val user = User(email, password)
        UserRepository.createUser(this, user)
        return user
    }
}
