package com.example.domain.models

data class Page(
    val index: Int = 0,
) {
    fun getLimit(): Int {
        return 50
    }
    fun getOffset(): Int {
        return index * 50
    }
}