package elmeniawy.eslam.imagepicker.utils.extension

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import elmeniawy.eslam.imagepicker.R

/**
 * FragmentExtension
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 11:57 AM.
 */

fun Fragment.getNavController(): NavController? {
    val activity = this.activity

    activity?.let {
        return Navigation.findNavController(activity, R.id.nav_host_fragment)
    }

    return null
}