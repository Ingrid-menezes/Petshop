package br.com.petshop.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.petshop.databinding.ProductItemBinding
import br.com.petshop.extensions.brazilianCurrencyValue
import br.com.petshop.extensions.uploadPicture
import br.com.petshop.model.Product
import br.com.petshop.ui.recyclerview.adapter.ProductListAdapter.ViewHolder

class ProductListAdapter(
    private val context: Context,
    products: List<Product>,
    // declaração da função para o listener do adapter
    var whenClickingOnTheItem: (product: Product) -> Unit = {}
) : RecyclerView.Adapter<ViewHolder>() {

    private var products = products.toMutableList()

    // utilização do inner na classe interna para acessar membros da classe superior
    // nesse caso, a utilização da variável whenClickingOnTheItem
    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var product: Product

        init {
            binding.root.setOnClickListener {
                if (::product.isInitialized)
                    whenClickingOnTheItem(product)
            }
        }

        fun bind(product: Product) {
            this.product = product
            val name = binding.productItemName
            name.text = product.name
            val description = binding.productItemDescription
            description.text = product.description
            val value = binding.productItemValue
            val currencyValue: String = product.value
                .brazilianCurrencyValue()
            value.text = currencyValue

            val visibleImage = if (product.image != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = visibleImage

            binding.imageView.uploadPicture(product.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size
    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }
}
