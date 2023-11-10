package com.mazizs.bandmembers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mazizs.bandmembers.data.Member
import com.mazizs.bandmembers.data.members
import com.mazizs.bandmembers.ui.theme.BandMembersTheme

//Fungsi onCreate ini digunakan untuk mengedit tampilan aktivitas utama dengan menggunakan komponen UI yang telah didefinisikan dalam BandApp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BandMembersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BandApp()
                }
            }
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi BandApp yaitu umtuk menampilkan dan mengatur Scrollable List
@Composable
fun BandApp() {
    Scaffold( //Untuk mengatur tata letak yang menyediakan slot untuk berbagai komponen dan elemen screen
        topBar = {
            BandTopAppBar()//TopAppBar Yaitu merupakan salah satu komponen dari scaffold yang berfungsi untuk menampilkan dibagian atas layar atau seperti judul aplikasi
        }
    ) { it ->
        LazyColumn(contentPadding = it) {//Untuk menampilkan daftar MemberItem atau anggota band
            items(members) {
                MemberItem( //Untuk menampilkan informasi setiap elemen member dari daftar members
                    member = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi MemberItem yaitu umtuk menampilkan dan mengatur members atau angota band
@Composable
fun MemberItem(
    member: Member,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card( //Untuk membuat tampilan seperti bentuk kartu yang dimana pada setiap ujungnya melengkung atau tidak lancip
        modifier = modifier
    ) {
        Column( //untuk mengatur tampilan elemen komponen dalam tata letak berbentuk kolom, seperti menatur jarak, lebar, dan posisi
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row( //Untuk menampilkan ikon atau foto member band, informasi member band, dan tombol aksi
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                MemberIcon(member.imageResourceId) //Untuk menampilkan foto member
                MemberInformation(member.name, member.age) //Untk menampilkan nama dan usia member
                Spacer(Modifier.weight(1f)) //Untuk memberikan jarak untuk tata letak diantara member name dengan tombol aksi
                MemberItemButton( //Untuk menampilkan tombol yang dimana untuk memperluas atau menyembunyikan informasi tambahan dari member
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            if (expanded) { //Untuk menampilkan informasi member group jika bagian tersebut diperluas
                MemberGroup(
                    member.group, modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi MemberItemButton yaitu umtuk menampilkan icon button dan mengaturnya
@Composable
private fun MemberItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton( //untuk membuat tombol ikon yang merespons interaksi pengguna, yang digunkanan untuk memperluas atau menyembunyikan informasi tambahan
        onClick = onClick,
        modifier = modifier
    ) {
        Icon( //untuk menampilkan ikon grafis, menampilkan ikon panah atas dan panah bawah, tergantung pada apakah elemen sedang diperluas atau tidak
            //Dengan mambahkan dependensi library material-icons-extended di bagian build.gradle.kts, maka dapat menggunakan ikon Icons.Filled.ExpandLess dan Icons.Filled.ExpandMore dari Material Icons dari Google Fonts
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi BandTopAppBar yaitu untuk mengatur TopAppBar atau judul pada layar
@Composable
fun BandTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row( //Untuk mengatur elemen Iamge dan Text secara horizontal
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image( //Digunakan untuk menempatkan Image atau logo
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null
                )
                Text( //Digunakan untuk Text atau judul apllikasi secara
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi MemberIcon yaitu umtuk menampilkan dan mengatur icon atau foto member
@Composable
fun MemberIcon(
    @DrawableRes dogIcon: Int,
    modifier: Modifier = Modifier
) {
    Image( //Untuk menampilkan gambar icon atau foto member
        modifier = modifier //Untuk modifikasi dan untuk mengatur ukuran, padding, dan bentuk gambar
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(dogIcon),
        contentDescription = null
    )
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi MemberInformation yaitu umtuk menampilkan dan mengatur informasi member
@Composable
fun MemberInformation(
    @StringRes memberName: Int,
    memberAge: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {//Untuk mengatur tampilan kolom secara vertikal
        Text( //Untuk menampilkan teks berdasarkan string menggunakan gaya typografi dari tema material
            text = stringResource(memberName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text( //Untuk menampilkan teks berdasarkan string dan parameter yang berupa usia member
            text = stringResource(R.string.years_old, memberAge),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi MemberGroup yaitu untuk menampilkan dan mengatur about atau tentang member
@Composable
fun MemberGroup(
    @StringRes memberGroup: Int,
    modifier: Modifier = Modifier
) {
    Column( //Untuk mengatur tampilan kolom secara vertikal
        modifier = modifier
    ) {
        Text( //Untuk menampilkan teks berdasarkan string menggunakan gaya typografi dari tema material
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall
        )
        Text( //Untuk menampilkan teks dan menentukan gaya teks menggunakan gaya typografi dari tema material untuk informasi grup member
            text = stringResource(memberGroup),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

//Fungsi di bawah ini adalah komponen Composable yang digunakan untuk menampilkan preview atau pratinjau BandApp dalam mode tema terang
@Preview
@Composable
fun BandPreview() {
    BandMembersTheme(darkTheme = false) {
        BandApp()
    }
}

//Fungsi di bawah ini adalah komponen Composable yang digunakan untuk menampilkan preview atau pratinjau BandApp dalam mode tema gelap
@Preview
@Composable
fun BandDarkThemePreview() {
    BandMembersTheme(darkTheme = true) {
        BandApp()
    }
}