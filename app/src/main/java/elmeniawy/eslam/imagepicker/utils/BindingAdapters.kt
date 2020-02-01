package elmeniawy.eslam.imagepicker.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import elmeniawy.eslam.imagepicker.R
import elmeniawy.eslam.imagepicker.utils.extension.getParentActivity

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
                    if (value.isBlank()) {
                        view.setImageResource(R.drawable.placeholder)
                    } else {
                        view.setImageDrawable(Drawable.createFromPath(value))
                    }
                }
            })
        }
    }
}