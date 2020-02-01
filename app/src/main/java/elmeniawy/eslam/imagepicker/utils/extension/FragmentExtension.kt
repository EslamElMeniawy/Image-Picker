package elmeniawy.eslam.imagepicker.utils.extension

import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
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

fun Fragment.isPermissionGranted(permission: String): Boolean {
    val context = this.context

    if (context != null) {
        return ContextCompat
            .checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    return false
}

fun Fragment.askForPermissions(permissions: Array<String>, requestCode: Int) {
    requestPermissions(permissions, requestCode)
}

fun isAllPermissionsGranted(grantResults: IntArray): Boolean {
    var isGranted = true

    for (grantResult in grantResults) {
        isGranted = grantResult == PackageManager.PERMISSION_GRANTED

        if (!isGranted) {
            break
        }
    }

    return isGranted
}