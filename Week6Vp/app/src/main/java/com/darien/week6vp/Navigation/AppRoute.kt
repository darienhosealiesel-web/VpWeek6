package com.darien.week6vp.Navigation


import kotlinx.serialization.Serializable

@Serializable
object DashboardRoute

@Serializable
object CommunitiesRoute

@Serializable
data class ClubProjectsRoute(val communityId: String)

object ProjectDetail {
    const val ARG_ID = "projectId"
    const val ROUTE = "project_detail/{$ARG_ID}"
    fun createRoute(projectId: String) = "project_detail/$projectId"
}