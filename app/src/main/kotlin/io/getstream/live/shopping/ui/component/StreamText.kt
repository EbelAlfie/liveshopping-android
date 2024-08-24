package io.getstream.live.shopping.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UrlText(
  url: String
) {
  SelectionContainer {
    Text(
      modifier = Modifier
        .border(1.dp, Color.DarkGray)
        .background(Color.LightGray)
        .padding(5.dp),
      text = url,
      color = Color.Black
    )
  }
}