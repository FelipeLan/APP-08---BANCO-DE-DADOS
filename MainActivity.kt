package com.example.app_08_banco_de_dados

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.database.sqlite.SQLiteDatabase
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        try {
            //CRIANDO O BANCO DE DADOS
            val bd = openOrCreateDatabase("app", MODE_PRIVATE, null)
            //CRIANDO A TABELA pessoa
            bd.execSQL("CREATE TABLE IF NOT EXISTS pessoa(nome VARCHAR, idade INT(3))")

            //Inserindo dados
            bd.execSQL("INSERT INTO pessoa (nome, idade) VALUES ('Felipão', 20)")
            bd.execSQL("INSERT INTO pessoa (nome, idade) VALUES ('Fatec Diadema', 3)")
            bd.execSQL("INSERT INTO pessoa (nome, idade) VALUES ('Ted Taxi', 18)")

            //recuperar pessoas
            val cursor = bd.rawQuery("SELECT nome, idade FROM pessoa", null)

            //indices das tabelas
            val indiceNome = cursor!!.getColumnIndex("nome")
            val indiceIdade = cursor.getColumnIndex("idade")
            cursor.moveToFirst()
            while (cursor != null) {
                Log.i("RESULTADO = NOME: ", cursor.getString(0))
                Log.i("RESULTADO = NOME: ", cursor.getString(indiceNome))
                Log.i("RESULTADO = IDADE: ", cursor.getString(indiceIdade))
                cursor.moveToNext()
            }



        } catch (e: Exception) {
            e.printStackTrace()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}