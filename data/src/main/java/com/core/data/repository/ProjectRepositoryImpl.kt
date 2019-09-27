package com.core.data.repository

import com.core.commons.Resource
import com.core.data.network.entity.ProjectEntity
import com.core.data.network.gateway.AppGateway
import com.core.data.persistence.dao.ProjectDao
import com.core.data.util.*
import com.core.domain.Project
import com.core.domain.repository.ProjectRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject
import io.reactivex.BackpressureStrategy
import java.util.concurrent.TimeUnit

class ProjectRepositoryImpl @Inject internal constructor(
        private val gateway: AppGateway,
        private val dao: ProjectDao
) : ProjectRepository {

    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    override fun list(username: String): Flowable<Resource<List<Project>>> {
        return Flowable.create({ emitter ->
            object : NetworkBoundResource<List<Project>, List<ProjectEntity>>(emitter) {

                override fun saveCallResult(data: List<ProjectEntity>) {
                    dao.insert(*data.map { entity -> entity.toProject() }.toTypedArray())
                }

                override fun shouldFetch(data: List<Project>?): Boolean {
                    return data == null || data.isEmpty() || repoListRateLimit.shouldFetch(username)
                }

                override fun loadFromDb(): Flowable<List<Project>> {
                    return dao.findAll()
                }

                override fun createCall(): Single<List<ProjectEntity>> {
                    return gateway.projectList(username)
                }
            }
        }, BackpressureStrategy.BUFFER)
    }

    override fun detail(username: String, projectName: String): Single<Project> = gateway
            .project(username, projectName)
            .map { it.toProject() }
}