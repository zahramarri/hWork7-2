package com.learning.hwork7_2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(val textID: Int, val isAnswered: Boolean = false, val isCheated: Boolean = false): Parcelable