package com.just.cse.digitaldiary.twozerotwothree.data.repository.repository

object DepartmentFakeDB {
    val departmentsOfAppliedScienceAndTechnology = listOf(
        Department(
            name = "Department Of Agro Product Processing Technology",
            shortName = "APPT"
        ),
        Department(
            name = "Department Of Climate and Disaster Management",
            shortName = "CDM"
        ),
        Department(
            name = "Department Of Environmental Science and Technology",
            shortName = "EST"
        ),
        Department(
            name = "Department Of Nutrition and Food Technology",
            shortName = "NFT"
        )
    )
    val departmentsOfBiologicalScienceAndTechnology = listOf(
        Department(
            name = "Department Of Fisheries and Marine Bioscience",
            shortName = "FMB"
        ),
        Department(
            name = "Department Of Genetic Engineering and Biotechnology",
            shortName = "Genetic Engineering & Biotechnology"
        ),
        Department(
            name = "Department Of Microbiology",
            shortName = "M"
        ),
        Department(
            name = "Department Of Pharmacy",
            shortName = "P"
        )
    )
    val departmentsOfNursingAndHealthScience = listOf(
        Department(
            name = "Department Of Nursing and Health Science",
            shortName = "NHS"
        ),
        Department(
            name = "Department Of Physical Education and Sports Science",
            shortName = "Physical Education & Sports Science"
        ),
        Department(
            name = "Department Of Physiotherapy and Rehabilitation",
            shortName = "Physiotherapy & Rehabilitation"
        )
    )
    val departmentOfEnglish = listOf(
        Department(
            name = "Department Of English",
            shortName = "Eng"
        )
    )
    val departmentsOfScience = listOf(
        Department(
            name = "Department Of Chemistry",
            shortName = "Che"
        ),
        Department(
            name = "Department Of Mathematics",
            shortName = "Math"
        ),
        Department(
            name = "Department Of Physics",
            shortName = "Phy"
        )
    )
    val departmentsOfBusinessAndFinance = listOf(
        Department(
            name = "Department Of Accounting and Information Systems",
            shortName = "Accounting & Information Systems"
        ),
        Department(
            name = "Department Of Finance and Banking",
            shortName = "Finance & Banking"
        ),
        Department(
            name = "Department Of Management",
            shortName = "Management"
        ),
        Department(
            name = "Department Of Marketing",
            shortName = "Marketing"
        )
    )
    val departmentsOfEngineeringAndTechnology = listOf(
        Department(
            name = "Department Of Biomedical Engineering",
            shortName = "Biomedical Engineering",

        ),
        Department(
            name = "Department Of Chemical Engineering",
            shortName = "Chemical Engineering"
        ),
        Department(
            name = "Department Of Computer Science and Engineering",
            shortName = "Computer Science & Engineering"
        ),
        Department(
            name = "Department Of Electrical and Electronic Engineering",
            shortName = "Electrical & Electronic Engineering"
        ),
        Department(
            name = "Department Of Industrial and Production Engineering",
            shortName = "Industrial & Production Engineering"
        ),
        Department(
            name = "Department Of Petroleum and Mining Engineering",
            shortName = "Petroleum & Mining Engineering"
        ),
        Department(
            name = "Department Of Textile Engineering",
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