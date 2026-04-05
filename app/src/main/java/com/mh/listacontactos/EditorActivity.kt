package com.mh.listacontactos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        val editNome = findViewById<EditText>(R.id.editNome)
        val editTelefone = findViewById<EditText>(R.id.editTelefone)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val nome = editNome.text.toString()
            val telefone = editTelefone.text.toString()
            val email = editEmail.text.toString()

            if (nome.isNotEmpty() && telefone.isNotEmpty()) {
                // Criar o novo objeto (ID pode ser random aqui para teste)
                val novoContacto = Contacto((0..1000).random(), nome, telefone, email)
                
                // IMPORTANTE: Enviar de volta para a MainActivity
                val resultIntent = Intent()
                resultIntent.putExtra("NOVO_CONTACTO", novoContacto)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Preencha o nome e telefone!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}