package com.backbase.citysearch.searchalgo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public interface Index<T> {
    void addEntry(@NonNull String key, @NonNull T data);
    @Nullable List<T> findEntries(@NonNull String searchQuery);
}
