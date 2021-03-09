package io.erikrios.github.githubuserapp.widgets

import android.app.job.JobParameters
import android.app.job.JobService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent

class UpdateWidgetService : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        val intent = Intent(this, GithubUserWidget::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE

        val ids = AppWidgetManager.getInstance(application)
            .getAppWidgetIds(ComponentName(application, GithubUserWidget::class.java))

        val githubUserWidget = GithubUserWidget()
        githubUserWidget.onUpdate(this, AppWidgetManager.getInstance(this), ids)
        jobFinished(params, false)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean = false
}