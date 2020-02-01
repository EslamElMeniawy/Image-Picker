package elmeniawy.eslam.imagepicker.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import elmeniawy.eslam.imagepicker.R
import elmeniawy.eslam.imagepicker.utils.extension.getParentActivity
import timber.log.Timber
import java.io.File

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
    imageUri: MutableLiveData<File?>?
) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()

    parentActivity?.let {
        imageUri?.let {
            imageUri.observe(parentActivity, Observer { value ->
//                if (value==null) {
//                    view.setImageResource(R.drawable.placeholder)
//                } else {
//                    //view.setImageDrawable(Drawable.createFromPath(value))
//                    //view.setImageURI(Uri.fromFile(value))
//                    //view.setImageDrawable(Drawable.createFromPath(value.absolutePath))
//                    Picasso.get().load(value).into(view, object : Callback {
//                        override fun onSuccess() {
//                            Timber.v("Successfully loaded image: $value")
//                        }
//
//                        override fun onError(e: Exception?) {
//                            Timber.w(e, "Failed to load image: $value")
//                        }
//                    })
//                }
            })
        }
    }
}