package miscellaneous.data.data_sources.remote

import miscellaneous.data.dto.AboutUsDTO
import miscellaneous.data.dto.EventGalleryDTO
import miscellaneous.data.dto.VCInfoDTO

internal class RemoteDataSource {
    suspend fun getVCInfo(): VCInfoDTO = VCInfoDTO(
        name = "Professor Dr. Md. Anwar Hossain",
        details = "Vice Chancellor\n" +
                "Jashore University of Science and Technology\n" +
                "Jashore, Bangladesh",
        message = "Jashore University of Science and Technology (JUST) has started a steady journey of reaching a new height of excellence in research and to achieve a unique milestone in promoting new ideas and innovation, and in serving the nation and the global community by creating enlightened and skilled professionals who can meet the challenges of the 21st century fostering the motto of ‘being the employer, not the employee’. In keeping with this purpose, JUST has already been declared a research university that aims at generating and advancing knowledge by cutting-edge research in its state-of-the-art laboratories and in the congenial academic ambience. Apart from these, JUST has international and local collaboration with a wide range of reputed academia and industry.\n" +
                "\n" +
                "Our focus is also on promoting intellectual leadership through innovation and outcome-based education, and fostering social commitment through community affiliation. It gives me immense pleasure that the concerted efforts of the faculty members and students both from home and abroad have added feathers in our cap.\n" +
                "\n" +
                "In order to achieve the rest of the noble goals and turn our country into the golden Bengal envisioned by the Father of the Nation Bangabandhu Sheikh Mujibur Rahman, we all have to work together relentlessly and wholeheartedly.",
        imageUrl = "https://just.edu.bd/images/vc2.jpg"
    )

    suspend fun getAboutUsInfo() = AboutUsDTO(
        appName = "JUST DIGITAL DIARY",
        developedDepartmentName = "Computer Science and Engineering",
        universityName ="Jashore University of Sciences and Technology(JUST)",
        otherInfo = ""

    )
    suspend fun getEvents(): List<EventGalleryDTO> {
        return listOf(
            EventGalleryDTO(
                name = "Spring Music Festival",
                details = "A vibrant outdoor music festival celebrating spring. Features a variety of genres and artists.",
                imageLink = "https://static.just.edu.bd/images/public/gallery/1670125964939_1200.jpeg"
            ),
            EventGalleryDTO(
                name = "Tech Innovators Conference",
                details = "Annual conference showcasing the latest in technology and innovation. Networking and keynote speeches.",
                imageLink = "https://static.just.edu.bd/images/public/gallery/1670125585615_1200.jpeg"
            ),
            EventGalleryDTO(
                name = "Marathon City Run",
                details = "A city-wide marathon with participants from all over the world. Includes a half-marathon and a 5K run.",
                imageLink = "https://static.just.edu.bd/images/public/gallery/1611033327761_1200.jpeg"
            ),
            EventGalleryDTO(
                name = "Food and Wine Expo",
                details = "Explore culinary delights and fine wines at this expo. Features local and international cuisine.",
                imageLink = "https://static.just.edu.bd/images/public/gallery/1670316318194_1200.jpeg"
            ),
            EventGalleryDTO(
                name = "Artists' Showcase",
                details = "An exhibition of contemporary art by emerging artists. Paintings, sculptures, and installations.",
                imageLink = "https://static.just.edu.bd/images/public/gallery/1692174265512_1200.jpeg"
            )
        )
    }
}