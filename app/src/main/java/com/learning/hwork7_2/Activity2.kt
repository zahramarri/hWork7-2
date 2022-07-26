package com.learning.hwork7_2

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.learning.hwork7_2.QuizContent.answers
import com.learning.hwork7_2.QuizContent.isCheated
import com.learning.hwork7_2.QuizContent.listOfQuestions
import com.learning.hwork7_2.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {
    private var isCheated = false
    private lateinit var binding: Activity2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val indexOfQuestion: Int = intent.getIntExtra(EXTRA_MESSAGE_QUESTION_INDEX, 0)
        val question = listOfQuestions[indexOfQuestion]

        binding.buttonShowAnswer.setOnClickListener{
//            isCheated[question] = true
            binding.textViewAnswer.visibility = View.VISIBLE
            binding.textViewAnswer.text = answers[question].toString()
            isCheated = true
        }

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

    private fun returnResult(){
        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_MESSAGE_IS_CHEATED, isCheated)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

}