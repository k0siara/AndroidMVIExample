package com.patrykkosieradzki.androidmviexample.ui.employees

import androidx.recyclerview.widget.DiffUtil
import com.patrykkosieradzki.androidmviexample.storage.model.EmployeeWithGenderAndAddresses

class EmployeeDiffCallback : DiffUtil.ItemCallback<EmployeeWithGenderAndAddresses>() {
    override fun areItemsTheSame(
        oldItem: EmployeeWithGenderAndAddresses,
        newItem: EmployeeWithGenderAndAddresses
    ): Boolean {
        return oldItem.employee.employeeId == newItem.employee.employeeId
    }

    override fun areContentsTheSame(
        oldItem: EmployeeWithGenderAndAddresses,
        newItem: EmployeeWithGenderAndAddresses
    ): Boolean {
        return oldItem == newItem
    }
}
