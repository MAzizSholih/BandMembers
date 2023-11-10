package com.mazizs.bandmembers.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mazizs.bandmembers.R

data class Member(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val group: Int
)

val members = listOf(
    Member(R.drawable.chester_bennington, R.string.member_name_1, 41, R.string.member_description_1),
    Member(R.drawable.mike_shinoda, R.string.member_name_2, 46, R.string.member_description_2),
    Member(R.drawable.brad_delson, R.string.member_name_3, 45, R.string.member_description_3),
    Member(R.drawable.rob_bourdon, R.string.member_name_4, 44, R.string.member_description_4),
    Member(R.drawable.dave_farrell, R.string.member_name_5, 46, R.string.member_description_5),
    Member(R.drawable.joe_hahn, R.string.member_name_6, 46, R.string.member_description_6),
    Member(R.drawable.oliver_sykes, R.string.member_name_7, 36, R.string.member_description_7),
    Member(R.drawable.jordan_fish, R.string.member_name_8, 37, R.string.member_description_8),
    Member(R.drawable.lee_malia, R.string.member_name_9, 36, R.string.member_description_9),
    Member(R.drawable.matthew_nicholls, R.string.member_name_10, 37, R.string.member_description_10),
    Member(R.drawable.matt_kean, R.string.member_name_11, 37, R.string.member_description_11)
)