package com.gxj.jacoco

import com.android.build.gradle.AppExtension
import com.gxj.jacoco.extension.JacocoExtension
import com.gxj.jacoco.task.BranchDiffTask
import groovy.util.logging.Log
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.UnknownTaskException
@Log("log")
class JacocoPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        log.info("apply jacoco 插件")
        JacocoExtension jacocoExtension = project.extensions.create("jacocoCoverageConfig", JacocoExtension)
        log.info("apply jacoco jacocoExtension .....")

        project.configurations.all { configuration ->
            def name = configuration.name
            log.info("apply jacoco configuration.name:" + name + ".............")
            if (name != "implementation" && name != "compile") {
                return
            }
            //为Project加入agent依赖
//            configuration.dependencies.add(project.dependencies.create('com.ttp.jacoco:rt:0.0.5'))
        }

        def android = project.extensions.android


        if (android instanceof AppExtension) {
            log.info("apply jacoco JacocoTransform ================")
            JacocoTransform jacocoTransform = new JacocoTransform(project, jacocoExtension)
            android.registerTransform(jacocoTransform)
            // throw an exception in instant run mode
            android.applicationVariants.all { variant ->
                def variantName = variant.name.capitalize()
                try {
                    def instantRunTask = project.tasks.getByName("transformClassesWithInstantRunFor${variantName}")
                    if (instantRunTask) {
                        throw new GradleException("不支持instant run")
                    }
                } catch (UnknownTaskException e) {
                }
            }
        }

        project.afterEvaluate {
            android.applicationVariants.all { variant ->
                def variantName = variant.name.capitalize()

                if (project.tasks.findByName('generateReport') == null) {
                    BranchDiffTask branchDiffTask = project.tasks.create('generateReport', BranchDiffTask)
                    branchDiffTask.setGroup("jacoco")
                    branchDiffTask.jacocoExtension = jacocoExtension
                }
            }
        }
        log.info("apply jacoco 插件  end ==========================")
    }
}