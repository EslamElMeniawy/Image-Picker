package elmeniawy.eslam.imagepicker.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import elmeniawy.eslam.imagepicker.R
import elmeniawy.eslam.imagepicker.databinding.FragmentMainBinding
import elmeniawy.eslam.imagepicker.utils.*
import elmeniawy.eslam.imagepicker.utils.extension.askForPermissions
import elmeniawy.eslam.imagepicker.utils.extension.getStatusBarHeight
import elmeniawy.eslam.imagepicker.utils.extension.isAllPermissionsGranted
import elmeniawy.eslam.imagepicker.utils.extension.isPermissionGranted
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        viewModel.onRequestPermissionsResult(requestCode, isAllPermissionsGranted(grantResults))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val queryImageUrl = if (data?.data != null) {
                //Photo from gallery.
                val imageUri = data.data
                imageUri?.path ?: ""
            } else {
                //Photo from camera.
                getImagePath(context)
            }

            if (queryImageUrl.isNotEmpty()) {
                viewModel.onActivityResult(requestCode, queryImageUrl)
            }
        }
    }

    private fun observeViewModel() {
        observeCheckCameraPermission()
        observeRequestCameraPermission()
        observeShowPicker()
        observeErrorMessageId()
    }

    private fun observeCheckCameraPermission() {
        viewModel.checkCameraPermission.observe(
            viewLifecycleOwner,
            Observer { checkCameraPermission ->
                checkCameraPermission?.let {
                    if (checkCameraPermission) {
                        viewModel.checkCameraPermission.value = null

                        viewModel.gotCameraPermissionStatus(
                            isPermissionGranted(Manifest.permission.CAMERA)
                        )
                    }
                }
            })
    }

    private fun observeRequestCameraPermission() {
        viewModel.requestCameraPermission.observe(
            viewLifecycleOwner,
            Observer { requestCameraPermission ->
                requestCameraPermission?.let {
                    if (requestCameraPermission) {
                        viewModel.requestCameraPermission.value = null

                        askForPermissions(
                            arrayOf(Manifest.permission.CAMERA),
                            CAMERA_PERMISSION_REQUEST_CODE
                        )
                    }
                }
            })
    }

    private fun observeShowPicker() {
        viewModel.showPicker.observe(viewLifecycleOwner, Observer { showPicker ->
            showPicker?.let {
                if (showPicker) {
                    viewModel.checkCameraPermission.value = null
                    startActivityForResult(getPickImageIntent(context), IMAGE_REQUEST_CODE)
                }
            }
        })
    }

    private fun observeErrorMessageId() {
        viewModel.errorMessageId.observe(viewLifecycleOwner, Observer { errorMessageId ->
            errorMessageId?.let {
                viewModel.errorMessageId.value = null
                Toast.makeText(context, getString(errorMessageId), Toast.LENGTH_LONG).show()
            }
        })
    }
}