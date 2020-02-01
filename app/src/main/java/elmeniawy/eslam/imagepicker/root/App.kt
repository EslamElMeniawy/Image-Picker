package elmeniawy.eslam.imagepicker.root

import androidx.multidex.MultiDexApplication
import elmeniawy.eslam.imagepicker.di.apiModule
import elmeniawy.eslam.imagepicker.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

/**
 * App
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 11:29 AM.
 */
@Suppress("unused")
class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin.
        startKoin {
            // Use AndroidLogger as Koin Logger.
            androidLogger(Level.INFO)

            // Use the Android context given there.
            androidContext(this@App)

            // Load properties from assets/koin.properties file.
            androidFileProperties()

            // Module list.
            modules(listOf(apiModule, viewModelsModule))
        }

        // Initialize Timber.
        Timber.plant(Timber.DebugTree())
    }
}