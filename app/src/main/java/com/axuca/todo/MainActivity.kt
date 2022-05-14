package com.axuca.todo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBarGradiant(this)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeButtonEnabled(true)
    }

    private fun setStatusBarGradiant(activity: Activity) {
        val window: Window = activity.window
        val background = ContextCompat.getDrawable(activity, R.drawable.gradient_bar)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor = ContextCompat.getColor(activity, android.R.color.transparent)

        window.setBackgroundDrawable(background)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return findNavController(R.id.fragmentContainerView).navigateUp() || super.onSupportNavigateUp()
//    }
}