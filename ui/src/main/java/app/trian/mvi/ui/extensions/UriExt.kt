/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.ui.extensions

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

fun Uri.getBitmap(c: ContentResolver): Bitmap?{
    return try {
        val input =  c.openInputStream(this)
        Bitmap.createScaledBitmap(
            BitmapFactory.decodeStream(input),
            120,
            120,
            false
        )
    }catch (e:Exception){
        null
    }
}