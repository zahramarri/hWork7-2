package com.learning.hwork7_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.learning.hwork7_2.QuizContent.answers
import com.learning.hwork7_2.QuizContent.isAnswered
import com.learning.hwork7_2.QuizContent.isCheated
import com.learning.hwork7_2.QuizContent.listOfQuestions
import com.learning.hwork7_2.databinding.ActivityMainBinding

const val EXTRA_MESSAGE = "EXTRA_MESSAGE"

class MainActivity : AppCompatActivity() {
    private var indexOfQuestion = 0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                indexOfQuestion = getInt(STATE_INDEX)
            }
        }

        binding.textViewQuestion.text = listOfQuestions[indexOfQuestion]
        binding.buttonPrevious.isEnabled = false

        binding.buttonPrevious.setOnClickListener{
            indexOfQuestion --
            binding.textViewQuestion.text = listOfQuestions[indexOfQuestion]

            if (isAnswered[listOfQuestions[indexOfQuestion]] == false) {
                binding.buttonTrue.isEnabled = true
                binding.buttonFalse.isEnabled = true
            } else {
                binding.buttonTrue.isEnabled = false
                binding.buttonFalse.isEnabled = false
            }

            binding.buttonNext.isEnabled = true
            if (indexOfQuestion == 0) {
                it.isEnabled = false
            }
        }

        binding.buttonNext.setOnClickListener{
            indexOfQuestion ++
            binding.textViewQuestion.text = listOfQuestions[indexOfQuestion]
            if (isAnswered[listOfQuestions[indexOfQuestion]] == false) {
                binding.buttonTrue.isEnabled = true
                binding.buttonFalse.isEnabled = true
            } else {
                binding.buttonTrue.isEnabled = false
                binding.buttonFalse.isEnabled = false
            }

            binding.buttonPrevious.isEnabled = true
            if (indexOfQuestion == 9) {
                it.isEnabled = false
            }
        }

        binding.buttonTrue.setOnClickListener{
            if (isCheated[listOfQuestions[indexOfQuestion]] == true) {
                Toast.makeText(this, "Cheating is wrong!", Toast.LENGTH_SHORT).show()
            } else {
                if (binding.buttonTrue.text.toString().toBoolean() == answers[binding.textViewQuestion.text]) {
                    Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "INCORRECT", Toast.LENGTH_SHORT).show()
                }
                isAnswered[listOfQuestions[indexOfQuestion]] = true
                it.isEnabled = false
                binding.buttonFalse.isEnabled = false
            }
        }
        binding.buttonFalse.setOnClickListener{
            if (isCheated[listOfQuestions[indexOfQuestion]] == true) {
                Toast.makeText(this, "Cheating is wrong!", Toast.LENGTH_SHORT).show()
            } else {
                if (binding.buttonFalse.text.toString().toBoolean() == answers[binding.textViewQuestion.text]) {
                    Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "INCORRECT", Toast.LENGTH_SHORT).show()
                }
                isAnswered[listOfQuestions[indexOfQuestion]] = true
                it.isEnabled = false
                binding.buttonTrue.isEnabled = false
            }
        }

        binding.buttonCheat.setOnClickListener{
            goToActivity2()
        }
    }

    private fun goToActivity2() {
        val intent = Intent(this, Activity2::class.java)
        intent.putExtra(EXTRA_MESSAGE, indexOfQuestion)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(STATE_INDEX, indexOfQuestion)
        }
        super.onSaveInstanceState(outState)
    }

    companion object{
        const val STATE_INDEX = "index of question state"
    }

    private fun initQuestions() {
        val questionList = mutableListOf<Question>(
        Question(R.string.question1),
        Question(R.string.question2),
        Question(R.string.question3),
        Question(R.string.question4),
        Question(R.string.question5),
        Question(R.string.question6),
        Question(R.string.question7),
        Question(R.string.question8),
        Question(R.string.question9),
        Question(R.string.question10))
    }
}