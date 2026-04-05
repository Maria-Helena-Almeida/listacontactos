package com.mh.listacontactos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
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
            val nome = editNome.text.toString().trim()
            val telefone = editTelefone.text.toString().trim()
            val email = editEmail.text.toString().trim()

            // VALIDAÇÕES (Regras de Negócio)
            val isTelefoneValido = telefone.length == 9 && telefone.all { it.isDigit() }
            val isEmailValido = Patterns.EMAIL_ADDRESS.matcher(email).matches()

            if (nome.isEmpty()) {
                editNome.error = "O nome é obrigatório"
            } else if (!isTelefoneValido) {
                editTelefone.error = "O telefone deve ter exatamente 9 dígitos"
            } else if (email.isNotEmpty() && !isEmailValido) {
                editEmail.error = "Formato de email inválido"
            } else {
                // Se passar nas validações, cria o objeto e envia de volta
                val novoContacto = Contacto((0..9999).random(), nome, telefone, email)
                
                val resultIntent = Intent()
                resultIntent.putExtra("NOVO_CONTACTO", novoContacto)
                setResult(Activity.RESULT_OK, resultIntent)
                finish() // Fecha e volta para a lista
            }
        }
    }
}