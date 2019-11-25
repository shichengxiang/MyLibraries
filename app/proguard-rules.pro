# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-ignorewarnings                     # 忽略警告，避免打包时某些警告出现
-optimizationpasses 5               # 指定代码的压缩级别
-dontusemixedcaseclassnames         # 是否使用大小写混合
#-skipnonpubliclibraryclasses    # 是否混淆第三方jar dont
#-dontpreverify                      # 混淆时是否做预校验
-verbose                            # 混淆时是否记录日
-dontwarn android.support.v4.**
-keep class com.gaosiedu.**{*;} #实体类
-keep class com.haoke91.entities.**{*;}#实体类
-keep class com.haoke91.baselibrary.model.**{*;}#实体类
-keep class com.haoke91.im.**{*;}#消息
-keep class airbnb.lottie.** {*;} #lottie 动画view
-keep class com.alibaba.android.**{*;}
-keep class comalibaba.fastjson.** {*;} #fastjson
-keep class com.alibaba.sdk.android.**{*;}
-keep class android.support.**{*;}# v7
-keep class tencent.**{*;} #腾讯播放器
-keep class com.tencent.**{*;}
-keep class com.bumptech.glide.**{*;} #glide
-keep class com.lzy.okgo.**{*;} #okgo
# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod
#RxJava RxAndroid OkHttp
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
#-keep attributes Signature
#-keep attributes Exceptions
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-dontwarn android.os.**
-keep class android.support.v4.** { *; }        # 保持哪些类不被混淆
-keep class com.baidu.** { *; }
-keep class vi.com.gdi.bgl.android.**{*;}
-keep class android.os.**{*;}
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.support.v4.widget
-keep public class * extends com.sqlcrypt.database
-keep public class * extends com.sqlcrypt.database.sqlite
-keep public class * extends com.treecore.**
-keep public class * extends de.greenrobot.dao.**
-keepclasseswithmembernames class * {       # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {            # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {            # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity { #保持类成员
   public void *(android.view.View);
}

-keepclassmembers enum * {                  # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {    # 保持 Parcelable 不被混淆
  public static final android.os.Parcelable$Creator *;
}
#talk
-keep class com.classroomsdk.**{*;}
-keep class com.eduhdsdk.**{*;}
# 实体 +拓课
-keep class com.haoke91.entities.**{*;}
-keep class com.gaosiedu.**{*;}
-keep class com.haoke91.**{*;}
-keep class com.haoke91.a91edu.domain.**{*;}
-keep class com.haoke91.a91edu.entities.**{*;}
-keep class com.eduhdsdk.**{*;}
-keep class com.classroomsdk.**{*;}
-keep class com.talkcloud.**{*;}
-keep class * extends **.View{*;}

#如果引用了v4或者v7包
-dontwarn android.support.**
-keep class android.support.**{*;}

#泛型
-keepattributes Signature

-keep public class **.R$*{
   public static final int *;
}

-keep class **$Properties
-keep class org.tkwebrtc.**{*;}
-keep class org.chromium.**{*;}
-keep class org.apache.**{*;}
-keep class org.xwalk.**{*;}
-keep class pl.droidsonroids.**{*;}
-keep class com.hitry.**{*;}
-keep class com.talkcloud.**{*;}
-keep class io.tksocket.**{*;}
-keep class io.socket.**{*;}
-keep class skin.support.**{*;}
-keep class net.hockeyapp.android.**{*;}
-keep class com.classroomsdk.**{*;}
-keepattributes *Annotation*
-keepattributes *JavascriptInterface*
-keepclassmembers class com.classroomsdk.JSWhitePadInterface{
  public *;
}


