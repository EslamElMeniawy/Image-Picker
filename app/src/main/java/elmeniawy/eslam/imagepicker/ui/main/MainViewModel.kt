package elmeniawy.eslam.imagepicker.ui.main

import androidx.lifecycle.ViewModel
import com.squareup.moshi.Moshi
import elmeniawy.eslam.imagepicker.model.api.ApiRepo

/**
 * MainViewModel
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 12:07 PM.
 */
class MainViewModel(
    private val apiRepository: ApiRepo,
    private val moshi: Moshi
) : ViewModel() {
}