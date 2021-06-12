package com.dendi.filmscatalogs.core.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.dendi.filmscatalogs.core.utils.DataDummy
import com.dendi.filmscatalogs.favorite.FavoriteViewModel
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: com.dendi.filmscatalogs.favorite.FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: com.dendi.filmcatalogs.core.data.FilmRepository

    @Mock
    private lateinit var observer: Observer<PagedList<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>>

    @Before
    fun setUp() {
        viewModel = com.dendi.filmscatalogs.favorite.FavoriteViewModel(filmRepository)
    }

    @Test
    fun `getFavorited should be success`() {
        val expected =
            MutableLiveData<PagedList<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>>()
        expected.value = PagedTestDataSources.snapshot(DataDummy.generateDummyMovies())

        `when`(filmRepository.getFavorited()).thenReturn(expected)

        viewModel.getFavorite().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavorite().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getFavorited should be success but data is empty`() {
        val expected =
            MutableLiveData<PagedList<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        `when`(filmRepository.getFavorited()).thenReturn(expected)

        viewModel.getFavorite().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getFavorite().value?.size
        Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    class PagedTestDataSources private constructor(private val items: List<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>) :
        PositionalDataSource<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>() {
        companion object {
            fun snapshot(items: List<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity> = listOf()): PagedList<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(
            params: LoadRangeParams,
            callback: LoadRangeCallback<com.dendi.filmcatalogs.core.data.source.local.entity.ListEntity>
        ) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}