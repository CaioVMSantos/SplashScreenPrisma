package com.example.splashscreenprismavi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.splashscreenprismavi.ui.theme.SplashScreenPrismaViTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SplashScreenPrismaViTheme {
                var showSplash by remember { mutableStateOf(true) }

                if (showSplash) {
                    SplashScreen(onSplashFinished = { showSplash = false })
                } else {
                    // Conteúdo principal após o Splash Screen
                }
            }
        }
    }

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val fontName = GoogleFont("Revalia")

    val myFontFamily = FontFamily(
        Font(googleFont = fontName, fontProvider = provider)
    )

    @Composable
    fun SplashScreen(onSplashFinished: () -> Unit) {
        // Define o gradiente
        val gradient = Brush.linearGradient(
            colors = listOf(Color(0xFF00CBD0), Color(0xFFAEF6CE)),
        )

        // Configura a exibição e animação do Splash Screen
        LaunchedEffect(Unit) {
            delay(6000)  // Duração do splash screen em milissegundos
            onSplashFinished()
        }

        // Conteúdo do Splash Screen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient),
            contentAlignment = Alignment.Center
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                // Quadrado semitransparente atrás da imagem
                Box(
                    modifier = Modifier
                        .size(150.dp)  // Tamanho do quadrado ligeiramente maior que a imagem
                        .background(Color.DarkGray.copy(alpha = 0.7f), shape = RoundedCornerShape(25.dp))
                )

                // Imagem do logo redimensionada
                Image(
                    painter = painterResource(id = R.drawable.prismalogo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(180.dp)  // Tamanho da imagem
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 90.dp)
            ){
                Text(
                    fontFamily = myFontFamily,
                    text = "Powered by",
                    fontSize = 16.sp,
                    color = Color.Transparent, // Tornar a cor do texto transparente
                    textAlign = TextAlign.Center,
                    style = androidx.compose.ui.text.TextStyle(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFFF5E8A), Color(0xFF007CC0)) // Gradiente do texto
                        )
                    )
                )

            }
        }
    }
}

