package com.laboratory.anyrandom.dao

import androidx.room.*
import com.laboratory.anyrandom.bean.UserBean

@Dao
interface UserDao {
    /**
     * 插入数据
     */
    @Insert
    fun insertAll(data: UserBean)

    /**
     * 删除数据
     */
    @Delete
    fun deleteAll(data: UserBean)

    /**
     *更新数据
     */
    @Update
    fun upData(data: UserBean)

    /**
     * 获取所有数据
     */
    @Query("SELECT * FROM userbean")
    fun getUserData(): MutableList<UserBean>
}