
class AddEmployeeFragment :
    BaseFragment<EmployeeDetailsContract.State, EmployeeDetailsContract.Event,
        EmployeeDetailsContract.Effect, EmployeeDetailsViewModel, EmployeeDetailsFragmentBinding>(
    R.layout.employee_details_fragment,
    EmployeeDetailsViewModel::class
) {

}