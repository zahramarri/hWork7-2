package com.learning.hwork7_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.learning.hwork7_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var indexOfQuestion = 0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewQuestion.text = QuizContent.listOfQuestions[indexOfQuestion]

        binding.buttonPrevious.setOnClickListener{
            indexOfQuestion --
            binding.textViewQuestion.text = QuizContent.listOfQuestions[indexOfQuestion]
            if (QuizContent.isAnswered[QuizContent.listOfQuestions[indexOfQuestion]] == false) {
                binding.buttonTrue.isClickable = true
                binding.buttonFalse.isClickable = true
            }
        }

        binding.buttonNext.setOnClickListener{
            indexOfQuestion ++
            binding.textViewQuestion.text = QuizContent.listOfQuestions[indexOfQuestion]
            if (QuizContent.isAnswered[QuizContent.listOfQuestions[indexOfQuestion]] == false) {
                binding.buttonTrue.isClickable = true
                binding.buttonFalse.isClickable = true
            }
        }

        binding.buttonTrue.setOnClickListener{
            if (binding.buttonTrue.text.toString().toBoolean() == QuizContent.answers[binding.textViewQuestion.text]) {
                Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "INCORRECT", Toast.LENGTH_SHORT).show()
            }
            QuizContent.isAnswered[QuizContent.listOfQuestions[indexOfQuestion]] = true
            it.isClickable = false
            binding.buttonFalse.isClickable = false
        }
        binding.buttonFalse.setOnClickListener{
            if (binding.buttonFalse.text.toString().toBoolean() == QuizContent.answers[binding.textViewQuestion.text]) {
                Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "INCORRECT", Toast.LENGTH_SHORT).show()
            }
            QuizContent.isAnswered[QuizContent.listOfQuestions[indexOfQuestion]] = true
            it.isClickable = false
            binding.buttonTrue.isClickable = false
        }
    }
}