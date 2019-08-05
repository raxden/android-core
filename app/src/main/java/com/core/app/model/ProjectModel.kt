package com.core.app.model

import com.core.domain.Project

data class ProjectModel(
        var name: String? = null,
        var description: String? = null
) {

    constructor(project: Project) : this(
            project.name,
            project.description
    )
}
