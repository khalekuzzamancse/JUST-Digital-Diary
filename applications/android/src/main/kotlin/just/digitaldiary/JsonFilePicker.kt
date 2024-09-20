package just.digitaldiary

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.core.net.toFile
import java.io.InputStream

@Composable
fun JsonFilePicker(
    onFileSelected: (String) -> Unit
) {
    val context = LocalContext.current
    var selectedFileName by remember { mutableStateOf<String?>(null) }

    // Launcher for file picker intent
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                // Process the file and read JSON content
                val jsonContent = readTextFromUri(context, uri)
                onFileSelected(jsonContent)
                selectedFileName = uri.lastPathSegment
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            // Trigger the file picker
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "application/json" // Only allow JSON file types
            }
            launcher.launch(intent)
        }) {
            Text(text = "Select JSON File")
        }

        selectedFileName?.let {
            Text(text = "Selected file: $it", textAlign = TextAlign.Center)
        }
    }
}

private fun readTextFromUri(context: android.content.Context, uri: Uri): String {
    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
    return inputStream?.bufferedReader().use { it?.readText() } ?: ""
}
