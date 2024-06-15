package com.ryan.agriaid.data.local.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ArticleViewModel(private val repository: ArticleRepository) : ViewModel() {
    fun insert(article: Article) {
        viewModelScope.launch {
            repository.insert(article)
        }
    }

    fun getAllArticles(): Flow<List<Article>> {
        return repository.getAllArticles()
    }
}