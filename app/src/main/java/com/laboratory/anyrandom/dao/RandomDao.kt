package com.laboratory.anyrandom.dao

import androidx.room.*
import com.laboratory.anyrandom.bean.RandomBean

@Dao
interface RandomDao {

    /**
     * 插入数据
     */
    @Insert
    suspend fun insertAll(data : RandomBean)

    /**
     * 删除数据
     */
    @Delete
    suspend fun deleteAll(data: RandomBean)

    @Query("delete from randombean where `index`=:id")
    suspend fun deleteId(id: Int)

    /**
     *更新数据
     */
    @Update
    suspend fun upData(data: RandomBean)

    /**
     * 获取指定ID的所有数据
     */
    @Query("SELECT * FROM randombean WHERE `index` = :value")
    suspend fun getRandomData(value : Int): MutableList<RandomBean>
}