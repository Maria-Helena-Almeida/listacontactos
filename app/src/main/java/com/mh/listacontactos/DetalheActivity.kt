package com.mh.listacontactos

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        val img = findViewById<ImageView>(R.id.imgDetalhe)
        val nome = findViewById<TextView>(R.id.txtNomeDetalhe)
        val telefone = findViewById<TextView>(R.id.txtTelefoneDetalhe)
        val email = findViewById<TextView>(R.id.txtEmailDetalhe)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        // Recuperar o objeto enviado (Casting para o tipo Contacto)
        val contacto = intent.getSerializableExtra("CONTACTO_OBJ") as? Contacto

        if (contacto != null) {
            nome.text = contacto.nome
            telefone.text = "📞 Telefone: ${contacto.telefone}"
            email.text = "✉️ Email: ${contacto.email}"
            img.setImageResource(contacto.imagemResId)
        }

        btnVoltar.setOnClickListener {
            finish() // Fecha este ecrã e volta para a lista
        }
    }
}