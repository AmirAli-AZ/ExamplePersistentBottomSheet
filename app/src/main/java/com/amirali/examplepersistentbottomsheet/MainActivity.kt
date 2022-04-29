package com.amirali.examplepersistentbottomsheet

import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.amirali.examplepersistentbottomsheet.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var behavior: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        behavior = BottomSheetBehavior.from(binding.bottomSheet)
        behavior.addBottomSheetCallback(bottomSheetCallBack)

        val list = mutableListOf(
            "Page 1","Page 2","Page 3"
        )

        val adapter = ViewPagerAdapter(list)

        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout , binding.viewPager) { item, position ->
            item.text = list[position]
        }.attach()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if ((resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES)
                binding.imgLine.setImageResource(R.drawable.ic_dark_line)
            else
                binding.imgLine.setColorFilter(R.drawable.ic_light_line)
        }
    }

    private val bottomSheetCallBack = object: BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when (newState) {
                BottomSheetBehavior.STATE_EXPANDED ->
                    Toast.makeText(this@MainActivity, "expanded", Toast.LENGTH_SHORT).show()
                BottomSheetBehavior.STATE_COLLAPSED ->
                    Toast.makeText(this@MainActivity, "collapsed", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            Log.d("MainActivity", "onSlide: $slideOffset")
        }
    }
}