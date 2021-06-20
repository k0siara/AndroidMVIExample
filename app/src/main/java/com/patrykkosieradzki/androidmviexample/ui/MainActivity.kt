package com.patrykkosieradzki.androidmviexample.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.patrykkosieradzki.androidmviexample.R
import com.patrykkosieradzki.androidmviexample.domain.DemoDataGenerator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val demoDataGenerator: DemoDataGenerator by inject()

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        GlobalScope.launch {
            demoDataGenerator.loadDemoDataIntoDB()
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            App()
        }
    }
}
