package com.example.login2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val email = intent.getStringExtra("email_id")
        val firebaseuid = intent.getStringExtra("user_id")

        val change_uid : TextView = findViewById(R.id.tv_user_id)
        val change_email :TextView = findViewById(R.id.tv_email)

        change_email.append(email.toString())
        change_uid.append(firebaseuid.toString())
    }

    fun onLogOut(view: View) {
        //firebase code to log out
        FirebaseAuth.getInstance().signOut()

        //creating intent to go to Login Page
        val intent_login = Intent(this,LoginActivity::class.java)
        startActivity(intent_login)
        finish()
    }
}