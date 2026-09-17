package io.github.pinkavocadodev.paalm

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.util.Date


@Composable
fun AboutPage(modifier: Modifier= Modifier){
    val scroll = rememberScrollState()
    val uriHandler = LocalUriHandler.current
    val date = LocalDate.now().year
    Column(
        modifier = modifier.fillMaxSize().background(Color.White).verticalScroll(scroll),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.padding(25.dp)) {
            Text("Che cos'è Posso andare al mare?", fontSize=25.sp, fontWeight = FontWeight(1000), color = Color.Black)
            Text("Paalm? è una web app che si pone lo scopo di rispondere in maniera coincisa e minimalista alla semplice domanda \"Si può andare al mare oggi?\".\n" +
                    "\n" +
                    "Conosciamo personalmente quanto sia problematico preparare una giornata di mare, soprattutto se si abita in zone lontane dalla costa, perciò abbiamo deciso di creare Paalm?, un servizio in grado di interpretare dati meteo e di restituire un giudizio coinciso e accurato riguardo la qualità della giornata di mare.\n" +
                    "\n" +
                    "Basta consultare servizi meteo di dubbia validità e basta interpretare noi stessi una miriade di dati confusi, insomma, Posso andare al mare o no?", color = Color.Black)
            HorizontalDivider(Modifier.padding(10.dp))
            Text("FAQ", fontSize = 20.sp, fontWeight = FontWeight(1000), color = Color.Black)
            Text("Q: Quale servizio meteo utilizzate per la raccolta dati?\n",fontWeight = FontWeight(1000), color = Color.Black)
            Text("A: Usiamo Open-Meteo, è un servizio di consulto molto versatile e facile da implementare.\n", color = Color.Black)
            Text("Q: Quali dati vengono interpellati dal vostro servizio?\n",fontWeight = FontWeight(1000), color = Color.Black)
            Text("A: I dati interpellati per il giudizio sono:\n" +
                    "    Precipitazioni totali;\n" +
                    "    Media Radiazioni UV;\n"  +
                    "    Media Nuvolosità;\n" +
                    "    Media di Temperatura.\n" +
                    "Raccogliamo i dati negli orari compresi tra le 8:00 e le 19:00, in quanto sono gli orari più papabili per godersi una giornata in spiaggia, qualunque altro orario viene ignorato! Inoltre raccogliamo anche dati relativi a vento e temperatura istantanei, visibili cliccando sul pulsante freccetta nella schermata principale.\n", color = Color.Black)
            Text("Q: Ho notato il tag \"Attenzione\" nella schermata \"Info aggiuntive\", di che si tratta?\n",fontWeight = FontWeight(1000), color = Color.Black)
            Text("A: Il servizio offre anche un giudizio in merito all'intensità delle radiazioni UV, già disperse dalle condizioni atmosferiche, per suggerire la potenza SPF della crema solare. Va da sé che il servizio offre un suggerimento, di conseguenza portate con voi la crema solare che ritenete più giusta e non scottatevi ;)\n", color = Color.Black)
            Text("Q: Posso usare il vostro servizio come metro di giudizio per andare in barca o in ogni caso andare a mare aperto?\n", fontWeight = FontWeight(1000), color = Color.Black)
            Text("A: ASSOLUTAMENTE NO. Il nostro servizio fornisce un giudizio lightweight sulla qualità della giornata se passata in spiaggia, non ci assumiamo alcuna responsabilità per danni a cose persone o animali nell'utilizzo del nostro servizio.\n", color = Color.Black)
            Text("Q: Raccogliete i dati degli utenti? Avete scopi commerciali?\n", fontWeight = FontWeight(1000), color = Color.Black)
            Text("A: Il servizio \"Posso andare al mare?\" non raccoglie alcun dato personale, né usa tracker di attività online.", color = Color.Black)
            Spacer(Modifier.size(10.dp))
            Button(
                onClick = {uriHandler.openUri("https://www.possoandarealmare.altervista.org/terms-and-conditions.php")},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Termini e condizioni")
            }
            HorizontalDivider(Modifier.padding(10.dp))
            Text("Contatti", fontSize = 25.sp, fontWeight = FontWeight(1000), color = Color.Black)

                Button(
                    onClick =  { uriHandler.openUri("mailto:andreadistefano.work@gmail.com?subject=Supporto%20Paalm")},
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text("E-Mail: andreadistefano.work@gmail.com")
                }
                Button(
                    onClick =  { uriHandler.openUri("https://pinkavocadodev.github.io/")},
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(text = "Github: PinkAvocadoDev")
                }
            HorizontalDivider(Modifier.padding(10.dp))
            Text("© $date Andrea Di Stefano, tutti i diritti riservati.", color = Color.Black)
        }
    }
}