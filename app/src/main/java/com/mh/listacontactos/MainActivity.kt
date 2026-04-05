package com.mh.listacontactos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Lista global para podermos manipular em toda a Activity
    private val listaDeContactos = mutableListOf<Contacto>()
    private lateinit var adapter: ContactoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listViewContactos)
        val btnAdicionar = findViewById<Button>(R.id.btnAdicionar)

        // 1. Criar dados de teste (Figura 1 do enunciado)
        if (listaDeContactos.isEmpty()) {
            listaDeContactos.add(Contacto(1, "Maria Silva", "912345678", "maria@email.com"))
            listaDeContactos.add(Contacto(2, "João Pereira", "933444555", "joao@email.com"))
            listaDeContactos.add(Contacto(3, "Ana Martins", "966777888", "ana@email.com"))
        }

        // 2. Configurar o Adaptador
        adapter = ContactoAdapter(this, listaDeContactos)
        listView.adapter = adapter

        // 3. Clique curto: Abrir Resumo (Figura 2 do enunciado)
        listView.setOnItemClickListener { _, _, position, _ ->
            val contactoSelecionado = listaDeContactos[position]
            val intent = Intent(this, DetalheActivity::class.java)
            intent.putExtra("CONTACTO_OBJ", contactoSelecionado)
            startActivity(intent)
        }

        // 4. Clique Longo: Eliminar (Requisito: Eliminar contactos)
        listView.setOnItemLongClickListener { _, _, position, _ ->
            listaDeContactos.removeAt(position)
            adapter.notifyDataSetChanged() // Atualiza a lista visualmente
            true // Indica que o clique longo foi processado
        }

        // 5. Botão Adicionar (Passaremos para isto no próximo passo)
        btnAdicionar.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            startActivity(intent)
        }
    }
    // Este código recebe o resultado da EditorActivity e atualiza a lista
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    
    if (resultCode == Activity.RESULT_OK) {
        val novo = data?.getSerializableExtra("NOVO_CONTACTO") as? Contacto
        if (novo != null) {
            listaDeContactos.add(novo)
            adapter.notifyDataSetChanged()
        }
    }
}
}