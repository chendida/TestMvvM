package zyxd.fish.mvvmtest

import android.app.Application
import android.content.Context
import android.util.Log
import kotlin.properties.Delegates

class App : Application() {
    companion object{
        var context : Context by Delegates.notNull()
            private set
    }
    override fun onCreate() {
        super.onCreate()
        context = this
        Log.i("App","onCreate")
    }
}