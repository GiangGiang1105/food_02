package com.example.gfood.screen.mealdetail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.gfood.BuildConfig
import com.example.gfood.R
import com.example.gfood.data.model.Meal
import com.example.gfood.data.model.MealDetail
import com.example.gfood.data.source.local.MealFavouriteLocalDataSource
import com.example.gfood.data.source.remote.MealDetailRemoteDataSource
import com.example.gfood.data.source.repository.MealDetailRepository
import com.example.gfood.data.source.repository.MealFavouriteRepository
import com.example.gfood.utils.Constant
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_meal_detail.*

class MealDetailActivity : YouTubeBaseActivity(), ContractMealDetail.View {

    private var presenter: MealDetailPresenter? = null
    private var meal: Meal? = null
    private var mealDetail: MealDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail)
        meal = intent.getParcelableExtra(BUNDLE_MEAL) as Meal?
        initData()
    }

    @SuppressLint("SetTextI18n")
    override fun onGetMealDetailSuccess(listMealDetails: MutableList<MealDetail>) {
        if (listMealDetails != null) {
            mealDetail = listMealDetails[0]
            textMeal.text = mealDetail?.title
            textIngredient.text =
                getString(R.string.ingredient) + mealDetail?.listIngredient.toString()
            textInstruction.text =
                getString(R.string.instruction) + mealDetail?.instructions
            showVideo(getIdVideo(mealDetail?.linkVideo))
        }
    }

    override fun onGetMealFavouriteSuccess(meals: MutableList<Meal>) = Unit

    override fun onSaveMealFavourite() {}

    override fun onDeleteMealFavourite() {}

    override fun onFailed(idMessage: Int) {}

    override fun onError(exception: Exception?) {}

    override fun onGetMeal(boolean: Boolean) {}

    private fun initData() {
        presenter = MealDetailPresenter(
            MealDetailRepository.getInstance(
                MealDetailRemoteDataSource.getInstance()
            ),
            MealFavouriteRepository.getInstance(
                MealFavouriteLocalDataSource.getInstance(this)
            )
        )
        presenter?.apply {
            setView(this@MealDetailActivity)
            getListMealDetails(meal?.id)
            onGetMeal(meal?.id)
        }
    }

    private fun showVideo(link: String?) {
        viewVideo.initialize(
            BuildConfig.KEY_YOUTUBE,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    youTubePlayer: YouTubePlayer?,
                    wasRestored: Boolean
                ) {
                    if (!wasRestored) {
                        youTubePlayer?.cueVideo(link)
                        youTubePlayer?.play()
                    }
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider?,
                    result: YouTubeInitializationResult?
                ) {
                }
            })
    }

    private fun getIdVideo(linkVideo: String?) = linkVideo.toString()
        .split(Constant.BASE_URL_VIDEO)[1]

    companion object {
        private const val BUNDLE_MEAL = "BUNDLE_MEAL"

        fun newIntent(context: Context?, meal: Meal) =
            Intent(context, MealDetailActivity::class.java).apply {
                putExtra(BUNDLE_MEAL, meal)
            }
    }
}
