package com.example.domain.models

data class Page(
    val index: Int = 0,
) {
    fun getLimit(): Int {
        return 20
    }
    fun getOffset(): Int {
        return index * 20
    }
}