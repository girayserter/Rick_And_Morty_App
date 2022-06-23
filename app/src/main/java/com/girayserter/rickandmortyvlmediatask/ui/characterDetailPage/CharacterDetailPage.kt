package com.girayserter.rickandmortyvlmediatask.ui.characterDetailPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.girayserter.rickandmortyvlmediatask.data.api.model.Result
import com.girayserter.rickandmortyvlmediatask.ui.characters.CharactersPageViewModel

@Composable
fun CharacterDetailPage(
    characterId:Int,
    navController: NavController
){
    val viewModel: CharacterDetailViewModel= hiltViewModel()
    val characterInfo by viewModel.characterInfo.collectAsState()
    LaunchedEffect(key1 = characterId) {
        viewModel.getCharacterInfo(characterId)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            CharacterImage(character = characterInfo)
            CharacterInfos(character = characterInfo)
        }
    }


}

@Composable
fun CharacterImage(character: Result){
    val imagePainter = rememberImagePainter(data = character.image)
    Card(
        shape= MaterialTheme.shapes.medium,
        modifier= Modifier
            .padding(16.dp)
    ){
        Box{
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )

        }
    }
}

@Composable
fun CharacterInfos(character: Result){
    Column(){
        Row(modifier = Modifier
            .fillMaxWidth(),
        Arrangement.Center) {
            Text(text = character.name, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(Modifier.padding(horizontal = 10.dp)) {
            Column() {
                Text(text = "Status: ",fontSize = 20.sp, fontWeight = FontWeight.Bold,modifier = Modifier.height(50.dp))
                Text(text = "Species: ", fontSize = 20.sp, fontWeight = FontWeight.Bold,modifier = Modifier.height(50.dp))
                Text(text = "Type: ", fontSize = 20.sp, fontWeight = FontWeight.Bold,modifier = Modifier.height(50.dp))
                Text(text = "Gender: ", fontSize = 20.sp, fontWeight = FontWeight.Bold,modifier = Modifier.height(50.dp))
                Text(text = "Origin: ", fontSize = 20.sp, fontWeight = FontWeight.Bold,modifier = Modifier.height(50.dp))
                Text(text = "Last Location: ", fontSize = 20.sp, fontWeight = FontWeight.Bold,modifier = Modifier.height(50.dp))
            }
            Column() {
                if(character.status!=null && character.status!=""){
                    Text(text = character.status, fontSize = 20.sp, modifier = Modifier.height(50.dp))
                }
                else{
                    Text(text = "Unknown", fontSize = 20.sp,modifier = Modifier.height(50.dp))
                }

                if(character.species!=null && character.species!=""){
                    Text(text = character.species, fontSize = 20.sp ,modifier = Modifier.height(50.dp))
                }
                else{
                    Text(text = "Unknown", fontSize = 20.sp,modifier = Modifier.height(50.dp))
                }

                if(character.type!=null && character.type!=""){
                    Text(text = character.type, fontSize = 20.sp,modifier = Modifier.height(50.dp))
                }
                else{
                    Text(text = "Unknown", fontSize = 20.sp,modifier = Modifier.height(50.dp))
                }

                if(character.gender!=null && character.gender!=""){
                    Text(text = character.gender, fontSize = 20.sp,modifier = Modifier.height(50.dp))
                }
                else{
                    Text(text = "Unknown", fontSize = 20.sp,modifier = Modifier.height(50.dp))
                }

                if(character.origin.name!=null && character.origin.name!=""){
                    Text(text = character.origin.name, fontSize = 20.sp,modifier = Modifier.height(50.dp))
                }
                else{
                    Text(text = "Unknown", fontSize = 20.sp,modifier = Modifier.height(50.dp))
                }

                if(character.location.name!=null && character.location.name!=""){
                    Text(text = character.location.name, fontSize = 20.sp,modifier = Modifier.height(50.dp))
                }
                else{
                    Text(text = "Unknown", fontSize = 20.sp,modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}