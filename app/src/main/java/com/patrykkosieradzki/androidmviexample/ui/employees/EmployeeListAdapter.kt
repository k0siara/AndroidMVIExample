package com.patrykkosieradzki.androidmviexample.ui.employees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.patrykkosieradzki.androidmviexample.databinding.EmployeeListItemBinding
import com.patrykkosieradzki.androidmviexample.storage.model.EmployeeWithGenderAndAddresses

class EmployeeListAdapter :
    PagingDataAdapter<EmployeeWithGenderAndAddresses, EmployeeListAdapter.EmployeeListItemViewHolder>(
        EmployeeDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeListItemViewHolder {
        val binding = EmployeeListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeListItemViewHolder, position: Int) {
        val employee = getItem(position)
        employee?.let { holder.bind(it) }
    }

    inner class EmployeeListItemViewHolder(
        private val binding: EmployeeListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EmployeeWithGenderAndAddresses): View {
            binding.item = employee
//            binding.listener = object : OnItemClickListener<EmployeeListItem> {
//                override fun onClick(item: EmployeeListItem) {
//                    onClick.invoke(item)
//                }
//            }
            return binding.root
        }
    }


}