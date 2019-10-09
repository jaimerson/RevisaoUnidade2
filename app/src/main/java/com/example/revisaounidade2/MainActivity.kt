package com.example.revisaounidade2

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revisaounidade4.PostAdapter

class MainActivity : AppCompatActivity() {
    val broadcastReceiver = PostBroadcastReceiver()
    val filter = IntentFilter(PostBroadcastReceiver.FILTER_NAME).apply{
        addAction(PostBroadcastReceiver.ACTION_NAME)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        applicationContext.registerReceiver(broadcastReceiver, filter)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val postListView = findViewById<RecyclerView>(R.id.recycler_view)
        postListView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        PostRepository.initialize(applicationContext)
        val adapter = PostAdapter(PostRepository.postsList) { position ->
            val dialog = RemovePostDialogFragment {
                val post = PostRepository.postsList[position]
                PostRepository.removePost(post)
                notifyPostRemoved(post)
            }
            dialog.show(supportFragmentManager, "RemovePostDialog")
        }

        postListView.adapter = adapter
    }

    private fun notifyPostRemoved(post: Post) {
        Intent().also {intent ->
            intent.setAction(PostBroadcastReceiver.ACTION_NAME)
            intent.putExtra("postTitle", post.title)
            sendBroadcast(intent)
        }
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
            R.id.btn_add_some_item -> {
                PostRepository.addPost(Post(-1, "lorem ipsum", "dolor sit amet"))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
