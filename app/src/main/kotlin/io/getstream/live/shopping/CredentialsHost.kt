package io.getstream.live.shopping

object CredentialsHost {

  const val TOKEN =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiU2t5UGhlbm9tZW5vbiJ9.htwosMi2COR-lxj-fAlEgOE7cfjmnqDWzNwZ_52kmzw"
  const val ID = "SkyPhenomenon"
  const val NAME = "SkyPhenomenon"
  const val AVATAR = "https://i.ytimg.com/vi/8cj4btkOttk/mqdefault.jpg"
  const val BANNER = "https://www.google.com/logos/doodles/2024/celebrating-rendang-6753651837110275-2x.png"
  const val DESCRIPTION = "This Doodle celebrates rendang, a rich Indonesian stew made with coconut milk."
  const val ROLE = "admin"
  const val LIVE_STREAM_ID = "LIVE_STREAM_${ID}_1"
}

val products: List<ProductModel> = listOf(
  ProductModel(
    id = "id:rendang",
    image = "https://www.astronauts.id/blog/wp-content/uploads/2023/03/Resep-Rendang-Daging-Sapi-Untuk-Lebaran-Gurih-dan-Nikmat-1024x683.jpg",
    name = "Rendang",
    desc= "Ini adalah randang kata Gordon ramsey. Sangat Enak"
  ),
  ProductModel(
    id = "id:pizza",
    image = "https://t1.gstatic.com/licensed-image?q=tbn:ANd9GcRoeUqD7lgiXavof_C8DW2QeI-BHIzGPLKRPCWinurLGGMBT7GSml0le6bQro8yWjAa",
    name = "Pizza",
    desc= "Ini adalah pizza, yang sangat enak"
  ),
  ProductModel(
    id = "id:lotek",
    image = "https://akcdn.detik.net.id/community/media/visual/2023/09/19/resep-lotek-bandung.jpeg?w=700&q=90",
    name = "Lotek",
    desc= "Ini lotek yang telah difermentasi 90 hari (Bumbu mengandung santan)"
  )
)

data class ProductModel(
  val id: String,
  val image: String,
  val name: String,
  val desc: String
)