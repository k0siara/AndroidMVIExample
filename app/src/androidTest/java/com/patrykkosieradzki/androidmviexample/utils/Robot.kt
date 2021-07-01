package com.patrykkosieradzki.androidmviexample.utils

open class Robot {

}

abstract class RobotTest<R : Robot> {

    protected fun withRobot(steps: R.() -> Unit) {
        createRobot().apply(steps)
    }

    abstract fun createRobot(): R
}