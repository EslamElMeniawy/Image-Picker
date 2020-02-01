package elmeniawy.eslam.imagepicker.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import elmeniawy.eslam.imagepicker.R
import elmeniawy.eslam.imagepicker.utils.extension.drawUnderStatusBar
import elmeniawy.eslam.imagepicker.utils.extension.getNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Revert back to app theme to override splash theme in manifest.
        setTheme(R.style.AppTheme)

        setContentView(R.layout.activity_main)

        // Make the status bar transparent and draw app under it.
        drawUnderStatusBar()
    }

    override fun onSupportNavigateUp(): Boolean = getNavController().navigateUp()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val navHostFragment = supportFragmentManager.fragments.first() as? NavHostFragment

        navHostFragment?.let {
            val childFragments = navHostFragment.childFragmentManager.fragments

            childFragments.forEach { fragment ->
                fragment.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}
