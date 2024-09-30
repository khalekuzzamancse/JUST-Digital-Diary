package data.data_source

val holidayJson = """
    {
      "academicCalendar": {
        "year": 2024,
        "januaryHolidays": [
          {"day": 10, "holidayType": "AllOff", "reason": "Winter Vacation"},
          {"day": 13, "holidayType": "AllOff", "reason": "Winter Vacation"},
          {"day": 14, "holidayType": "AllOff", "reason": "Winter Vacation"}
        ],
        "februaryHolidays": [
          {"day": 9, "holidayType": "AllOff", "reason": "International Mother Language Day"},
          {"day": 21, "holidayType": "AllOff", "reason": "Shaheed Day & International Mother Language Day"}
        ],
        "marchHolidays": [
          {"day": 17, "holidayType": "AllOff", "reason": "Bangabandhu Sheikh Mujibur Rahman's Birthday"},
          {"day": 26, "holidayType": "AllOff", "reason": "Independence Day"}
        ],
        "aprilHolidays": [
          {"day": 14, "holidayType": "AllOff", "reason": "Bengali New Year"},
          {"day": 21, "holidayType": "AllOff", "reason": "Shab-e-Barat"}
        ],
        "mayHolidays": [
          {"day": 1, "holidayType": "AllOff", "reason": "Labour Day"}
        ],
        "juneHolidays": [
          {"day": 3, "holidayType": "AllOff", "reason": "Eid-ul-Fitr"},
          {"day": 2, "holidayType": "OnlyClassOff", "reason": "Eid-ul-Fitr"}
          
        ],
        "julyHolidays": [
          {"day": 7, "holidayType": "AllOff", "reason": "Eid-ul-Azha"}
        ],
        "augustHolidays": [
          {"day": 15, "holidayType": "AllOff", "reason": "National Mourning Day"}
        ],
        "septemberHolidays": [
          {"day": 6, "holidayType": "AllOff", "reason": "Janmashtami"},
           {"day": 3, "holidayType": "AllOff", "reason": "Eid-ul-Fitr"},
          {"day": 2, "holidayType": "OnlyClassOff", "reason": "Eid-ul-Fitr"},
            {"day": 23, "holidayType": "SpecialDay", "reason": "University Day"}
          
        ],
        "octoberHolidays": [
          {"day": 10, "holidayType": "AllOff", "reason": "Durga Puja"}
        ],
        "novemberHolidays": [
          {"day": 11, "holidayType": "AllOff", "reason": "Eid-e-Milad-un-Nabi"}
        ],
        "decemberHolidays": [
          {"day": 16, "holidayType": "AllOff", "reason": "Victory Day"},
          {"day": 25, "holidayType": "AllOff", "reason": "Christmas Day"}
        ]
      }
    }

""".trimIndent()
