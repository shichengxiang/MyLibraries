package com.scx.mylibs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.scx.mylibs.ext.open
import com.scx.mylibs.ext.tryCatch
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
//        gsVideo.setVideoPath()
//        AssetFileDescriptor afd = assetManager.openFd("one.mp3");
//        MediaPlayer mediaPlayer = new MediaPlayer();
//        mediaPlayer.setDataSource(afd.getFileDescriptor()
//            , afd.getStartOffset()
//            , afd.getLength());
//        gsVideo.setVideoPath("https://www.iqiyi.com/897a7808-32ac-4ad0-8152-340960307499")
        gsVideo.setUrl(assets.openFd("record.mp4"))
    }

}
