package com.renesistech.quotes.helper

object Constant {

    @JvmField
    var headerName = "Content-Type"
    @JvmField
    var headerValue = "application/json"

    @JvmField
    var PACKAGE_NAME: String = "com.renesistech.quotes"

    const val MAX_RETRIES: Int = 5

    const val RETRY_DELAY: Int = 5000

    const val CONN_TIMEOUT: Long = 60

    const val READ_TIMEOUT: Long = 40

    var mColors = arrayOf(
        "FCE4EC", "F8BBD0", "F48FB1", "F06292", "EC407A", "E91E63", "D81B60",  //pinks
        "C2185B", "AD1457", "880E4F", "FF80AB", "FF4081", "F50057", "C51162",
        "E1F5FE", "B3E5FC", "81D4fA", "4fC3F7", "29B6FC", "03A9F4", "039BE5",  //light blue
        "0288D1", "0277BD", "01579B", "80D8FF", "40C4FF", "00B0FF", "0091EA",
        "E0F7FA", "B2EBF2", "80DEEA", "4DD0E1", "26C6DA", "00BCD4", "00ACC1",  //cyan
        "0097A7", "00838F", "006064", "84FFFF", "18FFFF", "00E5FF", "00B8D4",
        "E0F2F1", "B2DFDB", "80CBC4", "4DB6AC", "26A69A", "009688", "00897B",  //teal
        "00796B", "00695C", "004D40", "A7FFEB", "64FFDA", "1DE9B6", "00BFA5",
        "E8F5E9", "C8E6C9", "A5D6A7", "81C784", "66BB6A", "4CAF50", "43A047",  //green
        "388E3C", "2E7D32", "1B5E20", "B9F6CA", "69F0AE", "00E676", "00C853",
        "F1F8E9", "DCEDC8", "C5E1A5", "AED581", "9CCC65", "8BC34A", "7CB342",  //light green
        "689F38", "558B2F", "33691E", "CCFF90", "B2FF59", "76FF03", "64DD17",
        "F9FBE7", "F0F4C3", "E6EE9C", "DCE775", "D4E157", "CDDC39", "C0CA33",  //lime
        "A4B42B", "9E9D24", "827717", "F4FF81", "EEFF41", "C6FF00", "AEEA00",
        "FFFDE7", "FFF9C4", "FFF590", "FFF176", "FFEE58", "FFEB3B", "FDD835",  //yellow
        "FBC02D", "F9A825", "F57F17", "FFFF82", "FFFF00", "FFEA00", "FFD600",
        "FFF8E1", "FFECB3", "FFE082", "FFD54F", "FFCA28", "FFC107", "FFB300",  //amber
        "FFA000", "FF8F00", "FF6F00", "FFE57F", "FFD740", "FFC400", "FFAB00",
        "FFF3E0", "FFE0B2", "FFCC80", "FFB74D", "FFA726", "FF9800", "FB8C00",  //orange
        "F57C00", "EF6C00", "E65100", "FFD180", "FFAB40", "FF9100", "FF6D00",
        "FBE9A7", "FFCCBC", "FFAB91", "FF8A65", "FF7043", "FF5722", "F4511E",  //deep orange
        "E64A19", "D84315", "BF360C", "FF9E80", "FF6E40", "FF3D00", "DD2600",
        "ECEFF1", "CFD8DC", "B0BBC5", "90A4AE", "78909C", "607D8B", "546E7A",  //blue grey
        "455A64", "37474F", "263238" //150
    )
}
