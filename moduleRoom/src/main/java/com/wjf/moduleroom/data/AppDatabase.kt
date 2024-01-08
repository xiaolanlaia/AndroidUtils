package com.wjf.moduleroom.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wjf.moduleroom.entity.User

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/24 10:44
 *
 */

@Database(entities = [User::class], version = 1,exportSchema=false)
abstract class AppDatabase : RoomDatabase() {

    companion object{
        const val DATABASE_NAME = "room_db"
    }
    abstract fun userDao(): UserDao
}