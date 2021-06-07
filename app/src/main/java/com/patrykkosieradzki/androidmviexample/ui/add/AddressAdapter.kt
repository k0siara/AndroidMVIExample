package com.patrykkosieradzki.androidmviexample.ui.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patrykkosieradzki.androidmviexample.databinding.AddressListItemBinding
import com.patrykkosieradzki.androidmviexample.domain.model.Address
import com.patrykkosieradzki.androidmviexample.utils.OnItemClickListener

class AddressAdapter(
    private val dataSet: MutableList<Address>,
    private val onClick: (Address) -> Unit
) : RecyclerView.Adapter<AddressAdapter.AddressListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressListItemViewHolder {
        val binding = AddressListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AddressListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressListItemViewHolder, position: Int) {
        val address = dataSet[position]
        address.let { holder.bind(it) }
    }

    override fun getItemCount() = dataSet.size

    fun setItems(addresses: List<Address>) {
        dataSet.clear()
        dataSet.addAll(addresses)
    }

    inner class AddressListItemViewHolder(
        private val binding: AddressListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(address: Address): View {
            binding.item = address
            binding.listener = object : OnItemClickListener<Address> {
                override fun onClick(item: Address) {
                    onClick.invoke(item)
                }
            }
            return binding.root
        }
    }
}
