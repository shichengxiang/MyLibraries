package com.scx.mylibs.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import java.lang.Exception

/**
 * 项目名称：MyLibs
 * 类描述：
 * 创建人：shichengxiang
 * 创建时间：2019/8/22 10:27
 * @version
 */

fun Context.open(clz: Class<Activity>) {
    if (this !is Activity) {
        log("context that start activity is not activity")
    }
    startActivity(Intent(this, clz))
}

fun log(msg: String) {
    Log.d("livemanger===", msg)
}

fun toast(msg: String) {
    ToastUtils.showShort(msg)
}

fun <T> tryCatch(block: () -> T) {
    try {
        block.invoke()
    } catch (ex: Exception) {
        "异常"
    }
}

/* 异常默认值*/
inline fun <T> (() -> T).default(block: (Exception) -> T): T {
    try {
        return invoke()
    } catch (ex: Exception) {
        return block(ex)
    }
}

/*string 去除null*/
fun String?.str(block: () -> String): String {
    if (this != null) {
        return ""
    } else {
        return block()
    }
}

/* textview 设置text */
fun View.txt(text: String?) {
    if (this is TextView) {
        this.text = text.str { "" }
    }
}
