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

        // Recuperar o objeto Contacto enviado pela MainActivity
        val contacto = intent.getSerializableExtra("CONTACTO_OBJ") as? Contacto

        // Se o contacto existir, preenche os campos do layout
        if (contacto != null) {
            findViewById<TextView>(R.id.txtNomeDetalhe).text = contacto.nome
            findViewById<TextView>(R.id.txtTelefoneDetalhe).text = "📞 Telefone: ${contacto.telefone}"
            findViewById<TextView>(R.id.txtEmailDetalhe).text = "✉️ Email: ${contacto.email}"
            findViewById<ImageView>(R.id.imgDetalhe).setImageResource(contacto.imagemResId)
        }

        // Botão para voltar à lista
        findViewById<Button>(R.id.btnVoltar).setOnClickListener {
            finish()
        }
    }
}