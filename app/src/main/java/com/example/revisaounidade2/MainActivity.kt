package com.example.revisaounidade2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revisaounidade4.PostAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val postListView = findViewById<RecyclerView>(R.id.recycler_view)
        postListView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        PostRepository.initialize(applicationContext)

        val adapter = PostAdapter(PostRepository.postsList) { position ->
            val post = PostRepository.postsList[position]
            PostRepository.removePost(post)
        }
        postListView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.btn_logout -> {
                UserSession.reset()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.btn_add_post -> {
                val intent = Intent(this, CreatePostActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
