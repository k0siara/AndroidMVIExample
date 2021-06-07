package com.patrykkosieradzki.androidmviexample.ui.employees

import android.view.View
import android.widget.TextView
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.patrykkosieradzki.androidmviexample.R
import com.patrykkosieradzki.androidmviexample.databinding.EmployeeListFragmentBinding
import com.patrykkosieradzki.androidmviexample.utils.BaseFragment
import com.patrykkosieradzki.androidmviexample.utils.extensions.navigateTo
import com.patrykkosieradzki.androidmviexample.utils.observeInLifecycle
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*

@InternalCoroutinesApi
class EmployeeListFragment :
    BaseFragment<EmployeeListContract.State, EmployeeListContract.Event,
        EmployeeListContract.Effect, EmployeeListViewModel, EmployeeListFragmentBinding>(
        R.layout.employee_list_fragment,
        EmployeeListViewModel::class
    ) {

    private lateinit var adapter: EmployeeListAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun setupViews(view: View) {
        super.setupViews(view)
        onBackEvent = { requireActivity().moveTaskToBack(true) }
        adapter = EmployeeListAdapter {
            navigateTo(EmployeeListFragmentDirections.toEmployeeDetailsFragment())
        }
        layoutManager = LinearLayoutManager(requireContext())
        with(binding) {
            employeesRecyclerView.apply {
                layoutManager = this@EmployeeListFragment.layoutManager
                adapter = this@EmployeeListFragment.adapter
                addItemDecoration(
                    DividerItemDecoration(
                        employeesRecyclerView.context,
                        this@EmployeeListFragment.layoutManager.orientation
                    )
                )
            }
            fab.setOnClickListener {
                viewModel.onAddEmployeeButtonClicked()
            }
        }
        with(viewModel) {
            adapter.loadStateFlow
                .map { it.refresh }
                .distinctUntilChanged()
                .onEach {
                    when (it) {
                        is LoadState.Loading -> setLoadingState()
                        is LoadState.NotLoading -> {
                            if (adapter.itemCount == 0) {
                                setEmptyState()
                            } else {
                                setSuccessState()
                            }
                        }
                        is LoadState.Error -> setErrorState()
                    }
                }
                .observeInLifecycle(viewLifecycleOwner)

            employees
                .onEach { adapter.submitData(it) }
                .observeInLifecycle(viewLifecycleOwner)
        }
    }

    override fun handleState(it: EmployeeListContract.State) {
        if (it is EmployeeListContract.State.Empty) {
            view?.findViewById<TextView>(R.id.empty_text)?.visibility = View.VISIBLE
        } else {
            view?.findViewById<TextView>(R.id.empty_text)?.visibility = View.GONE
        }
    }
}
