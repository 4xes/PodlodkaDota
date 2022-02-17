package com.blackfox.podlodka.data

import com.blackfox.podlodka.R

data class AppData(
    val name: String,
    val image: Int,
    val icon: Int,
    val tags: List<String>,
    val description: String,
    val media: List<Media>,
    val reviews: List<Review>
) {

    companion object {
        val testData = AppData(
            "Dota",
            image = R.drawable.dota_back_image,
            icon = R.drawable.dota_icon,
            tags = listOf("Moba", "Multiplayer", "Strategy"),
            description = "Dota 2 is a multiplayer online battle arena (MOBA) game which has two teams of five players compete to collectively destroy a large structure defended by the opposing team known as the \"Ancient\", whilst defending their own.",
            media = listOf(
                Media(
                    Type.Video, R.drawable.dota_media_1
                ),
                Media(
                    Type.Photo, R.drawable.dota_media_2
                )
            ),
            reviews = listOf(
                Review(
                    author = ReviewAuthor(
                        avatar = R.drawable.dota_avatar_id1,
                        name = "Auguste Conte"
                    ),
                    date = "February 14, 2019", //todo timestamp
                    text = "“Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.”"
                ),
                Review(
                    author = ReviewAuthor(
                        avatar = R.drawable.dota_avatar_id2,
                        name = "Jang Marcelino"
                    ),
                    date = "February 14, 2019", //todo timestamp
                    text = "“Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.”"
                )
            )
        )
    }

    data class Media(
        val type: Type, val thumb: Int
    )

    enum class Type {
        Photo, Video
    }

    data class Rating(
        val rating: Float,
        val countReviews: String
    )

    data class ReviewAuthor(
        val avatar: Int,
        val name: String
    )

    data class Review(
        val author: ReviewAuthor,
        val date: String,
        val text: String
    )
}
