package com.learning.hwork7_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.learning.hwork7_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewQuestion.text = QuizContent.listOfQuestions[0]

        binding.buttonTrue.setOnClickListener{
            if (binding.buttonTrue.text.toString().toBoolean() == QuizContent.questionsAndAnswers[binding.textViewQuestion.text]) {
                Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "INCORRECT", Toast.LENGTH_SHORT).show()
            }
            it.isClickable = false
            binding.buttonFalse.isClickable = false
        }
        binding.buttonFalse.setOnClickListener{
            if (binding.buttonFalse.text.toString().toBoolean() == QuizContent.questionsAndAnswers[binding.textViewQuestion.text]) {
                Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "INCORRECT", Toast.LENGTH_SHORT).show()
            }
            it.isClickable = false
            binding.buttonTrue.isClickable = false
        }
    }
}