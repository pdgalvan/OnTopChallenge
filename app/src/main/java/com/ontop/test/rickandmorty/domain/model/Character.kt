package com.ontop.test.rickandmorty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Character(
    val name: String,
    val id: Int,
    val episode: List<String>,
    val gender: String,
    val image: String,
    val location: Location,
    val origin: Location,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) : Parcelable
