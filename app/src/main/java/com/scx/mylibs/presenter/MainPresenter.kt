package com.scx.mylibs.presenter

import android.view.View
import com.haoke91.entities.Mode
import com.scx.mylibs.view.BaseView


/**
 * 项目名称：MyLibs
 * 类描述：
 * 创建人：shichengxiang
 * 创建时间：2019/11/25 18:29
 * @version
 */
class MainPresenter : BasePresenter<BaseView> {
    companion object {
        val MODE_STUDENT = 0 //默认学生端code
        val MODE_BX = 1 // 北校
    }

    var currentMode = MODE_STUDENT

    constructor(t: BaseView) : super(t)

    fun exChangeMode(student: View, bx: View) {
        if (currentMode == MODE_STUDENT) {
            student.visibility = View.GONE
            bx.visibility = View.VISIBLE
            currentMode = MODE_BX
        } else {
            student.visibility = View.VISIBLE
            bx.visibility = View.GONE
            currentMode = MODE_STUDENT
        }
    }

    fun enterRoom() {

    }
}
