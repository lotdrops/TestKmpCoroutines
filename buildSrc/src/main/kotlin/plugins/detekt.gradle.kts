package plugins

import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.kotlin.dsl.registering
import org.gradle.kotlin.dsl.withType

plugins {
    id("io.gitlab.arturbosch.detekt")
}

val configPath = "$rootDir/config/detekt/detekt.yml"
val baselinePath = "$rootDir/config/detekt/baseline.xml"

val xmlReportMerge by tasks.registering(io.gitlab.arturbosch.detekt.report.XmlReportMergeTask::class) {
    output.set(project.layout.buildDirectory.file("reports/detekt/merge.xml"))
}
subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = "1.8"
    }

    detekt {
        toolVersion = Versions.detekt
        input = files("src")
        config = files(configPath)
        baseline = file(baselinePath)
        buildUponDefaultConfig = true
        reports {
            html {
                enabled = true
                destination = file("reports/detekt.html")
            }
            xml {
                enabled = true
                destination = file("reports/detekt.xml")
            }
            txt {
                enabled = false
                destination = file("reports/detekt.txt")
            }
        }

        plugins.withType(io.gitlab.arturbosch.detekt.DetektPlugin::class) {
            tasks.withType(Detekt::class) detekt2@{
                xmlReportMerge.configure {
                    this.mustRunAfter(this@detekt2)
                    input.from(this@detekt2.xmlReportFile)
                }
            }
        }
    }
}
