package com.backbase.citysearch.searchalgo;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrieImpl<T> implements Index<T> {
    private final TrieNode<T> rootNode;

    public TrieImpl() {
        rootNode = new TrieNode<T>();
    }

    @Override
    public void addEntry(@NotNull String key, @NotNull T data) {
        char[] chars = key.toCharArray();
        TrieNode<T> startNode = rootNode;
        for (char ch : chars) {
            TrieNode<T> trieNode = startNode.children.get(ch);
            if (trieNode == null) {
                TrieNode<T> newNode = new TrieNode<T>();
                startNode.children.put(ch, newNode);
                startNode = newNode;
            } else {
                startNode = trieNode;
            }
        }
        startNode.data = data;
    }

    @Override
    public @Nullable
    List<T> findEntries(@NotNull String searchQuery) {
        char[] chars = searchQuery.toCharArray();
        TrieNode<T> startNode = rootNode;
        for (char ch : chars) {
            TrieNode<T> nextNode = startNode.children.get(ch);
            if (nextNode == null) {
                return null;
            } else {
                startNode = nextNode;
            }
        }
        return collectAllData(startNode);
    }

    private @NonNull
    List<T> collectAllData(TrieNode<T> startNode) {
        List<T> dataList = new ArrayList<>();
        if (startNode.data != null) {
            dataList.add(startNode.data);
        }
        for (Map.Entry<Character, TrieNode<T>> entries : startNode.children.entrySet()) {
            List<T> newList = collectAllData(entries.getValue());
            dataList.addAll(newList);
        }
        return dataList;
    }
}
