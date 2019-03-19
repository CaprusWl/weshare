package com.hqbd.weshare.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.Toast
import com.hqbd.weshare.GlideImageLoader
import com.hqbd.weshare.R
import com.youth.banner.Banner

open class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        val banner = activity?.findViewById<Banner>(R.id.banner)
        Log.d("HomeFragment", "" + getMyImages()?.toList()?.size)
        banner?.setImageLoader(GlideImageLoader())
                ?.setImages(getMyImages())
                ?.start()
    }

    private fun getMyImages() : ArrayList<String>? {
        val images = activity?.assets?.list("images")
        val realJpgImage = arrayListOf<String>()
        var index = 0
        while (index < images?.size!!) {
            val string = "file:///android_asset/images/${images[index]}"
            if (string.contains(".jpg")) {
                realJpgImage.add(string)
            }
            index++
            Log.d("HomeFragment", string)
        }

        return realJpgImage
    }

}