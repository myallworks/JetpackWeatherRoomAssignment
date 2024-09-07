package dev.shrishri1108.jetpackweaterroomassignment.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.shrishri1108.jetpackweaterroomassignment.data.local.CONSTANTS
import dev.shrishri1108.jetpackweaterroomassignment.data.local.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            CONSTANTS.NAMEDATABASE
        ).build()


    @Provides
    fun provideUsersDao(appDatabase: AppDatabase) =
        appDatabase.usersDao()
}