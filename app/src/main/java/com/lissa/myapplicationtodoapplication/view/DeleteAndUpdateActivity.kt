package com.lissa.myapplicationtodoapplication.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.lissa.myapplicationtodoapplication.R
import com.lissa.myapplicationtodoapplication.database.Word
import com.lissa.myapplicationtodoapplication.database.WordsApplication
import com.lissa.myapplicationtodoapplication.databinding.ActivityAddAndDeleteBinding
import com.lissa.myapplicationtodoapplication.viewmodel.WordViewModel
import com.lissa.myapplicationtodoapplication.viewmodel.WordViewModelFactory

class DeleteAndUpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddAndDeleteBinding
    private var application: WordsApplication? = null
    private var wordViewModel: WordViewModel? = null
    private var id: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_and_delete)
        application = WordsApplication(this)
        wordViewModel = ViewModelProviders.of(this, WordViewModelFactory(application!!.repository))
            .get(WordViewModel::class.java)
        val task: String? = intent.getStringExtra("task")
        val description: String? = intent.getStringExtra("description")
        val finishBy: String? = intent.getStringExtra("finishBy")
        id = intent.getIntExtra("primaryKey", 0)
        val finished: Boolean? = intent.getBooleanExtra("finished", false)
        setTextData(task, description, finishBy, finished)

        btnOnClick()
    }

    private fun setTextData(
        task: String?,
        description: String?,
        finishBy: String?,
        finished: Boolean?
    ) {
        binding.editTextTask.setText(task)
        binding.editTextDesc.setText(description)
        binding.editTextFinishBy.setText(finishBy)

    }

    private fun btnOnClick() {
        binding.buttonDelete.setOnClickListener {
            deleteAlertDialog()
        }
        binding.buttonUpdate.setOnClickListener {
            updateTask()
        }

    }

    private fun updateTask() {
        val words = binding.editTextTask.text.toString()
        val description = binding.editTextDesc.text.toString()
        val finishBy = binding.editTextFinishBy.text.toString()

        val word =
            Word(id!!.toInt(), words, description, finishBy, binding.checkBoxFinished.isChecked)
        wordViewModel!!.update(word)
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun deleteAlertDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure?")
        builder.setPositiveButton("Yes",
            DialogInterface.OnClickListener { _, i ->
                deleteTask()
            })
        builder.setNegativeButton("No",
            DialogInterface.OnClickListener { _, i -> })

        val ad: AlertDialog = builder.create()
        ad.show()
    }

    private fun deleteTask() {
        val words = binding.editTextTask.text.toString()
        val description = binding.editTextDesc.text.toString()
        val finishBy = binding.editTextFinishBy.text.toString()
        val word =
            Word(id!!.toInt(), words, description, finishBy, binding.checkBoxFinished.isChecked)
        wordViewModel!!.delete(word)
        startActivity(Intent(this, MainActivity::class.java))

    }


}