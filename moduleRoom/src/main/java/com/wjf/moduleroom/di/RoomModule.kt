package com.wjf.moduleroom.di

import androidx.room.Room
import com.wjf.moduleroom.RoomApplication
import com.wjf.moduleroom.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/24 12:53
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app : RoomApplication) : AppDatabase{
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }
}