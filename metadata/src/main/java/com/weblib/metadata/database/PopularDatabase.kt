package com.weblib.metadata.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.weblib.metadata.Movie
import com.weblib.metadata.converter.IntListConverter


@Database(entities = [Movie::class], version = 2, exportSchema = false)
@TypeConverters(IntListConverter::class)
abstract class PopularDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}