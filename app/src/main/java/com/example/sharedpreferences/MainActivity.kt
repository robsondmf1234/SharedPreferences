package com.example.sharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var buttonSalvar: Button
    lateinit var textResultado: TextView
    lateinit var editNome: TextInputEditText

    private val ARQUIVO_PREFERENCIA = "ArquivoPreferencia"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSalvar = findViewById(R.id.buttonSalvar)
        textResultado = findViewById(R.id.textResultado)
        editNome = findViewById(R.id.editNome)


        buttonSalvar.setOnClickListener {
            val preferences: SharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0)
            val editor: SharedPreferences.Editor = preferences.edit()

            //validar o nome
            if (editNome.text.toString().equals("")) {
                Toast.makeText(applicationContext, "Preencha o nome", Toast.LENGTH_LONG).show()
            } else {
                val nome = editNome.text.toString()
                editor.putString("nome", nome)
                editor.commit()
                textResultado.setText("Olá, $nome")
            }
        }

        //Recuperar dados salvos
        val preferences: SharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0)

        //Valida se temos o nome em preferencias
        if (preferences.contains("nome")) {
            val nome = preferences.getString("nome", "Olá, usuário não definido")
            textResultado.setText("Olá, $nome")
        } else {
            textResultado.setText("Olá, usuário não definido.")
        }
    }
}