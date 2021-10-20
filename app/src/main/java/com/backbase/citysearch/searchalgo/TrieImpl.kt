package com.backbase.citysearch.searchalgo

import java.util.*

class TrieImpl<T> : Index<T> {
    private val rootNode: TrieNode<T>
    override fun addEntry(key: String, data: T) {
        val chars = key.toCharArray()
        var startNode = rootNode
        for (ch in chars) {
            val trieNode = startNode.children[ch]
            if (trieNode == null) {
                val newNode = TrieNode<T>()
                startNode.children[ch] = newNode
                startNode = newNode
            } else {
                startNode = trieNode
            }
        }
        startNode.data = data
    }

    override fun findEntries(searchQuery: String): List<T>? {
        val chars = searchQuery.toCharArray()
        var startNode = rootNode
        for (ch in chars) {
            val nextNode = startNode.children[ch]
            startNode = (nextNode ?: return null)
        }
        return collectAllData(startNode)
    }

    private fun collectAllData(startNode: TrieNode<T>): List<T> {
        val dataList: MutableList<T> = ArrayList()
        if (startNode.data != null) {
            dataList.add(startNode.data!!)
        }
        for ((_, value) in startNode.children) {
            val newList = collectAllData(value)
            dataList.addAll(newList)
        }
        return dataList
    }

    init {
        rootNode = TrieNode()
    }
}