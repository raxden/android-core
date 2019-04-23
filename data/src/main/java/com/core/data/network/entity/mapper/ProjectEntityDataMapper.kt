package com.core.data.entity.mapper

import android.content.Context
import com.core.commons.DataMapper
import com.core.commons.extension.mapTo
import com.core.data.entity.ProjectEntity
import com.core.domain.Project
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectEntityDataMapper @Inject internal constructor(context: Context) : DataMapper<ProjectEntity, Project>(context) {

    override fun transform(source: ProjectEntity): Project = source.mapTo()

    override fun inverse(source: Project): ProjectEntity = source.mapTo()
}
