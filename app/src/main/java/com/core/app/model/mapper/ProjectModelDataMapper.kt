package com.core.app.model.mapper

import android.content.Context
import com.core.app.model.ProjectModel
import com.core.commons.DataMapper
import com.core.commons.extension.mapTo
import com.core.domain.Project
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectModelDataMapper @Inject internal constructor(context: Context) : DataMapper<Project, ProjectModel>(context) {

    override fun transform(source: Project): ProjectModel = source.mapTo()

    override fun inverse(source: ProjectModel): Project = source.mapTo()
}
