package com.example.revisaounidade2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val user = UserRepository.getUser(this)

        if(user != null){
            txt_login_email.setText(user.email)
            txt_login_pwd.setText(user.password)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login_sign_up.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
