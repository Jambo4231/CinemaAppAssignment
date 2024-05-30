package com.example.cinemaapp

import com.google.firebase.auth.FirebaseAuth

class AuthUtils {
    companion object {
        fun isLoggedIn(): Boolean {
            val auth = FirebaseAuth.getInstance()
            return auth.currentUser != null
        }
    }
}
