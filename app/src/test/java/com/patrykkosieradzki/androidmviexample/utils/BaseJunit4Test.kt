package com.patrykkosieradzki.androidmviexample.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
abstract class BaseJunit4Test {
    protected val testCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun baseSetup() {
        MockitoAnnotations.initMocks(this)
        MVIExampleDispatchers.IO = testCoroutineDispatcher
    }

    @After
    fun baseTearDown() {
        MVIExampleDispatchers.resetAll()
    }
}