package com.just.cse.digitaldiary.twozerotwothree.data.repository.faculty_info

object FacultyInfoRepository {
    fun getFacultyMessage(facultyId: String):FacultyMessage{
        return FacultyMessage(
            imageUrl = "https://static.just.edu.bd/images/public/others/1675089849536_900.jpeg",
            message = "Welcome to the Faculty of Engineering and Technology (FET). " +
                    "This is a creative, enterprising and dynamic faculty where the faculty members are " +
                    "dedicated to creating knowledge and opportunities to ensure that all the students can excel within their chosen discipline." +
                    " In FET, we offer courses and carry out research, enterprise and knowledge transfer across a diverse field, " +
                    "such as Computer Science and Engineering (CSE), Chemical Engineering (ChE), Industrial and Production Engineering (IPE), Petroleum and Mining Engineering (PME), " +
                    "Electrical and Electronic Engineering (EEE)," +
                    " Biomedical Engineering (BME) and Textile Engineering (TE)."
        )
    }
}