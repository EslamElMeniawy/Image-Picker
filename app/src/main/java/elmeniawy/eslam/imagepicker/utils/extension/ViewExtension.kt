package elmeniawy.eslam.imagepicker.utils.extension

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * ViewExtension
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 11:56 AM.
 */

fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context

    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }

        context = context.baseContext
    }

    return null
}