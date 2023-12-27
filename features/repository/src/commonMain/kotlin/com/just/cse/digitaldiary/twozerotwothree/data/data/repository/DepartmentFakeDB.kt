package com.just.cse.digitaldiary.twozerotwothree.data.data.repository

object DepartmentFakeDB {
    val departmentsOfAppliedScienceAndTechnology = listOf(
        Department(
            fullName = "Department Of Agro Product Processing Technology",
            shortName = "Agro Processing"
        ),
        Department(
            fullName = "Department Of Climate and Disaster Management",
            shortName = "Climate & Disaster Management"
        ),
        Department(
            fullName = "Department Of Environmental Science and Technology",
            shortName = "Environmental Science & Tech"
        ),
        Department(
            fullName = "Department Of Nutrition and Food Technology",
            shortName = "Nutrition & Food Tech"
        )
    )
    val departmentsOfBiologicalScienceAndTechnology = listOf(
        Department(
            fullName = "Department Of Fisheries and Marine Bioscience",
            shortName = "Fisheries & Marine Bioscience"
        ),
        Department(
            fullName = "Department Of Genetic Engineering and Biotechnology",
            shortName = "Genetic Engineering & Biotechnology"
        ),
        Department(
            fullName = "Department Of Microbiology",
            shortName = "Microbiology"
        ),
        Department(
            fullName = "Department Of Pharmacy",
            shortName = "Pharmacy"
        )
    )
    val departmentsOfNursingAndHealthScience = listOf(
        Department(
            fullName = "Department Of Nursing and Health Science",
            shortName = "Nursing & Health Science"
        ),
        Department(
            fullName = "Department Of Physical Education and Sports Science",
            shortName = "Physical Education & Sports Science"
        ),
        Department(
            fullName = "Department Of Physiotherapy and Rehabilitation",
            shortName = "Physiotherapy & Rehabilitation"
        )
    )
    val departmentOfEnglish = listOf(
        Department(
            fullName = "Department Of English",
            shortName = "English"
        )
    )
    val departmentsOfScience = listOf(
        Department(
            fullName = "Department Of Chemistry",
            shortName = "Chemistry"
        ),
        Department(
            fullName = "Department Of Mathematics",
            shortName = "Mathematics"
        ),
        Department(
            fullName = "Department Of Physics",
            shortName = "Physics"
        )
    )
    val departmentsOfBusinessAndFinance = listOf(
        Department(
            fullName = "Department Of Accounting and Information Systems",
            shortName = "Accounting & Information Systems"
        ),
        Department(
            fullName = "Department Of Finance and Banking",
            shortName = "Finance & Banking"
        ),
        Department(
            fullName = "Department Of Management",
            shortName = "Management"
        ),
        Department(
            fullName = "Department Of Marketing",
            shortName = "Marketing"
        )
    )
    val departmentsOfEngineeringAndTechnology = listOf(
        Department(
            fullName = "Department Of Biomedical Engineering",
            shortName = "Biomedical Engineering",

        ),
        Department(
            fullName = "Department Of Chemical Engineering",
            shortName = "Chemical Engineering"
        ),
        Department(
            fullName = "Department Of Computer Science and Engineering",
            shortName = "Computer Science & Engineering"
        ),
        Department(
            fullName = "Department Of Electrical and Electronic Engineering",
            shortName = "Electrical & Electronic Engineering"
        ),
        Department(
            fullName = "Department Of Industrial and Production Engineering",
            shortName = "Industrial & Production Engineering"
        ),
        Department(
            fullName = "Department Of Petroleum and Mining Engineering",
            shortName = "Petroleum & Mining Engineering"
        ),
        Department(
            fullName = "Department Of Textile Engineering",
            shortName = "Textile Engineering"
        )
    )
    fun getDepartmentById(departmentId: String): Department? {
        val allDepartments = listOf(
            departmentsOfAppliedScienceAndTechnology,
            departmentsOfBiologicalScienceAndTechnology,
            departmentsOfNursingAndHealthScience,
            departmentOfEnglish,
            departmentsOfScience,
            departmentsOfBusinessAndFinance,
            departmentsOfEngineeringAndTechnology
        ).flatten()

        return allDepartments.find { it.id == departmentId }
    }

}