package com.lissa.myapplicationtodoapplication.view


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.lissa.myapplicationtodoapplication.R
import com.lissa.myapplicationtodoapplication.SetOnClickListner
import com.lissa.myapplicationtodoapplication.WordListAdapter
import com.lissa.myapplicationtodoapplication.database.Word
import com.lissa.myapplicationtodoapplication.database.WordsApplication
import com.lissa.myapplicationtodoapplication.databinding.ActivityMainBinding
import com.lissa.myapplicationtodoapplication.viewmodel.WordViewModel
import com.lissa.myapplicationtodoapplication.viewmodel.WordViewModelFactory


class MainActivity : AppCompatActivity(), SetOnClickListner {

    private val newWordActivityRequestCode = 1
    private var application: WordsApplication? = null
    private var wordViewModel: WordViewModel? = null

    /* private val wordViewModel: WordViewModel by viewModels {
          WordViewModelFactory((application as WordsApplication).repository)
      }
  */
    var adapter: WordListAdapter? = null

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        application = WordsApplication(this)
        wordViewModel = ViewModelProviders.of(this, WordViewModelFactory(application!!.repository))
            .get(WordViewModel::class.java)
        initAdapter()
        initObserver()
        btnOnclick()
    }

    private fun btnOnclick() {
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    private fun initObserver() {
        wordViewModel!!.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.

            words.let { adapter!!.submitList(it) }
        }
    }

    private fun initAdapter() {
        adapter = WordListAdapter()
        binding.recyclerview.adapter = adapter
        adapter!!.setonclik = this

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val words = intentData?.getStringExtra(NewWordActivity.EXTRA_REPLY)
            val description = intentData?.getStringExtra(NewWordActivity.EXTRA_DESC)
            val finishBy = intentData?.getStringExtra(NewWordActivity.EXTRA_FINISH_BY)
            Log.e("TAG", "onActivityResult: " + words + ":::" + description + "::" + finishBy)
            // intentData?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { reply ->
            val word = Word(0, words!!, description, finishBy, false)
            wordViewModel!!.insert(word)
            // }
            Log.e("TAG", "onActivityResult: " + word)


        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun listner(
        word: String?,
        description: String?,
        finishBy: String?,
        finished: Boolean,
        id: Int
    ) {

        val intent = Intent(this@MainActivity, DeleteAndUpdateActivity::class.java)
        intent.putExtra("task", word);
        intent.putExtra("description", description);
        intent.putExtra("finishBy", finishBy);
        intent.putExtra("finished", finished);
        intent.putExtra("primaryKey", id);
        startActivity(intent)
        Log.e("TAG", "listner: "+id )
    }




}