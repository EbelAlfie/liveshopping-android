package io.getstream.live.shopping.ui.component

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable

@Composable
fun StreamTab() {
  TabRow(selectedTabIndex = 1) {
    Tab(selected = true, onClick = { /*TODO*/ })
    Tab(selected = true, onClick = { /*TODO*/ })
  }
}