package com.example.setupgameadmin


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val emailSignIn = findViewById<EditText>(R.id.emailSignIn)
        val passwordSignIn = findViewById<EditText>(R.id.passwordSignIn)

        btnSignIn.setOnClickListener {
            val email = emailSignIn.text.toString().trim()
            val password = passwordSignIn.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInUser(email, password)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        val forgetPassword = findViewById<TextView>(R.id.textViewForgetPassword)

        forgetPassword.setOnClickListener {
            // Rediriger vers ResetPasswordActivity
            val intent = Intent(this, ResetPassword::class.java)
            startActivity(intent)
        }

    }

    private fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Sign In Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,HomeScreen::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Sign In Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

}