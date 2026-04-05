package com.mh.listacontactos

import java.io.Serializable

// Usamos Serializable para permitir que o objeto seja enviado entre ecrãs
data class Contacto(
    var id: Int,
    var nome: String,
    var telefone: String,
    var email: String,
    var imagemResId: Int = android.R.drawable.ic_menu_report_image
) : Serializable