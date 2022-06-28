package com.learning.hwork7_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.learning.hwork7_2.QuizContent.answers
import com.learning.hwork7_2.QuizContent.listOfQuestions
import com.learning.hwork7_2.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {
    private lateinit var binding: Activity2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val indexOfQuestion: Int = intent.getIntExtra(EXTRA_MESSAGE, 0)
        val question = listOfQuestions[indexOfQuestion]

        binding.buttonShowAnswer.setOnClickListener{
            binding.textViewAnswer.visibility = View.VISIBLE
            binding.textViewAnswer.text = answers[question].toString()
        }
    }
}