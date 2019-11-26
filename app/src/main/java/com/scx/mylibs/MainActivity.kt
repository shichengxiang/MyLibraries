package com.scx.mylibs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.haoke91.room.LiveManager
import com.haoke91.room.interfaces.RoomListener
import com.scx.mylibs.ext.toast
import com.scx.mylibs.presenter.MainPresenter
import com.scx.mylibs.view.BaseView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RoomListener, BaseView {
    var mPresenter: MainPresenter? = null
    override fun onClassBegin() {
    }

    override fun onClassDismiss() {
    }

    override fun onEnterRoom() {
    }

    override fun onError(code: Int, errMsg: String) {
        toast(errMsg)
    }

    override fun onKickOut(res: Int) {
    }

    override fun onWarning(code: Int) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        mPresenter = MainPresenter(this)
        mPresenter?.currentMode =
            if (layout_student.visibility == View.VISIBLE) MainPresenter.MODE_STUDENT else MainPresenter.MODE_BX
        btnEnter.setOnClickListener {
            //            if (!verifyparams()) {
//                toast("请确认参数是否为空")
//            }
            val liveManager = LiveManager.newInstance()
            liveManager.apply {
                isDebug(true)
                setListener(this@MainActivity)
                if (mPresenter?.currentMode == MainPresenter.MODE_STUDENT) {
                    enterRoom(
                        this@MainActivity,
                        etClassTypeId.text.toString(),
                        etClassId.text.toString(),
                        etLessonId.text.toString(),
                        etStudentId.text.toString(),
                        etNickName.text.toString()
                    )
                } else {
                    enterBxRoom(
                        this@MainActivity,
                        etUserId.text.toString(),
                        etCkId.text.toString(),
                        "123",
                        "",
                        "",
                        ""
                    )
                }
            }
        }
        tvExchangeMode.setOnClickListener {
            mPresenter?.exChangeMode(layout_student, layout_bx)
        }
        tvExchangeMode.setOnClickListener {
            mPresenter?.exChangeMode(layout_student, layout_bx)
        }
    }

    private fun verifyparams(): Boolean {
        if (etClassId.text.toString().isNullOrEmpty()) return false
        if (etLessonId.text.toString().isNullOrBlank()) return false
        if (etStudentId.text.toString().isNullOrBlank()) return false
        if (etNickName.text.toString().isNullOrBlank()) return false
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.destory()
    }

    override fun onView(code: Int, msg: String) {
    }

}
