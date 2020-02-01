package elmeniawy.eslam.imagepicker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.moshi.Moshi
import elmeniawy.eslam.imagepicker.R
import elmeniawy.eslam.imagepicker.model.api.ApiRepo
import elmeniawy.eslam.imagepicker.utils.CAMERA_PERMISSION_REQUEST_CODE
import elmeniawy.eslam.imagepicker.utils.IMAGE_REQUEST_CODE
import timber.log.Timber

/**
 * MainViewModel
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 12:07 PM.
 */
class MainViewModel(
    private val apiRepository: ApiRepo,
    private val moshi: Moshi
) : ViewModel() {
    val imageUri: MutableLiveData<String> = MutableLiveData()
    val checkCameraPermission: MutableLiveData<Boolean> = MutableLiveData()
    val requestCameraPermission: MutableLiveData<Boolean> = MutableLiveData()
    val showPicker: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessageId: MutableLiveData<Int> = MutableLiveData()

    init {
        imageUri.value = ""
    }

    fun addClicked() {
        checkCameraPermission.value = true
    }

    fun gotCameraPermissionStatus(isGranted: Boolean) {
        if (isGranted) {
            showPicker.value = true
        } else {
            requestCameraPermission.value = true
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, isGranted: Boolean) {
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if (isGranted) {
                    showPicker.value = true
                } else {
                    errorMessageId.value = R.string.camera_permission_required
                }
            }
        }
    }

    fun onActivityResult(requestCode: Int, queryImageUrl: String) {
        when (requestCode) {
            IMAGE_REQUEST_CODE -> {
                Timber.d("ReturnedPath: $queryImageUrl")
                imageUri.value = queryImageUrl
            }
        }
    }
}