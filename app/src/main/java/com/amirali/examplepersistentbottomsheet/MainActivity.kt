package com.amirali.examplepersistentbottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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