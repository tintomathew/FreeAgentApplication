/*
 * *
 *  * Created by Tinto Mathew on 24/11/2022, 20:53
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 24/11/2022, 20:53
 *
 */

package com.music.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.music.R
import com.squareup.picasso.Picasso

object Extensions {
    @JvmStatic
    @BindingAdapter("bindImageUrl")
    fun bindImage(imageView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            // Load the image in the background using Picasso.
            Picasso.with(imageView.context).load(imgUrl).fit().centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error_sing)
                .into(imageView)
        }
    }

    fun Context.isNetWorkConnected(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager
            .getNetworkCapabilities(network)
        return (capabilities != null
                && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED))
    }
}