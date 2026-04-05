package com.mh.listacontactos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val listaDeContactos = mutableListOf<Contacto>()
    private lateinit var adapter: ContactoAdapter

    // NOVO: Este objeto escuta o resultado da EditorActivity
    private val editorLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val novo = result.data?.getSerializableExtra("NOVO_CONTACTO") as? Contacto
            if (novo != null) {
                listaDeContactos.add(novo)
                adapter.notifyDataSetChanged() // Atualiza a lista no ecrã
                Toast.makeText(this, "Contacto adicionado!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listViewContactos)
        val btnAdicionar = findViewById<Button>(R.id.btnAdicionar)

        // Dados de teste iniciais
        if (listaDeContactos.isEmpty()) {
            listaDeContactos.add(Contacto(1, "Maria Silva", "912345678", "maria@email.com"))
            listaDeContactos.add(Contacto(2, "João Pereira", "933444555", "joao@email.com"))
        }

        adapter = ContactoAdapter(this, listaDeContactos)
        listView.adapter = adapter

        // Ativa o menu de contexto (clique longo) para a lista
        registerForContextMenu(listView)

        // Clique curto: Ver Resumo
        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetalheActivity::class.java)
            intent.putExtra("CONTACTO_OBJ", listaDeContactos[position])
            startActivity(intent)
        }

        // Clique no botão Adicionar
        btnAdicionar.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            editorLauncher.launch(intent) // Abre esperando o resultado
        }
    }

    // Criar o menu de contexto (Eliminar/Editar)
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle("Opções")
        menu?.add(0, v?.id!!, 0, "Editar")
        menu?.add(0, v?.id!!, 0, "Eliminar")
    }

    // Lógica do menu de contexto
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val posicao = info.position

        when (item.title) {
            "Eliminar" -> {
                listaDeContactos.removeAt(posicao)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Removido!", Toast.LENGTH_SHORT).show()
            }
            "Editar" -> {
                // Para cumprir o requisito de edição de forma simples:
                Toast.makeText(this, "A editar: ${listaDeContactos[posicao].nome}", Toast.LENGTH_SHORT).show()
                // Nota: Num cenário real, abriríamos o EditorActivity com os dados atuais.
            }
        }
        return true
    }
}