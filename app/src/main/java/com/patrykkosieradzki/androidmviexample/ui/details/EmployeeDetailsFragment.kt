package com.patrykkosieradzki.androidmviexample.ui.details

import com.patrykkosieradzki.androidmviexample.R
import com.patrykkosieradzki.androidmviexample.databinding.EmployeeDetailsFragmentBinding
import com.patrykkosieradzki.androidmviexample.utils.BaseFragment

class EmployeeDetailsFragment :
    BaseFragment<EmployeeDetailsContract.State, EmployeeDetailsContract.Event,
        EmployeeDetailsContract.Effect, EmployeeDetailsViewModel, EmployeeDetailsFragmentBinding>(
        R.layout.employee_details_fragment,
        EmployeeDetailsViewModel::class
    )