package elmeniawy.eslam.imagepicker.model.api

import elmeniawy.eslam.imagepicker.model.data_classes.UploadResponse
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * ApiService
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 11:45 AM.
 */
interface ApiService {
    @Multipart
    @POST("upload")
    fun uploadPhotoAsync(@Part photo: MultipartBody.Part): Deferred<Response<UploadResponse>>
}