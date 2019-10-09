package com.example.revisaounidade2

class UserSession {
    companion object {
        var currentUser: User? = null

        fun start(user: User){
            this.currentUser = user
        }

        fun reset(){
            this.currentUser = null
        }
    }

}
