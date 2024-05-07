package com.dracul.pokeapp.utills

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