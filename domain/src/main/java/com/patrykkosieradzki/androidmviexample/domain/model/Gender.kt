package com.patrykkosieradzki.androidmviexample.domain.model

data class Gender(
    val id: Long,
    val name: String
) {
    override fun toString() = name
}
