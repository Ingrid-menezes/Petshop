package br.com.petshop.ui.activity


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.petshop.dao.ProductDao
import br.com.petshop.databinding.ActivityProductListBinding
import br.com.petshop.ui.recyclerview.adapter.ProductListAdapter

class ProductListActivity : AppCompatActivity() {
    private val dao = ProductDao()
    private val adapter = ProductListAdapter(context = this, products = dao.searchProduct())
    private val binding by lazy {
        ActivityProductListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureRecyclerView()
        configureFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.searchProduct())
    }

    private fun configureFab() {
        val fab = binding.activityProductListFab
        fab.setOnClickListener {
            goToProductForm()
        }
    }

    private fun goToProductForm() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    private fun configureRecyclerView() {
        val recyclerView = binding.activityProductListRecyclerview
        recyclerView.adapter = adapter

        // implementação do listener para abrir a Activity de detalhes do produto
        // com o produto clicado
        adapter.whenClickingOnTheItem  = {
            val intent = Intent(
                this,
                DetailsProductActivity::class.java
            ).apply {
                putExtra(KEY_PRODUCT, it)
            }
            startActivity(intent)
        }
    }
}