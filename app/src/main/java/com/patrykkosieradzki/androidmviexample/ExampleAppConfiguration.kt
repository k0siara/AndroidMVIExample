package com.patrykkosieradzki.androidmviexample

import com.patrykkosieradzki.androidmviexample.domain.AppConfiguration

class ExampleAppConfiguration : AppConfiguration {
    override val debug = BuildConfig.DEBUG
}
