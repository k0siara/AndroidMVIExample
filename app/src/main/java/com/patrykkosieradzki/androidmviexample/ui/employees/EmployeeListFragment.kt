package com.patrykkosieradzki.androidmviexample.ui.employees

import com.patrykkosieradzki.androidmviexample.R
import com.patrykkosieradzki.androidmviexample.databinding.EmployeeListFragmentBinding
import com.patrykkosieradzki.androidmviexample.utils.BaseFragment

class EmployeeListFragment :
    BaseFragment<EmployeeListContract.State, EmployeeListContract.Event,
        EmployeeListContract.Effect, EmployeeListViewModel, EmployeeListFragmentBinding>(
        R.layout.employee_list_fragment,
        EmployeeListViewModel::class
    )
