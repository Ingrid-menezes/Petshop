package br.com.petshop.extensions

import android.widget.ImageView
import br.com.petshop.R
import coil.load

fun ImageView.uploadPicture(
    url: String? = null, fallback: Int = R.drawable.imagem_padrao
) {
    load(url) {
        fallback(fallback)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}