package com.backbase.citysearch.searchalgo

import java.util.*

class TrieNode<T> internal constructor() {
    var data: T? = null
    var children: MutableMap<Char, TrieNode<T>>

    init {
        children = HashMap()
    }
}