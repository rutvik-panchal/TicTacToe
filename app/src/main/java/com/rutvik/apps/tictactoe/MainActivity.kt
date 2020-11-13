package com.rutvik.apps.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rutvik.apps.tictactoe.databinding.ActivityMainBinding
import com.rutvik.apps.tictactoe.game.GameActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playNowButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, GameActivity::class.java))
        }

        binding.playerSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                buttonView.text = "Player One plays first!"
            } else {
                buttonView.text = "Player Two plays first!"
            }
        }
    }
}