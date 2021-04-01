package com.laboratory.anyrandom.dao

import androidx.room.*
import com.laboratory.anyrandom.bean.HomeDetailBean
import com.laboratory.anyrandom.bean.RandomResultBean

@Dao
interface RandomResultDao {

    /**
     * 插入数据
     */
    @Insert
    fun insertAll(data: RandomResultBean)

    /**
     * 删除数据
     */
    @Delete
    fun deleteAll(data: RandomResultBean)

    /**
     *更新数据
     */
    @Update
    fun upData(data: RandomResultBean)

    /**
     * 获取所有数据
     */
    @Query("SELECT * FROM randomresultbean")
    fun getRandomResultData(): MutableList<RandomResultBean>

}