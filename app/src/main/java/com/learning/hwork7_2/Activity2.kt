package com.learning.hwork7_2

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.learning.hwork7_2.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {
    private var question: Question? = null
    private lateinit var binding: Activity2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setQuestion()

        binding.buttonShowAnswer.setOnClickListener {
            question?.isCheated = true
            binding.textViewAnswer.visibility = View.VISIBLE
            binding.textViewAnswer.text = question?.answer.toString()
        }

    }

    private fun setQuestion() {
        question = intent.getParcelableExtra(EXTRA_MESSAGE_QUESTION)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                returnResult()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun returnResult() {
        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_MESSAGE_IS_CHEATED, question?.isCheated)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

}