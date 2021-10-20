package com.backbase.citysearch.searchalgo

interface Index<T> {
    fun addEntry(key: String, data: T)
    fun findEntries(searchQuery: String): List<T>?
}