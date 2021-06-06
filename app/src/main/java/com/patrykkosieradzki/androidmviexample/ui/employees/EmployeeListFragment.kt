package com.patrykkosieradzki.androidmviexample.ui.employees

import android.view.View
import com.patrykkosieradzki.androidmviexample.R
import com.patrykkosieradzki.androidmviexample.databinding.EmployeeListFragmentBinding
import com.patrykkosieradzki.androidmviexample.utils.BaseFragment

class EmployeeListFragment :
    BaseFragment<EmployeeListContract.State, EmployeeListContract.Event,
        EmployeeListContract.Effect, EmployeeListViewModel, EmployeeListFragmentBinding>(
        R.layout.employee_list_fragment,
        EmployeeListViewModel::class
    ) {

    private lateinit var adapter: EmployeeListAdapter

    override fun setupViews(view: View) {
        super.setupViews(view)
        onBackEvent = { requireActivity().moveTaskToBack(true) }
        adapter = EmployeeListAdapter() // { viewModel.onAnimalItemClicked(it) }
        with(binding) {
            employeesRecyclerView.adapter = this@EmployeeListFragment.adapter
            fab.setOnClickListener {
                viewModel.onAddEmployeeButtonClicked()
            }
        }
    }
}
