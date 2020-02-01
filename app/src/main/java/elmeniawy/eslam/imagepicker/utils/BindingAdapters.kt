package elmeniawy.eslam.imagepicker.utils

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import elmeniawy.eslam.imagepicker.R
import elmeniawy.eslam.imagepicker.utils.extension.getParentActivity
import timber.log.Timber

/**
 * BindingAdapters
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 11:59 AM.
 */

/**
 * Set [ImageView] online image using picasso
 * Or local image using its URI.
 */
@BindingAdapter(
    "imageUrl",
    "imageUri",
    "imagePlaceholder",
    "mutableTintColorId",
    requireAll = false
)
fun bindingAdapterIV(
    view: ImageView,
    @Nullable imageUrl: MutableLiveData<String>?,
    @Nullable imageUri: MutableLiveData<String>?,
    @Nullable imagePlaceholder: MutableLiveData<Int>?,
    @Nullable tintColorId: MutableLiveData<Int>?
) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()

    parentActivity?.let {
        imageUrl?.let {
            imageUrl.observe(parentActivity, Observer { value ->
                value?.let {
                    if (value.isNotBlank()) {
                        loadImageWithPicasso(
                            view, value,
                            imagePlaceholder?.value ?: R.drawable.placeholder
                        )
                    } else {
                        view.setImageResource(
                            imagePlaceholder?.value ?: R.drawable.placeholder
                        )
                    }
                }
            })
        }

        imageUri?.let {
            imageUri.observe(parentActivity, Observer { value ->
                value?.let {
                    if (value.isNotBlank()) {
                        view.setImageDrawable(Drawable.createFromPath(value))
                    }
                }
            })
        }

        tintColorId?.let {
            tintColorId.observe(parentActivity, Observer { value ->
                value?.let {
                    ImageViewCompat.setImageTintList(
                        view,
                        ColorStateList.valueOf(ContextCompat.getColor(parentActivity, value))
                    )
                }
            })
        }
    }
}

/**
 * Load image with picasso.
 */
private fun loadImageWithPicasso(view: ImageView, imageUrl: String, imagePlaceholder: Int) {
    Picasso.get()
        .load(imageUrl)
        .placeholder(imagePlaceholder)
        .error(imagePlaceholder)
        .fit()
        .into(view, object : Callback {
            override fun onSuccess() {
                Timber.v("Successfully loaded image: $imageUrl")
            }

            override fun onError(e: Exception?) {
                Timber.w(e, "Failed to load image: $imageUrl")
            }
        })
}