package com.scx.mylibs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.haoke91.room.LiveManager
import com.haoke91.room.interfaces.RoomListener
import com.scx.mylibs.ext.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RoomListener {
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
        btnEnter.setOnClickListener {
            if (!verifyparams()) {
                toast("请确认参数是否为空")
            }
            val liveManager = LiveManager.newInstance()
            liveManager.apply {
                isDebug(true)
                setListener(this@MainActivity)
                enterRoom(
                    this@MainActivity,
                    etClassTypeId.text.toString(),
                    etClassId.text.toString(),
                    etLessonId.text.toString(),
                    etStudentId.text.toString(),
                    etNickName.text.toString()
                )
            }
        }
    }

    private fun verifyparams(): Boolean {
        if (etClassId.text.toString().isNullOrEmpty()) return false
        if (etLessonId.text.toString().isNullOrBlank()) return false
        if (etStudentId.text.toString().isNullOrBlank()) return false
        if (etNickName.text.toString().isNullOrBlank()) return false
        return true
    }

}
