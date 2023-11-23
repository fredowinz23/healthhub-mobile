package com.capstone.healthhubnurse

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.capstone.healthhubnurse.api.UserSession
import com.capstone.healthhubnurse.auth.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val userSession = UserSession(this)
        Handler(Looper.getMainLooper()).postDelayed({
            if (userSession.username.isNullOrBlank()){
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            }
            else{
                startActivity(Intent(this@SplashScreenActivity, NurseMainActivity::class.java))
            }
            finish()
        }, 2000)
    }
}