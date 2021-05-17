package com.laboratory.anyrandom

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.alibaba.android.arouter.launcher.ARouter
import com.laboratory.anyrandom.dao.AppDatabase

class App : Application() {

    companion object {
        private var DB: AppDatabase? = null

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context // 注入懒加载 全局 context

        fun getDatabase(context: Context): AppDatabase {
            return DB ?: synchronized(this){
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "RandomData"
                ).build()
                DB = db
                db
            }
        }
    }
    private var isDebug : Boolean = false

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        if (isDebug) {          // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)// 尽可能早，推荐在Application中初始化
    }

}