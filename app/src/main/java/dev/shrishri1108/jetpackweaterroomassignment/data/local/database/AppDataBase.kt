package dev.shrishri1108.jetpackweaterroomassignment.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Users::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}