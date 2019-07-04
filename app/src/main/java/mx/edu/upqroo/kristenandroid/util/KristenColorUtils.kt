package mx.edu.upqroo.kristenandroid.util

import androidx.annotation.ColorInt
import androidx.palette.graphics.Palette
import java.util.*

object KristenColorUtils {

    @ColorInt
    fun getColor(palette: Palette?, fallback: Int): Int {
        if (palette != null) {
            when {
                palette.vibrantSwatch != null -> return palette.vibrantSwatch!!.rgb
                palette.mutedSwatch != null -> return palette.mutedSwatch!!.rgb
                palette.darkVibrantSwatch != null -> return palette.darkVibrantSwatch!!.rgb
                palette.darkMutedSwatch != null -> return palette.darkMutedSwatch!!.rgb
                palette.lightVibrantSwatch != null -> return palette.lightVibrantSwatch!!.rgb
                palette.lightMutedSwatch != null -> return palette.lightMutedSwatch!!.rgb
                palette.swatches.isNotEmpty() -> return Collections.max(
                        palette.swatches, SwatchComparator.instance).rgb
            }
        }
        return fallback
    }

    private class SwatchComparator : Comparator<Palette.Swatch> {

        override fun compare(lhs: Palette.Swatch, rhs: Palette.Swatch): Int {
            return lhs.population - rhs.population
        }

        companion object {
            private var sInstance: SwatchComparator? = null

            internal val instance: SwatchComparator
                get() {
                    if (sInstance == null) {
                        sInstance = SwatchComparator()
                    }
                    return sInstance as SwatchComparator
                }
        }
    }
}
