package elmeniawy.eslam.imagepicker.model.api

import elmeniawy.eslam.imagepicker.model.data_classes.UploadResponse
import elmeniawy.eslam.imagepicker.utils.RandomString
import elmeniawy.eslam.imagepicker.utils.getExtension
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import java.io.File
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response

/**
 * ApiRepository
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 11:45 AM.
 */
class ApiRepository(private val apiService: ApiService) : ApiRepo {
    override fun uploadPhotoAsync(photo: File): Deferred<Response<UploadResponse>> {
        val imageRequestFile =
            photo.asRequestBody("application/x-www-form-urlencoded".toMediaType())

        val photoPart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "FileData",
            RandomString().nextString() + getExtension(photo.absolutePath),
            imageRequestFile
        )

        return apiService.uploadPhotoAsync(photoPart)
    }
}