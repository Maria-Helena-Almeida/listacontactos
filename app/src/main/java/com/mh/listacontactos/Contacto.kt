package com.mh.listacontactos

import java.io.Serializable

// Usamos Serializable para poder passar o objeto inteiro entre Activities via Intent
data class Contacto(
    var id: Int,
    var nome: String,
    var telefone: String,
    var email: String,
    var imagemResId: Int = android.R.drawable.ic_menu_report_image // Ícone padrão
) : Serializable