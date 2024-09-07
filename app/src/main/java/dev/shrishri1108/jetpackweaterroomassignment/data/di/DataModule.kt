package dev.shrishri1108.jetpackweaterroomassignment.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.shrishri1108.jetpackweaterroomassignment.data.UsersRepository
import dev.shrishri1108.jetpackweaterroomassignment.data.UsersRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsUserRepository(usersRepositoryImp: UsersRepositoryImp): UsersRepository

}