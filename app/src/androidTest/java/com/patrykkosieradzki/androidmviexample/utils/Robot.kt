package com.patrykkosieradzki.androidmviexample.utils

import java.util.concurrent.TimeUnit

open class Robot {
    fun wait(seconds: Int) = TimeUnit.SECONDS.sleep(seconds.toLong())

    companion object {
        var screenshotsEnabled: Boolean = false
    }
}

abstract class RobotTest<R : Robot> {

    protected fun withRobot(steps: R.() -> Unit) {
        createRobot().apply(steps)
    }

    abstract fun createRobot(): R
}