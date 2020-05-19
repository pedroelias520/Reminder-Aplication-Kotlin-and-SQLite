package com.example.reminder_aplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.form_screen.*


class FormActivity : AppCompatActivity (){

    fun saveRecord(view: View){
        val nome = NomeText.text
        val hora = HoraText.text
        val descricao = DescricaoText.text


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_screen)
    }
}