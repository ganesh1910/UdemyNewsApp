package com.gk.udemynewsapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gk.udemynewsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(article: Article): Long

    @Delete
    suspend fun deleteArticle(article: Article): Int

    @Query("SELECT * FROM articles")
    fun getAllSavedArticles(): Flow<List<Article>>
}