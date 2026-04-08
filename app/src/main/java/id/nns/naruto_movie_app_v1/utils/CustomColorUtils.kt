package id.nns.naruto_movie_app_v1.utils

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette

object CustomColorUtils {
    fun getLightMutedColorFromImage(
        context: Context,
        img: Int,
        defaultColor: Color
    ) : Color {
        val drawable = ContextCompat.getDrawable(context, img) as? BitmapDrawable
        val bitmap = drawable?.bitmap ?: return defaultColor
        val palette = Palette.from(bitmap).generate()
        val lightMutedColorInt = palette.getLightMutedColor(defaultColor.toArgb())
        val adjustedColor = Color(
            ColorUtils.blendARGB(lightMutedColorInt, Color.White.toArgb(), 0.6f)
        )
        return adjustedColor
    }
}
