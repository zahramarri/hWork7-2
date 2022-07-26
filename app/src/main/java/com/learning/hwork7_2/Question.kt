package com.learning.hwork7_2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(val textID: Int, val answer: Boolean, var isAnswered: Boolean = false, var isCheated: Boolean = false): Parcelable