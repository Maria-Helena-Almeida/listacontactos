package com.mh.listacontactos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ContactoAdapter(context: Context, private val lista: List<Contacto>) :
    ArrayAdapter<Contacto>(context, 0, lista) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_contacto, parent, false)

        val contacto = lista[position]

        val img = view.findViewById<ImageView>(R.id.imgContacto)
        val nome = view.findViewById<TextView>(R.id.txtNome)
        val telefone = view.findViewById<TextView>(R.id.txtTelefone)

        nome.text = contacto.nome
        telefone.text = contacto.telefone
        img.setImageResource(contacto.imagemResId)

        return view
    }
}