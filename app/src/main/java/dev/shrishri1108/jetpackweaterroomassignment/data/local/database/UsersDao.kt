package dev.shrishri1108.jetpackweaterroomassignment.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String
)

@Dao
interface UsersDao {
    @Query("SELECT * FROM Users")
    fun getUsers(): Flow<List<Users>>

    @Insert
    fun insertUser(user: Users)

    @Update
    fun updateUser(user: Users)

    @Delete
    fun deleteUser(user: Users)

}