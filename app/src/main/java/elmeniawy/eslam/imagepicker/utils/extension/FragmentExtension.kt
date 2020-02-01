package elmeniawy.eslam.imagepicker.utils.extension

import androidx.fragment.app.Fragment

/**
 * FragmentExtension
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 11:57 AM.
 */

fun Fragment.getStatusBarHeight(): Int {
    val resources = this.resources
    var statusBarHeight = 0

    val resourceId = resources.getIdentifier(
        "status_bar_height", "dimen", "android"
    )

    if (resourceId > 0) {
        statusBarHeight = resources.getDimensionPixelSize(resourceId)
    }

    return statusBarHeight
}