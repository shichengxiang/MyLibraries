package com.scx.mylibs

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

/**
 * 项目名称：MyLibs
 * 类描述：
 * 创建人：shichengxiang
 * 创建时间：2019/11/5 18:50
 */
class MyApplication : Application(){
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}
