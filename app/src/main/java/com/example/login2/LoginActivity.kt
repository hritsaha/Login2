package com.example.login2

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
    }

    fun onLogin(view: View) {
        val emailInput : EditText = findViewById(R.id.input_email)
        val passInput : EditText = findViewById(R.id.input_pass)

        when{
            TextUtils.isEmpty(emailInput.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this,"Please Enter Email", Toast.LENGTH_SHORT).show()
            }

            TextUtils.isEmpty(passInput.text.toString().trim{it <= ' '}) -> {
                Toast.makeText(this,"Please Enter Password", Toast.LENGTH_SHORT).show()
            }

            else -> {
                val email = emailInput.text.toString()
                val pass = passInput.text.toString()

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener({
                    task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Sign In Successful", Toast.LENGTH_SHORT).show()

                        val intent_main = Intent(this,MainActivity::class.java)
                        intent_main.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent_main.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                        intent_main.putExtra("email_id",email)
                        startActivity(intent_main)
                        finish()

                    }
                    else{
                        Toast.makeText(this@LoginActivity,task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }

    fun onRegister(view: View) {
        val intent_register = Intent(this,RegisterActivity::class.java)
        startActivity(intent_register)
        finish()
    }

}