package br.com.petshop.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.petshop.databinding.FormImageBinding
import br.com.petshop.extensions.uploadPicture

class ImageFormDialog(private val context: Context) {

    fun show(
        defaultUrl: String? = null,
        uploadedImage: (Image: String) -> Unit
    ) {
        FormImageBinding
            .inflate(LayoutInflater.from(context)).apply {
// Carregar informação e imagem da URL
                defaultUrl?.let {
                    formImageImageview.uploadPicture(it)
                    formImageUrl.setText(it)
                }
// Upload da imagem
                formImageLoadButton.setOnClickListener {
                    val url = formImageUrl.text.toString()
                    formImageImageview.uploadPicture(url)
                }

// Caixa de dialogo (click de salvar ou cancelar imagem)
                AlertDialog.Builder(context)
                    .setView(root)
                    .setPositiveButton("Confirmar") { _, _ ->
                        val url = formImageUrl.text.toString()
                        Log.i("ImageFormDialog", "mostra: $url")
                        uploadedImage(url)
                    }
                    .setNegativeButton("Cancelar") { _, _ ->

                    }
                    .show()
            }
    }
}
