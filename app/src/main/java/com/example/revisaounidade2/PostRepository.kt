package com.example.revisaounidade2

import android.content.Context
import android.database.Cursor

class PostRepository {
    companion object {
        private var databaseHelper: DatabaseHelper? = null
        var postsList: ArrayList<Post> = ArrayList()

        fun initialize(context: Context){
            this.databaseHelper = DatabaseHelper(context)
            refresh()
        }

        fun refresh(){
            this.postsList = retrieveAll()
        }

        fun retrieveAll(): ArrayList<Post> {
            val list = ArrayList<Post>()
            val connection = databaseHelper!!.readableDatabase
            val cursor: Cursor = connection.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME} ORDER BY id ASC", null)

            while(cursor.moveToNext()){
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val title = cursor.getString(cursor.getColumnIndex(("title")))
                val content = cursor.getString(cursor.getColumnIndex(("content")))
                list.add(Post(id, title, content))
            }
            cursor.close()
            connection.close()

            return list
        }

        fun addPost(post: Post) {
            val connection = databaseHelper!!.writableDatabase
            connection.execSQL("""
                INSERT INTO ${DatabaseHelper.TABLE_NAME} (title, content)
                VALUES ("${post.title}", "${post.content}");
            """.trimIndent())

            connection.close()
            refresh()
        }

        fun removePost(post: Post) {
            this.postsList.remove(post)
            val connection = databaseHelper!!.writableDatabase
            connection.execSQL("""
                DELETE FROM "${DatabaseHelper.TABLE_NAME}"
                WHERE id = ${post.id};
            """.trimIndent())

            connection.close()
        }

        fun updatePost(post: Post) {
            val connection = databaseHelper!!.writableDatabase
            connection.execSQL("""
                UPDATE ${DatabaseHelper.TABLE_NAME}
                SET title = "${post.title}", content = "${post.content}"
                WHERE id = ${post.id};
            """.trimIndent())

            connection.close()
            refresh()
        }
    }
}
