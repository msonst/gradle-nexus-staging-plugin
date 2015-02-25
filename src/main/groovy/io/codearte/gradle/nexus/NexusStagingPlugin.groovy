package io.codearte.gradle.nexus

import io.codearte.gradle.nexus.infra.NexusStagingException
import org.gradle.api.Plugin
import org.gradle.api.Project

class NexusStagingPlugin implements Plugin<Project> {

    private Project project
    private NexusStagingExtension extension

    @Override
    void apply(Project project) {
        this.project = project
        this.extension = createExtension(project)
        createAndConfigureGetStagingProfileTask2(project)
    }

    NexusStagingExtension createExtension(Project project) {
        project.extensions.create("nexusStaging", NexusStagingExtension)
    }

    void createAndConfigureGetStagingProfileTask2(Project project) {
        GetStagingProfileTask2 task = project.tasks.create("getStagingProfileTask", GetStagingProfileTask2)
        task.with {
            description = "TODO getStagingProfileTask"
            group = "release"
        }
        setTaskDefaults(task)
    }

    void setTaskDefaults(BaseStagingTask task) {
        task.conventionMapping.with {
            nexusUrl = { extension.nexusUrl }
            username = { extension.username }
            password = { extension.password }
        }
    }
}