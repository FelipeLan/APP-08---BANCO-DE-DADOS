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
            val bd = openOrCreateDatabase("empresa", MODE_PRIVATE, null)
            //CRIANDO A TABELA pessoa
            bd.execSQL("CREATE TABLE IF NOT EXISTS funcionario(nome VARCHAR, idade INT(3), ocupacao VARCHAR, dtNasc DATE, salario DOUBLE)")

            //Inserindo dados
            bd.execSQL("INSERT INTO pessoa (nome, idade, ocupacao, dtNasc, salario) VALUES ('Felipão', 20, 'Secretário', 2005/13/06, 2.300)")
            bd.execSQL("INSERT INTO pessoa (nome, idade, ocupacao, dtNasc, salario) VALUES ('Sergio', 49, 'Administrador', 1977/11/01, 7.100)")
            bd.execSQL("INSERT INTO pessoa (nome, idade, ocupacao, dtNasc, salario) VALUES ('Carlos', 48, 'Secretário', 1978/05/01, 2.800)")
            bd.execSQL("INSERT INTO pessoa (nome, idade, ocupacao, dtNasc, salario) VALUES ('David', 25, 'Zelador', 2001/17/07, 1.500)")
            bd.execSQL("INSERT INTO pessoa (nome, idade, ocupacao, dtNasc, salario) VALUES ('Homer', 46, 'RH', 1980/10/12, 3.100)")
            bd.execSQL("INSERT INTO pessoa (nome, idade, ocupacao, dtNasc, salario) VALUES ('Patrick', 27, 'Segurança', 1999/12/12, 2.000)")
            bd.execSQL("INSERT INTO pessoa (nome, idade, ocupacao, dtNasc, salario) VALUES ('Bob', 28, 'Cozinheiro', 1998/08/08, 2.800)")
            bd.execSQL("INSERT INTO pessoa (nome, idade, ocupacao, dtNasc, salario) VALUES ('Tom', 25, 'Segurança', 2001/11/09, 1.700)")
            bd.execSQL("INSERT INTO pessoa (nome, idade, ocupacao, dtNasc, salario) VALUES ('Jerry', 20, 'Financeiro', 2005/11/07, 8.800)")
            bd.execSQL("INSERT INTO pessoa (nome, idade, ocupacao, dtNasc, salario) VALUES ('Rafael', 24, 'Repositor', 2002/19/08, 2.300)")

            //recuperar pessoas
            val cursor = bd.rawQuery("SELECT nome, idade FROM pessoa", null)

            //indices das tabelas
            val indiceNome = cursor!!.getColumnIndex("nome")
            val indiceIdade = cursor.getColumnIndex("idade")
            val indiceOcupacao = cursor.getColumnIndex("ocupacao")
            val indiceDtNasc = cursor.getColumnIndex("dtNasc")
            val indiceSalario = cursor.getColumnIndex("salario")
            cursor.moveToFirst()
            while (cursor != null) {
                Log.i("RESULTADO = NOME: ", cursor.getString(0))
                Log.i("RESULTADO = NOME: ", cursor.getString(indiceNome))
                Log.i("RESULTADO = IDADE: ", cursor.getString(indiceIdade))
                Log.i("RESULTADO = OCUPAÇÃO: ", cursor.getString(indiceOcupacao))
                Log.i("RESULTADO = DATA DE NASC: ", cursor.getString(indiceDtNasc))
                Log.i("RESULTADO = SALÁRIO: ", cursor.getString(indiceSalario))

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
