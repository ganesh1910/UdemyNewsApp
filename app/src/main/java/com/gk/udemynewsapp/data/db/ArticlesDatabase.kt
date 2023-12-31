package com.gk.udemynewsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gk.udemynewsapp.data.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun getArticlesDAO(): ArticlesDAO
}