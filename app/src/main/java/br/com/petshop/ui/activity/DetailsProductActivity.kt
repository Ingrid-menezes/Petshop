package br.com.petshop.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.petshop.databinding.ActivityDetailsProductBinding
import br.com.petshop.extensions.brazilianCurrencyValue
import br.com.petshop.extensions.uploadPicture
import br.com.petshop.model.Product

class DetailsProductActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetailsProductBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        uploadProduct()
    }

    private fun uploadProduct() = // tentativa de buscar o produto se ele existir,
        // caso contrário, finalizar a Activity
        intent.getParcelableExtra<Product>(KEY_PRODUCT)?.let { uploadProduct ->
            fillFields(uploadProduct)
        } ?: finish()

    private fun fillFields(productLoaded: Product) {
        with(binding) {
            activityDetailsProductImage.uploadPicture(productLoaded.image)
            activityDetailsProductName.text = productLoaded.name
            activityDetailsProductDescription.text = productLoaded.description
            activityDetailsProductValue.text =
                productLoaded.value.brazilianCurrencyValue()
        }
    }
}
