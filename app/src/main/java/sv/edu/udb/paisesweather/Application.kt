package sv.edu.udb.paisesweather

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        Glide.get(this).apply {
            var diskCacheStrategy = DiskCacheStrategy.ALL
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).trimMemory(level)
    }
}