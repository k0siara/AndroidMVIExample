package com.patrykkosieradzki.androidmviexample.ui.add

import com.patrykkosieradzki.androidmviexample.R
import com.patrykkosieradzki.androidmviexample.databinding.AddEmployeeFragmentBinding
import com.patrykkosieradzki.androidmviexample.utils.BaseFragment

class AddEmployeeFragment :
    BaseFragment<AddEmployeeContract.State, AddEmployeeContract.Event,
        AddEmployeeContract.Effect, AddEmployeeViewModel, AddEmployeeFragmentBinding>(
        R.layout.add_employee_fragment,
        AddEmployeeViewModel::class
    )
