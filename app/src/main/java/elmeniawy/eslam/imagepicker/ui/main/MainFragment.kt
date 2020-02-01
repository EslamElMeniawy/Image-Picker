package elmeniawy.eslam.imagepicker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import elmeniawy.eslam.imagepicker.R
import elmeniawy.eslam.imagepicker.databinding.FragmentMainBinding
import elmeniawy.eslam.imagepicker.utils.extension.getStatusBarHeight
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * MainFragment
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 12:07 PM.
 */
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Set container padding to status bar height so that content not cut by status bar.
        layout_main.setPadding(getStatusBarHeight())

        observeViewModel()
        binding.viewModel = viewModel
    }

    private fun observeViewModel() {}
}