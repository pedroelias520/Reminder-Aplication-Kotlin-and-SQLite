package com.example.reminder_aplication

import DBhelper.DBhelper
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_screen.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)
        var db = DBhelper.Companion

        Add_Button.setOnClickListener {
            intent = Intent(this,FormActivity::class.java)
            startActivity(intent)
        }

    }
}
