package io.erikrios.github.githubuserapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import io.erikrios.github.githubuserapp.R

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_DELAY = 2500L
    }

    private var handler: Handler? = null
    private val runnable: Runnable = Runnable {
        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        handler = Handler(Looper.getMainLooper())
        handler?.postDelayed(runnable, SPLASH_DELAY)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacks(runnable)
    }
}