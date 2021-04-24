package com.lissa.myapplicationtodoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import com.lissa.myapplicationtodoapplication.databinding.ActivitySplashScreenBinding
import com.lissa.myapplicationtodoapplication.view.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash_screen)



        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        binding.splashMotionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            }

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}


        })
    }
    override fun onResume() {
        super.onResume()
        binding.splashMotionLayout.startLayoutAnimation()
    }
    }
