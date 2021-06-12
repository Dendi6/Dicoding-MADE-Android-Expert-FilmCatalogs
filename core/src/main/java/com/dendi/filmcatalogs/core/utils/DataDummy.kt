package com.dendi.filmcatalogs.core.utils

import com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity
import com.dendi.filmcatalogs.core.data.source.remote.response.ListResponse

object DataDummy {
    fun generateDummyMovies(): List<ListEntity> {

        val movies = ArrayList<ListEntity>()

        movies.add(
            ListEntity(
                399566,
                "Godzilla vs. Kong",
                " ",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                false,
                "movies"
            )
        )

        return movies
    }

    fun generateDummyTvShow(): List<ListEntity> {
        val tvShow = ArrayList<ListEntity>()

        tvShow.add(
           ListEntity(
                1399,
                "",
                "Game of Thrones",
                "/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                false,
                "movies"
            )
        )

        return tvShow
    }

    fun generateRemoteDummyMovies(): ArrayList<ListResponse> {
        val movies = ArrayList<ListResponse>()

        movies.add(
            ListResponse(
                460465,
                "Mortal Kombat",
                " ",
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "tv"
            )
        )

        return movies
    }

    fun generateRemoteDummyTvShow(): ArrayList<ListResponse> {
        val tvShow = ArrayList<ListResponse>()

        tvShow.add(
            ListResponse(
                88396,
                "null",
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "tv"
            )
        )

        return tvShow
    }
}