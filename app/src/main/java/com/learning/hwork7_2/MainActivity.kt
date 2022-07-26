package com.learning.hwork7_2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.learning.hwork7_2.databinding.ActivityMainBinding

const val EXTRA_MESSAGE_QUESTION = "question"
const val EXTRA_MESSAGE_IS_CHEATED = "cheat"

class MainActivity : AppCompatActivity() {
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                questionList[indexOfQuestion].isCheated =
                    intent?.getBooleanExtra(EXTRA_MESSAGE_IS_CHEATED, false) == true
            }
        }
    private var questionList = arrayListOf<Question>()
    private var indexOfQuestion = 0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            loadQuestions()
            initializeViews()
        } else {
            with(savedInstanceState) {
                indexOfQuestion = getInt(STATE_INDEX)
                questionList =
                    getParcelableArrayList<Question>(STATE_QUESTION_LIST) as ArrayList<Question>
                showTextViewQuestion()
            }
        }

        binding.buttonPrevious.setOnClickListener {
            indexOfQuestion--
            showTextViewQuestion()

            if (!questionList[indexOfQuestion].isAnswered) {
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

        binding.buttonNext.setOnClickListener {
            indexOfQuestion++
            showTextViewQuestion()

            if (!questionList[indexOfQuestion].isAnswered) {
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

        binding.buttonTrue.setOnClickListener {
            if (questionList[indexOfQuestion].isCheated) {
                Toast.makeText(this, "Cheating is wrong!", Toast.LENGTH_SHORT).show()
            } else {
                if (binding.buttonTrue.text.toString()
                        .toBoolean() == questionList[indexOfQuestion].answer
                ) {
                    Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "INCORRECT", Toast.LENGTH_SHORT).show()
                }
                questionList[indexOfQuestion].isAnswered = true
                it.isEnabled = false
                binding.buttonFalse.isEnabled = false
            }
        }

        binding.buttonFalse.setOnClickListener {
            if (questionList[indexOfQuestion].isCheated) {
                Toast.makeText(this, "Cheating is wrong!", Toast.LENGTH_SHORT).show()
            } else {
                if (binding.buttonFalse.text.toString()
                        .toBoolean() == questionList[indexOfQuestion].answer
                ) {
                    Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "INCORRECT", Toast.LENGTH_SHORT).show()
                }
                questionList[indexOfQuestion].isAnswered = true
                it.isEnabled = false
                binding.buttonTrue.isEnabled = false
            }
        }

        binding.buttonCheat.setOnClickListener {
            goToActivity2()
        }
    }

    private fun getQuestionText(): String {
        return getString(questionList[indexOfQuestion].textID)
    }

    private fun initializeViews() {
        showTextViewQuestion()
        binding.buttonPrevious.isEnabled = false
    }

    private fun showTextViewQuestion() {
        binding.textViewQuestion.text = getQuestionText()
    }

    private fun goToActivity2() {
        val intent = Intent(this, Activity2::class.java)
        intent.putExtra(EXTRA_MESSAGE_QUESTION, questionList[indexOfQuestion])
        startForResult.launch(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(STATE_INDEX, indexOfQuestion)
            putParcelableArrayList(STATE_QUESTION_LIST, questionList)
        }
        super.onSaveInstanceState(outState)
    }

    companion object {
        const val STATE_INDEX = "index of question state"
        const val STATE_QUESTION_LIST = "list of questions state"
    }

    private fun loadQuestions() {
        questionList = arrayListOf(
            Question(R.string.question1, false),
            Question(R.string.question2, false),
            Question(R.string.question3, false),
            Question(R.string.question4, true),
            Question(R.string.question5, true),
            Question(R.string.question6, false),
            Question(R.string.question7, true),
            Question(R.string.question8, false),
            Question(R.string.question9, true),
            Question(R.string.question10, true)
        )
    }
}