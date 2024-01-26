package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.dto.AboutUsDTO
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.dto.VCInfoDTO

internal class RemoteDataSource {
    suspend fun getVCInfo() = VCInfoDTO(
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
}