package com.example.appmahasiswa.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiInsert : AlamatNavigasi {
    override val route: String = "insert"
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiDetail : AlamatNavigasi {
    override val route = "detail"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}

object DestinasiUpdate : AlamatNavigasi {
    override val route = "update"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}