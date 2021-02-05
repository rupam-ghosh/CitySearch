package com.backbase.citysearch.searchalgo;

import java.util.HashMap;
import java.util.Map;

public class TrieNode<T> {
    T data;
    Map<Character, TrieNode<T>> children;
    TrieNode() {
        children = new HashMap<>();
    }
}
