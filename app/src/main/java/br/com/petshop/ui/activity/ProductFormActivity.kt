package br.com.petshop.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.petshop.dao.ProductDao
import br.com.petshop.databinding.ActivityProductFormBinding
import br.com.petshop.extensions.uploadPicture
import br.com.petshop.model.Product
import br.com.petshop.ui.dialog.ImageFormDialog
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar produto"
        configureSaveButton()
        binding.activityProductFormImage.setOnClickListener {
            ImageFormDialog(this)
                .show(url) { image ->
                    url = image
                    binding.activityProductFormImage.uploadPicture(url)
                }
        }
    }

    private fun configureSaveButton() {
        val saveButton = binding.activityProductFormSaveButton
        val dao = ProductDao()
        saveButton.setOnClickListener {
            val productNew = createProduct()
            dao.add(productNew)
            finish()
        }
    }

    private fun createProduct(): Product {
        val fieldName = binding.activityProductFormName
        val name = fieldName.text.toString()
        val fieldDescription = binding.activityProductFormDescription
        val description = fieldDescription.text.toString()
        val fieldValue = binding.activityProductFormValue
        val textValue = fieldValue.text.toString()
        val value = if (textValue.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(textValue)
        }

        return Product(
            name = name,
            description = description,
            value = value,
            image = url
        )
    }
}

