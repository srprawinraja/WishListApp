package com.example.mywishlistapp

data class Wish(
    val id: Long=0L,
    val title: String="",
    val description:String=""
)

object DummyList{
    val wishList= listOf(
        Wish(title = "Google Watch 2",
            description = "An Android Watch")
    )
}
