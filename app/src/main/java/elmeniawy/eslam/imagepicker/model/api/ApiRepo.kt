package elmeniawy.eslam.imagepicker.model.api

import elmeniawy.eslam.imagepicker.model.data_classes.UploadResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.io.File

/**
 * ApiRepo
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 11:45 AM.
 */
interface ApiRepo {
    fun uploadPhotoAsync(photo: File): Deferred<Response<UploadResponse>>
}