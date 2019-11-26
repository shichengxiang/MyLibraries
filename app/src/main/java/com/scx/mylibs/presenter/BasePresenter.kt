package com.scx.mylibs.presenter

import android.content.Context
import com.scx.mylibs.view.BaseView

/**
 * 项目名称：MyLibs
 * 类描述：
 * 创建人：shichengxiang
 * 创建时间：2019/11/25 18:15
 * @version
 */
abstract class BasePresenter<T> where T : BaseView {
    var mView: T? = null
    var mContext: Context? = null

    constructor(t: T) {
        this.mView = t
        if (t is Context)
            mContext = t
    }

    fun destory() {
        mView = null
        mContext = null
    }
}
