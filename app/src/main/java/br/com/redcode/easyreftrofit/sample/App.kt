package br.com.redcode.easyreftrofit.sample

import android.app.Application
import android.content.Context
import timber.log.Timber
import java.lang.ref.WeakReference

/**
 * Created by pedrofsn on 30/03/2019.
 */
class App : Application() {

    companion object {
        private lateinit var mContext: WeakReference<Context>

        fun getContext() = mContext.get()
    }

    init {
        Timber.tag("pedrofsn")
    }

    override fun onCreate() {
        super.onCreate()
        mContext = WeakReference(this)
        initializeTimber()
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}