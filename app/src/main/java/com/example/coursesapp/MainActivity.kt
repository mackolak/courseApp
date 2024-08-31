package com.example.coursesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursesapp.model.Topic
import com.example.coursesapp.ui.theme.CoursesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicList(DataSource.topics)
                }
            }
        }
    }
}

@Composable
fun TopicGridItem(topic: Topic, modifier: Modifier = Modifier) {
    Card(
    ) {
        Row {
            Image(
                painter = painterResource(id = topic.imageId),
                contentDescription = null,
                modifier = Modifier.size(68.dp)
            )
            Column(
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.padding_medium),
                    start = dimensionResource(id = R.dimen.padding_medium),
                    end = dimensionResource(id = R.dimen.padding_medium)
                )
            ) {
                Text(
                    text = stringResource(id = topic.topicId),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small)),
                )
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier.padding(end=dimensionResource(id = R.dimen.padding_small))
                    )
                    Text(
                        text = topic.lessonsCount.toString(),
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
        }
    }
}

@Composable
fun TopicList(topicList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_small)),
    ) {
        items(topicList) { topic ->
            TopicGridItem(topic = topic)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoursesAppTheme {
        TopicList(DataSource.topics.sortedBy { it.topicId })
    }
}