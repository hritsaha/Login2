package com.example.login2

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        val registerButton : Button = findViewById(R.id.btn_register)
        val emailInput : EditText = findViewById(R.id.input_email)
        val passInput :EditText = findViewById(R.id.input_pass)
        registerButton.setOnClickListener {
            when{
                TextUtils.isEmpty(emailInput.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show()

                }

                TextUtils.isEmpty(passInput.text.toString().trim{it <= ' '}) -> {
                    Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show()
                }

                else -> {
                    val email = emailInput.text.toString().trim{ it <= ' '}
                    val pass = passInput.text.toString().trim{it <= ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener({
                        task ->
                        if(task.isSuccessful){
                            val firebaseUser : FirebaseUser = task.result!!.user!!
                            Toast.makeText(this,"Registration Successful!",Toast.LENGTH_SHORT).show()

                            val intent_main = Intent(this,MainActivity::class.java)
                            intent_main.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent_main.putExtra("user_id",firebaseUser.uid)
                            intent_main.putExtra("email_id",email)
                            startActivity(intent_main)
                            finish()
                        }

                        else{
                            Toast.makeText(this,"Registration Failed!",Toast.LENGTH_SHORT).show()
                        }
                    })


                }
            }
        }
    }

    fun onLogin(view: View) {
        val intent_login = Intent(this,LoginActivity::class.java)
        startActivity(intent_login)
        finish()
    }


}