package elmeniawy.eslam.imagepicker.di

import elmeniawy.eslam.imagepicker.ui.main.MainViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

/**
 * ViewModelsModule
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 11:46 AM.
 */

val viewModelsModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
}