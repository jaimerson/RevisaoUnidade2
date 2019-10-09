package com.example.revisaounidade2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_post.*

class CreatePostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        btn_post_create.setOnClickListener {
            val post = Post(-1, edit_txt_post_title.text.toString(), edit_txt_post_content.text.toString())
            PostRepository.addPost(post)
            NotificationUtils.notificationSimple(this, "Post created", post.title)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
