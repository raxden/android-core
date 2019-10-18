package com.core.data.repository.mapper

import com.core.common.android.DataMapper
import com.core.data.remote.entity.ProjectEntity
import com.core.domain.Project
import javax.inject.Singleton

@Singleton
class ProjectDataMapper: DataMapper<ProjectEntity, Project>() {

    override fun transform(source: ProjectEntity): Project {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun inverse(source: Project): ProjectEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}