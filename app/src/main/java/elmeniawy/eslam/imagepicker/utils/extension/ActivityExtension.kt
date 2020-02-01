package elmeniawy.eslam.imagepicker.utils.extension

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