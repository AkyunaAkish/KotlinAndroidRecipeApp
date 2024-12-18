package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// This needs to be parcelable
// due to it's use in passing between
// nav composables (turns objects into a string then converts it back to it's original state when retrieved)
@Parcelize
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
) : Parcelable

data class CategoriesResponse(
    val categories: List<Category>
)
