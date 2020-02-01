package elmeniawy.eslam.imagepicker.utils

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
    "imageUri"
)
fun bindingAdapterIV(
    view: ImageView,
    imageUri: MutableLiveData<String>?
) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()

    parentActivity?.let {
        imageUri?.let {
            imageUri.observe(parentActivity, Observer { value ->
                value?.let {
                    if (value.isNullOrBlank()) {
                        view.setImageResource(R.drawable.placeholder)
                    } else {
                        Glide.with(view.context)
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder)
                            .load(value)
                            .listener(object : RequestListener<Bitmap> {
                                override fun onResourceReady(
                                    resource: Bitmap?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    dataSource: DataSource?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    Timber.v("Successfully loaded image: %s", value)
                                    return false
                                }

                                override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    Timber.w(e, "Failed to load image: %s", value)
                                    return false
                                }
                            })
                            .into(view)
                    }
                }
            })
        }
    }
}