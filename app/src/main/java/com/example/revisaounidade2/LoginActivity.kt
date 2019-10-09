package com.example.revisaounidade2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if(UserSession.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            return
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val user = UserRepository.getUser(this)

        if(user != null){
            txt_login_email.setText(user.email)
            txt_login_pwd.setText(user.password)
        }

        btn_login.setOnClickListener {
            val newUser = User(txt_login_email.text.toString(), txt_login_pwd.text.toString())
            if(newUser.equals(user)){
                UserSession.start(newUser)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        btn_login_sign_up.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
