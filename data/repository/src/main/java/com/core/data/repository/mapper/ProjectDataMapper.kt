package com.core.data.repository.mapper

import android.net.Uri
import com.core.common.android.DataMapper
import com.core.common.android.extensions.toLocalDateTime
import com.core.data.remote.entity.ProjectEntity
import com.core.domain.Project
import javax.inject.Singleton

@Singleton
class ProjectDataMapper(
    private val userDataMapper: UserDataMapper
): DataMapper<ProjectEntity, Project>() {

    override fun transform(source: ProjectEntity) = Project(
        id = source.id ?: 0L,
        name = source.name ?: "",
        description = source.description ?: "",
        user = source.user?.let { userDataMapper.transform(it) },
        uri = Uri.parse(source.url),
        createdAt = source.created_at?.toLocalDateTime(""),
        updatedAt = source.updated_at?.toLocalDateTime("")
    )

    override fun inverse(source: Project): ProjectEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}