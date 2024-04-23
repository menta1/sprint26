package com.decide.sprint26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.decide.sprint26.ui.theme.Sprint26Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sprint26Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ContactDetails(
                        Contact(
                            name = "Василий",
                            surname = "Иванович",
                            familyName = "Иванов",
                            imageRes = R.drawable.image,
                            isFavorite = true,
                            phone = "+7 495 495 95 95",
                            address = "г. Москва улица Уличная 4, квартира 42",
                            email = "akjsdak@kajsnd.com",
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (contact.imageRes != null) {
            Image(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(100.dp)
                    .width(150.dp),
                painter = painterResource(contact.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        } else {
            Box(modifier = Modifier.padding(top = 20.dp), contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = null,
                    tint = Color.LightGray
                )
                Text(
                    text = contact.name.take(1) + contact.familyName.take(1),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(modifier = Modifier.padding(top = 10.dp)) {
            Text(text = contact.name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            if (!contact.surname.isNullOrBlank()) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = contact.surname,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Row {
            Text(text = contact.familyName, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            if (contact.isFavorite) {
                Image(
                    modifier = Modifier.padding(start = 10.dp),
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = null
                )
            }

        }

        Spacer(modifier = Modifier.height(40.dp))
        InfoRow(first = stringResource(id = R.string.phone), second = contact.phone)
        InfoRow(first = stringResource(id = R.string.address), second = contact.address)
        InfoRow(first = stringResource(id = R.string.email), second = contact.email ?: "")
    }
}

@Composable
fun InfoRow(first: String, second: String) {
    if (second.isNotBlank()) {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(modifier = Modifier.weight(1f), text = "$first:", textAlign = TextAlign.End)
            Spacer(modifier = Modifier.width(10.dp))
            Text(modifier = Modifier.weight(1f), text = second, fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailsWithNullPreview() {
    Sprint26Theme {
        ContactDetails(
            Contact(
                name = "Василий",
                surname = "Иванович",
                familyName = "Иванов",
                imageRes = null,
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "г. Москва улица Уличная 4, квартира 42",
                email = "akjsdak@kajsnd.com",
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailsPreview() {
    Sprint26Theme {
        ContactDetails(
            Contact(
                name = "Василий",
                surname = null,
                familyName = "Иванов",
                imageRes = R.drawable.image,
                isFavorite = false,
                phone = "...",
                address = "г. Москва улица Уличная 4, квартира 42",
                email = null,
            )
        )
    }
}

