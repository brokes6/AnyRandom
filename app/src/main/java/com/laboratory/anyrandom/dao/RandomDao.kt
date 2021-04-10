package com.laboratory.anyrandom.dao

import androidx.room.*
import com.laboratory.anyrandom.bean.RandomBean

@Dao
interface RandomDao {

    /**
     * 插入数据
     */
    @Insert
    fun insertAll(data : RandomBean)

    /**
     * 删除数据
     */
    @Delete
    fun deleteAll(data: RandomBean)

    @Query("delete from randombean where `index`=:id")
    fun deleteId(id: Int)

    /**
     *更新数据
     */
    @Update
    fun upData(data: RandomBean)

    /**
     * 获取所有数据
     */
    @Query("SELECT * FROM randombean WHERE `index` = :value")
    fun getRandomData(value : Int): MutableList<RandomBean>
}