package id.nns.naruto_movie_app_v1

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.nns.naruto_movie_app_v1.R.drawable.naruto_movie_1
import id.nns.naruto_movie_app_v1.R.drawable.naruto_movie_2
import id.nns.naruto_movie_app_v1.R.drawable.naruto_movie_3
import id.nns.naruto_movie_app_v1.R.drawable.naruto_movie_4
import id.nns.naruto_movie_app_v1.R.drawable.naruto_movie_5
import id.nns.naruto_movie_app_v1.R.drawable.naruto_movie_6
import id.nns.naruto_movie_app_v1.R.drawable.naruto_movie_7
import id.nns.naruto_movie_app_v1.R.drawable.naruto_movie_8
import id.nns.naruto_movie_app_v1.R.drawable.naruto_movie_9
import id.nns.naruto_movie_app_v1.R.drawable.naruto_movie_10
import id.nns.naruto_movie_app_v1.R.drawable.naruto_movie_11
import id.nns.naruto_movie_app_v1.data.NarutoMovieData
import id.nns.naruto_movie_app_v1.R.drawable.placeholder
import id.nns.naruto_movie_app_v1.ui.detail.DetailActivity
import id.nns.naruto_movie_app_v1.utils.CustomDateUtils.formatDate
import id.nns.naruto_movie_app_v1.utils.CustomColorUtils.getLightMutedColorFromImage

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFEFEFEF))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black)
                            .statusBarsPadding()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Naruto Movie App",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )
                    }
                    MovieList(
                        movies = getNarutoMovies(),
                        onMovieClick = { movie ->
                            val intent = Intent(this@MainActivity, DetailActivity::class.java)
                            intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MovieList(movies: List<NarutoMovieData>, onMovieClick: (NarutoMovieData) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        items(movies) { movie ->
            val context = LocalContext.current
            val defaultColor = MaterialTheme.colorScheme.surfaceVariant

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = getLightMutedColorFromImage(
                        context,
                        movie.img ?: placeholder,
                        defaultColor
                    ),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onMovieClick(movie) },
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                ) {
                    Image(
                        painter = painterResource(movie.img ?: placeholder),
                        contentDescription = movie.title,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(
                                RoundedCornerShape(8.dp)
                            )
                    )
                    Spacer(
                        modifier = Modifier
                            .width(12.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = movie.title.toString(),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = formatDate(movie.date.toString()),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${movie.duration} • Score: ${movie.score}",
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }
        }
    }
}

private fun getNarutoMovies() : List<NarutoMovieData> {
    val narutoMovies = mutableListOf<NarutoMovieData>()

    narutoMovies.addAll(
        listOf(
            NarutoMovieData(
                id = 1,
                img = naruto_movie_1,
                title = "Naruto the Movie: Ninja Clash in the Land of Snow",
                date = "08/21/2004",
                duration = "1h 22m",
                score = 73,
                overview = "Naruto is thrilled when he is sent on a mission to protect his favorite actress, Yukie Fujikaze, on the set of her new movie, The Adventures of Princess Gale. But when the crew ventures out to film in the icy, foreboding Land of Snow, Yukie mysteriously flees! Naruto and his squad set off to find her... unaware that three Snow Ninja lie in wait, with a sinister purpose that will force Yukie to face her hidden past!",
            ),
            NarutoMovieData(
                id = 2,
                img = naruto_movie_2,
                title = "Naruto the Movie: Legend of the Stone of Gelel",
                date = "08/06/2005",
                duration = "1h 37m",
                score = 68,
                overview = "Naruto, Shikamaru, and Sakura are executing their mission of delivering a lost pet to a certain village. However, right in the midst of things, troops led by the mysterious knight, Temujin, attack them. In the violent battle, the three become separated. Temujin challenges Naruto to a fight and at the end of the fierce battle, both fall together from a high cliff.",
            ),
            NarutoMovieData(
                id = 3,
                img = naruto_movie_3,
                title = "Naruto the Movie: Guardians of the Crescent Moon Kingdom",
                date = "08/05/2006",
                duration = "1h 35m",
                score = 71,
                overview = "Naruto Uzumaki, Kakashi Hatake, Sakura Haruno, and Rock Lee are assigned to protect the prince of the Land of the Moon, Michiru, during his world trip; other escorts had been hired, but quit due to being treated poorly. The Land of the Moon is a very wealthy nation, so Michiru tends to buy whatever he wants, and has a very materialistic worldview. His Hikaru, also acts in much the same manner.",
            ),
            NarutoMovieData(
                id = 4,
                img = naruto_movie_4,
                title = "Naruto Shippuden the Movie",
                date = "08/04/2007",
                duration = "1h 34m",
                score = 74,
                overview = "Demons that once almost destroyed the world, are revived by someone. To prevent the world from being destroyed, the demon has to be sealed and the only one who can do it is the shrine maiden Shion from the country of demons, who has two powers; one is sealing demons and the other is predicting the deaths of humans. This time Naruto's mission is to guard Shion, but she predicts Naruto's death. The only way to escape it, is to get away from Shion, which would leave her unguarded, then the demon, whose only goal is to kill Shion will do so, thus meaning the end of the world. Naruto decides to challenge this \"prediction of death.\"",
            ),
            NarutoMovieData(
                id = 5,
                img = naruto_movie_5,
                title = "Naruto Shippuden the Movie: Bonds",
                date = "08/02/2008",
                duration = "1h 30m",
                score = 73,
                overview = "A mysterious group of ninjas makes a surprise attack on the Konohagakure, which takes great damage. The nightmare of another Shinobi World War could become a reality. Sasuke, who was still a missing nin from Konoha trying to kill his brother, Itachi, appears for the second time in front of Naruto at an unknown location to prevent it from happening.",
            ),
            NarutoMovieData(
                id = 6,
                img = naruto_movie_6,
                title = "Naruto Shippuden the Movie: The Will of Fire",
                date = "08/01/2009",
                duration = "1h 35m",
                score = 74,
                overview = "Ninjas with bloodline limits begin disappearing in all the countries and blame points toward the fire nation. By Tsunade's order, Kakashi is sacrificed to prevent an all out war. After inheriting charms left by Kakashi, Naruto fights through friends and foes to prevent his death while changing the minds of those who've inherited the will of fire.",
            ),
            NarutoMovieData(
                id = 7,
                img = naruto_movie_7,
                title = "Naruto Shippuden the Movie: The Lost Tower",
                date = "07/31/2010",
                duration = "1h 25m",
                score = 72,
                overview = "Assigned on a mission to capture Mukade, a missing-nin, Naruto Uzumaki sets out for the once glorious historic ruins of \"Ouran\", where he pursues and corners the rouge ninja. Mukade's goal is revealed to be a dormant leyline within the ruins; he unleashes the power of the leyline, causing a light to envelop Naruto, sending him into the past, 20 years before the series began. When Naruto awakens, he comes into contact with the Fourth Hokage, Minato Namikaze.",
            ),
            NarutoMovieData(
                id = 8,
                img = naruto_movie_8,
                title = "Naruto Shippuden the Movie: Blood Prison",
                date = "07/30/2011",
                duration = "1h 48m",
                score = 73,
                overview = "After his capture for attempted assassination of the Raikage, leader of Kumogakure, as well as killing Jōnin from Kirigakure and Iwagakure, Naruto is imprisoned in Hōzukijou: A criminal containment facility known as the Blood Prison. Mui, the castle master, uses the ultimate imprisonment technique to steal power from the prisoners, which is when Naruto notices his life has been targeted. Thus begins the battle to uncover the truth behind the mysterious murders and prove Naruto's innocence.",
            ),
            NarutoMovieData(
                id = 9,
                img = naruto_movie_9,
                title = "Road to Ninja: Naruto the Movie",
                date = "07/28/2012",
                duration = "1h 49m",
                score = 74,
                overview = "Sixteen years ago, a mysterious masked ninja unleashes a powerful creature known as the Nine-Tailed Demon Fox on the Hidden Leaf Village Konoha, killing many people. In response, the Fourth Hokage Minato Namikaze and his wife Kushina Uzumaki, the Demon Fox's living prison or Jinchūriki, manage to seal the creature inside their newborn son Naruto Uzumaki. With the Tailed Beast sealed, things continued as normal. However, in the present day, peace ended when a group of ninja called the Akatsuki attack Konoha under the guidance of Tobi, the mysterious masked man behind Fox's rampage years ago who intends on executing his plan to rule the world by shrouding it in illusions.",
            ),
            NarutoMovieData(
                id = 10,
                img = naruto_movie_10,
                title = "The Last: Naruto the Movie",
                date = "12/06/2014",
                duration = "1h 54m",
                score = 75,
                overview = "Two years after the events of the Fourth Great Ninja War, the moon that Hagoromo Otsutsuki created long ago to seal away the Gedo Statue begins to descend towards the world, threatening to become a meteor that would destroy everything on impact. Amidst this crisis, a direct descendant of Kaguya Otsutsuki named Toneri Otsutsuki attempts to kidnap Hinata Hyuga but ends up abducting her younger sister Hanabi. Naruto and his allies now mount a rescue mission before finding themselves embroiled in a final battle to decide the fate of everything.",
            ),
            NarutoMovieData(
                id = 11,
                img = naruto_movie_11,
                title = "Boruto: Naruto the Movie",
                date = "08/07/2015",
                duration = "1h 35m",
                score = 75,
                overview = "The spirited Boruto Uzumaki, son of Seventh Hokage Naruto, is a skilled ninja who possesses the same brashness and passion his father once had. However, the constant absence of his father, who is busy with his Hokage duties, puts a damper on Boruto's fire. He ends up meeting his father's friend Sasuke, and requests to become... his apprentice!? The curtain on the story of the new generation rises!",
            )
        )
    )

    return narutoMovies
}
