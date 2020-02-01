package elmeniawy.eslam.imagepicker.utils.extension

import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import elmeniawy.eslam.imagepicker.R

/**
 * ActivityExtension
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 11:56 AM.
 */

fun AppCompatActivity.getNavController(): NavController =
    Navigation.findNavController(this, R.id.nav_host_fragment)

fun AppCompatActivity.drawUnderStatusBar() {
    val window = this.window
    window.statusBarColor = Color.TRANSPARENT

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    } else {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}