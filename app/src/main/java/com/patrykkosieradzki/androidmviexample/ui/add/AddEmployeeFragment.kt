package com.patrykkosieradzki.androidmviexample.ui.add

import android.view.View
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
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
            firstName.doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm(firstName = text.toString())
            }
            lastName.doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm(lastName = text.toString())
            }
            address.doOnTextChanged { text, _, _, _ ->
                viewModel.updateForm(address = text.toString())
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun handleState(it: AddEmployeeContract.State) {
        with(binding) {
            (addressesRecyclerView.adapter as AddressAdapter).run {
                setItems(it.addresses)
                notifyDataSetChanged() // Todo: replace with DiffUtil
            }

            when (it) {
                is AddEmployeeContract.State.Initial -> {
                    (genderSpinner.adapter as ArrayAdapter<Gender>).run {
                        addAll(it.genders)
                        genderSpinner.setSelection(0)
                    }
                }
                else -> {
                }
            }
        }
    }
}
