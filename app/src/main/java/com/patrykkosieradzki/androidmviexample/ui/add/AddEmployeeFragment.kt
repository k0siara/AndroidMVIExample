package com.patrykkosieradzki.androidmviexample.ui.add

import android.view.View
import android.widget.ArrayAdapter
import com.patrykkosieradzki.androidmviexample.R
import com.patrykkosieradzki.androidmviexample.databinding.AddEmployeeFragmentBinding
import com.patrykkosieradzki.androidmviexample.domain.model.Gender
import com.patrykkosieradzki.androidmviexample.utils.BaseFragment

class AddEmployeeFragment :
    BaseFragment<AddEmployeeContract.State, AddEmployeeContract.Event,
        AddEmployeeContract.Effect, AddEmployeeViewModel, AddEmployeeFragmentBinding>(
        R.layout.add_employee_fragment,
        AddEmployeeViewModel::class
    ) {

    override fun setupViews(view: View) {
        super.setupViews(view)
        with(binding) {
            toolbar.setNavigationOnClickListener { onBackEvent.invoke() }
            genderSpinner.adapter = ArrayAdapter<Gender>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                mutableListOf()
            )
            addressesRecyclerView.adapter = AddressAdapter(mutableListOf()) {
                viewModel.onRemoveAddressClicked(it)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun handleState(it: AddEmployeeContract.State) {


        when (it) {
            is AddEmployeeContract.State.Initial -> {
                (binding.genderSpinner.adapter as ArrayAdapter<Gender>).run {
                    addAll(it.genders)
                    binding.genderSpinner.setSelection(0)
                }
            }
            else -> {
            }
        }
    }
}
