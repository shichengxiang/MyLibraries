package com.scx.mylibs.views;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;
import com.scx.mylibs.R;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * 项目名称：MyLibs
 * 类描述：
 * 创建人：shichengxiang
 * 创建时间：2019/7/8 16:22
 */
public class GSPlayer extends RelativeLayout implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {
    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private MediaPlayer mMediaPlayer;
    private Context mContext;
    public final static int PLAYERSTATUS_PREPARE = 0;
    public final static int PLAYERSTATUS_PLAYING = 1;
    public final static int PLAYERSTATUS_END = 2;
    public final static int PLAYERSTATUS_LEISURE = -1;

    public GSPlayer(Context context) {
        super(context);
        init(context);
    }

    public GSPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_video, null);
        addView(view);
        mSurfaceView = view.findViewById(R.id.gsSurfaceView);
        this.mHolder = mSurfaceView.getHolder();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setLooping(false);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                log("what = " + what + "     extra=" + extra);
                return false;
            }
        });
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                log("play end");
            }
        });
        this.mHolder.addCallback(this);
    }

    public void setUrl(AssetFileDescriptor fileDescriptor){
        try {
            mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),fileDescriptor.getLength());
            if (mSurfaceView.getVisibility() != View.VISIBLE) {
                mSurfaceView.setVisibility(View.VISIBLE);
            }
            mSurfaceView.setZOrderOnTop(true);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setPlayUrl(String url) {
        try {
            mMediaPlayer.setDataSource(url);
            if (mSurfaceView.getVisibility() != View.VISIBLE) {
                mSurfaceView.setVisibility(View.VISIBLE);
            }
            mSurfaceView.setZOrderOnTop(true);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mMediaPlayer.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if (null != mMediaPlayer) {
            mMediaPlayer.start();
        }
    }

    private void log(String log) {
        Log.e("player===", log);
    }
}
