package elmeniawy.eslam.imagepicker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import elmeniawy.eslam.imagepicker.R
import elmeniawy.eslam.imagepicker.model.api.ApiRepo
import elmeniawy.eslam.imagepicker.model.data_classes.UploadResponse
import elmeniawy.eslam.imagepicker.utils.CAMERA_PERMISSION_REQUEST_CODE
import elmeniawy.eslam.imagepicker.utils.IMAGE_REQUEST_CODE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File

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
                uploadPhoto(File(queryImageUrl))
            }
        }
    }

    private fun uploadPhoto(photo: File) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val deferred = apiRepository.uploadPhotoAsync(photo)
                val response = deferred.await()

                launch(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        handleResponse(response.body())
                    } else {
                        handleException(
                            Exception("${response.code()} Response was not successful.")
                        )
                    }
                }
            } catch (exception: Exception) {
                launch(Dispatchers.Main) {
                    handleException(exception)
                }
            }
        }
    }

    private fun handleResponse(response: UploadResponse?) {
        response?.let {
            val jsonAdapter: JsonAdapter<UploadResponse> =
                moshi.adapter(UploadResponse::class.java)

            Timber.d("ProfileResponse: ${jsonAdapter.toJson(response)}")
        }
    }

    private fun handleException(exception: Exception) {
        Timber.e(exception)
    }
}