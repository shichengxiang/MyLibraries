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
        toast("上课了")
    }

    override fun onClassDismiss() {
        toast("下课了")
    }

    override fun onEnterRoom() {
    }

    override fun onError(code: Int, errMsg: String) {
        toast(errMsg)
    }

    override fun onKickOut(res: Int) {
        toast("被踢出了 $res")
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
//                    "random": "ec0cbf986d1540b183b35343fc0246a1",
//                    "appSign": "E90072956D66FC2999C6DEF4E45EDE03",
//                    "appId": "gaosibx",
//                    "expire": "1576759995",
//                    "nickname": "吴依华",
//                    "userId": "253421",
//                    "ckId": "21398"
                    enterBxRoom(this@MainActivity,"253421","我是测试","21398","E90072956D66FC2999C6DEF4E45EDE03",
                        "ec0cbf986d1540b183b35343fc0246a1","1576759995","E90072956D66FC2999C6DEF4E45EDE03")
//                    enterBxRoom(
//                        this@MainActivity,
//                        etUserId.text.toString(),
//                        etBxNickName.text.toString(),
//                        etCkId.text.toString(),
//                        etAppId.text.toString(),
//                        etRandom.text.toString(),
//                        etExpire.text.toString(),
//                        etAppSign.text.toString()
//                    )
                }
            }
        }
        tvExchangeMode.setOnClickListener {
            mPresenter?.exChangeMode(layout_student, layout_bx)
        }
        tvExchangeMode.setOnClickListener {
            mPresenter?.exChangeMode(layout_student, layout_bx)
            tvCuttentRole.text =
                if (mPresenter?.currentMode == MainPresenter.MODE_BX) "北校入口" else "学生端入口"
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
