package com.mh.listacontactos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
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

    // Verificar se recebemos um contacto para EDITAR
    val contactoParaEditar = intent.getSerializableExtra("CONTACTO_EDITAR") as? Contacto
    val posicaoOriginal = intent.getIntExtra("POSICAO", -1)

    // Se for edição, preenchemos os campos com os dados atuais
    if (contactoParaEditar != null) {
        editNome.setText(contactoParaEditar.nome)
        editTelefone.setText(contactoParaEditar.telefone)
        editEmail.setText(contactoParaEditar.email)
        btnGuardar.text = "ATUALIZAR" // Muda o texto do botão
    }

    btnGuardar.setOnClickListener {
        val nome = editNome.text.toString().trim()
        val telefone = editTelefone.text.toString().trim()
        val email = editEmail.text.toString().trim()

        // (Mantém as tuas validações de 9 dígitos aqui...)
        if (nome.isEmpty() || telefone.length != 9) {
            Toast.makeText(this, "Dados inválidos!", Toast.LENGTH_SHORT).show()
        } else {
            // Criamos o objeto com os dados novos
            val contactoFinal = Contacto(contactoParaEditar?.id ?: (0..999).random(), nome, telefone, email)
            
            val resultIntent = Intent()
            resultIntent.putExtra("NOVO_CONTACTO", contactoFinal)
            resultIntent.putExtra("POSICAO", posicaoOriginal) // Devolvemos a posição para a MainActivity saber onde gravar
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
}