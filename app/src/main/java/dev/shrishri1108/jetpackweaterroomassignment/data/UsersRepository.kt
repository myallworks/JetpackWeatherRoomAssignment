package dev.shrishri1108.jetpackweaterroomassignment.data

import android.util.Log
import dev.shrishri1108.jetpackweaterroomassignment.data.local.database.Users
import dev.shrishri1108.jetpackweaterroomassignment.data.local.database.UsersDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


interface UsersRepository {
    val users: Flow<List<UsersImp>>
    suspend fun insert(user: UsersImp)

    suspend fun delete(user: UsersImp)

}


class UsersRepositoryImp @Inject constructor(
    private val usersDao: UsersDao
) : UsersRepository {
    override val users: Flow<List<UsersImp>>
        get() = usersDao.getUsers().map { items ->
            items.map {
                UsersImp(
                    id = it.id,
                    email = it.email,
                    lastName = it.lastName,
                    firstName = it.firstName
                )
            }
        }

    override suspend fun insert(user: UsersImp) =
        usersDao.insertUser(
            Users(
                email = user.email,
                lastName = user.lastName,
                firstName = user.firstName
            )
        )

    override suspend fun delete(user: UsersImp) {
        Log.d("logs", "Deleting user with id: ${user.id}")
        usersDao.deleteUser(
            Users(
                id = user.id,
                email = user.email,
                lastName = user.lastName,
                firstName = user.firstName
            )
        )
    }

}