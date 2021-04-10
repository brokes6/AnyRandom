package com.laboratory.anyrandom.dao

import androidx.room.*
import com.laboratory.anyrandom.bean.HomeDetailBean

@Dao
interface HomeDao {

    /**
     * 插入数据
     */
    @Insert
    fun insertAll(data: HomeDetailBean)

    /**
     * 删除数据
     */
    @Delete
    fun deleteAll(data: HomeDetailBean)


    @Query("delete from homedetailbean where cardId=:id")
    fun deleteId(id: Int)

    /**
     *更新数据
     */
    @Update
    fun upData(data: HomeDetailBean)

    /**
     * 获取所有数据
     */
    @Query("SELECT * FROM homedetailbean")
    fun getHomeData(): MutableList<HomeDetailBean>
}