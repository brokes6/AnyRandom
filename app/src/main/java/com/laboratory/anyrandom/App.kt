package com.laboratory.anyrandom

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.laboratory.anyrandom.dao.AppDatabase

class App : Application() {

    companion object {
        private var db: AppDatabase? = null

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context // 注入懒加载 全局 context

        fun getDatabase(context: Context): AppDatabase? {
            if (db == null) {
                synchronized(AppDatabase::class.java) {
                    if (db == null) {
                        db = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "RandomData"
                        ).fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return db
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
//        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
//            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
//                (activity as IActivity).initView()
//            }
//
//            override fun onActivityStarted(activity: Activity) {
//                (activity as IActivity).initData()
//            }
//
//            override fun onActivityResumed(p0: Activity) {
//            }
//
//            override fun onActivityPaused(p0: Activity) {
//            }
//
//            override fun onActivityStopped(p0: Activity) {
//            }
//
//            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
//            }
//
//            override fun onActivityDestroyed(p0: Activity) {
//            }
//
//        })
    }

}