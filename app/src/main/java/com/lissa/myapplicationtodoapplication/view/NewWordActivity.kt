package com.lissa.myapplicationtodoapplication.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import com.lissa.myapplicationtodoapplication.R
import com.lissa.myapplicationtodoapplication.databinding.ActivityNewWordBinding

class NewWordActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewWordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_word)
        btnOnclick()

    }

    private fun btnOnclick() {
        binding.buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.editWord.text) && TextUtils.isEmpty(binding.etDescription.text) && TextUtils.isEmpty(
                    binding.editTextFinishBy.text
                )
            ) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = binding.editWord.text.toString()
                val desc = binding.etDescription.text.toString()
                val finishBy = binding.editTextFinishBy.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                replyIntent.putExtra(EXTRA_DESC, desc)
                replyIntent.putExtra(EXTRA_FINISH_BY, finishBy)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
        const val EXTRA_DESC = "com.example.android.wordlistsql.DESC"
        const val EXTRA_FINISH_BY = "com.example.android.wordlistsql.FINISH_BY"
    }
}