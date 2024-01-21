package com.just.cse.digitaldiary.twozerotwothree.data.repository.vc_repsoitory

object VCInfoRepository {
    fun getVCInfo():ViceChancellorInfo{
       return ViceChancellorInfo(
            name = "Mr. X",
            title = "Vice Chancellor",
            university = "Jashore University of Science and Technology"
        )
    }
    fun getMessageFromVC(): String{
      return  """
 Jashore University of Science and Technology (JUST) has started a steady journey of reaching a new height of excellence in research and to achieve a unique milestone in promoting new ideas and innovation, and in serving the nation and the global community by creating enlightened and skilled professionals who can meet the challenges of the 21st century fostering the motto of ‘being the employer, not the employee’. In keeping with this purpose, JUST has already been declared a research university that aims at generating and advancing knowledge by cutting-edge research in its state-of-the-art laboratories and in the congenial academic ambience. Apart from these, JUST has international and local collaboration with a wide range of reputed academia and industry...
    """.trimIndent()
    }
}