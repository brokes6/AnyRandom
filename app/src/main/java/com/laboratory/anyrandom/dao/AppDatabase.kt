package com.laboratory.anyrandom.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.laboratory.anyrandom.bean.HomeDetailBean
import com.laboratory.anyrandom.bean.RandomBean
import com.laboratory.anyrandom.bean.RandomResultBean

@Database(entities = [RandomBean::class, HomeDetailBean::class,RandomResultBean::class], version = 5)
abstract class AppDatabase : RoomDatabase() {

    abstract val randomDao: RandomDao

    abstract val homeDao: HomeDao

    abstract val randomResultDao: RandomResultDao

}