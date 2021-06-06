package com.patrykkosieradzki.androidmviexample.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.patrykkosieradzki.androidmviexample.BR
import com.patrykkosieradzki.androidmviexample.R
import com.patrykkosieradzki.androidmviexample.domain.AppConfiguration
import com.patrykkosieradzki.androidmviexample.utils.extensions.goneIfWithAnimation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber
import kotlin.reflect.KClass

@Suppress("TooManyFunctions")
abstract class BaseFragment<STATE : UiState, EVENT : UiEvent, EFFECT : UiEffect,
    VM : BaseViewModel<STATE, EVENT, EFFECT>, VDB : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    vmKClass: KClass<VM>
) : Fragment() {

    private var _binding: VDB? = null
    protected val binding get() = _binding!!

    val viewModel: VM by lazy {
        getViewModel(clazz = vmKClass)
    }

    val appConfiguration: AppConfiguration by inject()

    private var loader: LottieAnimationView? = null

    var onBackEvent: () -> Unit = {
        try {
            findNavController().navigateUp()
        } catch (e: IllegalStateException) {
            Timber.e("Fragment $this is not a NavHostFragment or within a NavHostFragment")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<VDB>(inflater, layoutId, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                setVariable(BR.vm, viewModel)
            }
        return RelativeLayout(requireContext()).apply {
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            id = R.id.loader_container
            addView(
                binding.root,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
            loader = LottieAnimationView(requireContext()).apply {
                isClickable = true
                isFocusable = true
                visibility = View.GONE
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                setAnimation(R.raw.lottie_loading_animation)
                repeatMode = LottieDrawable.RESTART
                repeatCount = LottieDrawable.INFINITE
                playAnimation()
            }
            addView(
                loader,
                RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        }
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            viewModelScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    inProgress.collect {
                        loader?.goneIfWithAnimation(!it)
                    }
                }

            }
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                onBackEvent.invoke()
            }
            setupViews(view)
            initialize()
        }
    }

    open fun setupViews(view: View) {}

//    protected fun showError(error: ErrorEvent) = when (error.throwable) {
//        is ApiException -> {
//            showApiException(error.throwable, error.isInitialState)
//        }
//        else -> if (appConfiguration.debug) showErrorDialog(
//            error.throwable?.message ?: "Error"
//        ) else showErrorDialog(
//            getString(R.string.general_error_message)
//        )
//    }

//    private fun showApiException(error: ApiException, isInitialError: Boolean) {
//        showErrorDialog(error.errorMessage) {
//            when {
//                isInitialError -> {
//                    try {
//                        findNavController().popBackStack()
//                    } catch (e: IllegalStateException) {
//                        Timber.e("Fragment $this is not a NavHostFragment or within a NavHostFragment")
//                    }
//                }
//            }
//        }
//    }

//    protected fun showErrorDialog(message: String, actionOnDismiss: () -> Unit = {}) {
//        Timber.e("showErrorDialog:$message")
//        val dialog = DialogFragmentFactory.newErrorInstance(message)
//        dialog.callback = {
//            actionOnDismiss()
//            viewModel.updateViewStateToSuccess()
//        }
//        dialog.show(childFragmentManager)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
