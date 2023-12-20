package com.just.cse.digitaldiary.twozerotwothree.data.repository.event_gallery_repository.data

import com.just.cse.digitaldiary.twozerotwothree.data.repository.event_gallery_repository.model.JustEvent

object EventGalleryRepository {

    val events = listOf(
        JustEvent(
            name = "Spring Music Festival",
            details = "A vibrant outdoor music festival celebrating spring. Features a variety of genres and artists.",
            imageLink = "https://static.just.edu.bd/images/public/gallery/1670125964939_1200.jpeg"
        ),
        JustEvent(
            name = "Tech Innovators Conference",
            details = "Annual conference showcasing the latest in technology and innovation. Networking and keynote speeches.",
            imageLink = "https://static.just.edu.bd/images/public/gallery/1670125585615_1200.jpeg"
        ),
        JustEvent(
            name = "Marathon City Run",
            details = "A city-wide marathon with participants from all over the world. Includes a half-marathon and a 5K run.",
            imageLink = "https://static.just.edu.bd/images/public/gallery/1611033327761_1200.jpeg"
        ),
        JustEvent(
            name = "Food and Wine Expo",
            details = "Explore culinary delights and fine wines at this expo. Features local and international cuisine.",
            imageLink = "https://static.just.edu.bd/images/public/gallery/1670316318194_1200.jpeg"
        ),
        JustEvent(
            name = "Artists' Showcase",
            details = "An exhibition of contemporary art by emerging artists. Paintings, sculptures, and installations.",
            imageLink = "https://static.just.edu.bd/images/public/gallery/1692174265512_1200.jpeg"
        )
    )

    // Example usage
    fun main() {
        events.forEach { event ->
            println("Event: ${event.name}, Details: ${event.details}, Image Link: ${event.imageLink}")
        }
    }

}