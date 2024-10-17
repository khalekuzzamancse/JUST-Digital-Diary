@file:Suppress("unused","composableNaming")
package common.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Vertical spacers
@Composable
fun VerticalSpace_4() = Spacer(modifier = Modifier.height(4.dp))

@Composable
fun VerticalSpace_8() = Spacer(modifier = Modifier.height(8.dp))

@Composable
fun VerticalSpace_16() = Spacer(modifier = Modifier.height(16.dp))
@Composable
fun VerticalSpace_32() = Spacer(modifier = Modifier.height(32.dp))

// Horizontal spacers
@Composable
fun HorizontalSpace_4() = Spacer(modifier = Modifier.width(4.dp))

@Composable
fun HorizontalSpace_8() = Spacer(modifier = Modifier.width(8.dp))

@Composable
fun HorizontalSpace_16() = Spacer(modifier = Modifier.width(16.dp))
