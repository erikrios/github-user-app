package io.erikrios.github.githubuserapp.widgets

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import io.erikrios.github.githubuserapp.R
import io.erikrios.github.githubuserapp.databases.UserDatabase
import io.erikrios.github.githubuserapp.models.User
import io.erikrios.github.githubuserapp.widgets.GithubUserWidget.Companion.EXTRA_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

internal class StackRemoteViewsFactory(private val context: Context) :
    RemoteViewsService.RemoteViewsFactory {

    private val users = mutableListOf<User>()

    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        runBlocking {
            val deferred = async(Dispatchers.IO) {
                UserDatabase(context).getUserDao().getUsers()
            }
            val favoriteUsers = deferred.await()
            users.clear()
            users.addAll(favoriteUsers)
        }
    }

    override fun onDestroy() {

    }

    override fun getCount(): Int = users.size

    override fun getViewAt(position: Int): RemoteViews {
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_item)

        if (users.isNotEmpty()) {
            try {
                val bitmap = Glide.with(context)
                    .asBitmap()
                    .load(users[position].avatarUrl)
                    .submit(512, 512)
                    .get()

                remoteViews.setImageViewBitmap(R.id.image_view, bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val extras = bundleOf(EXTRA_NAME to users[position].name)
        val fillIntent = Intent()
        fillIntent.putExtras(extras)
        remoteViews.setOnClickFillInIntent(R.id.image_view, fillIntent)
        return remoteViews
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = 0

    override fun hasStableIds(): Boolean = false
}