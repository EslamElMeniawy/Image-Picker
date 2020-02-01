package elmeniawy.eslam.imagepicker.model.data_classes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UploadResponse(
    @Json(name = "FileExt")
    val fileExt: String? = "",
    @Json(name = "FileId")
    val fileId: String? = "",
    @Json(name = "FileName")
    val fileName: String? = ""
)