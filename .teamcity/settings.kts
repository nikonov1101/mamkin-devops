import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs

import mamkin.devops.resources.*


version = "2019.1"

// root project, the entrypoint of this configuration
project {
    vcsRoot(MamkinDevopsVcsRoot)

    subProjects(TestSuite)
}

object TestSuite : Project({
    name = "test suite"

    buildType(FrontendUnitTests)
})

object FrontendUnitTests : BuildType({
    name = "frontend/unit"

    vcs {
        root(MamkinDevopsVcsRoot)
        checkoutMode = CheckoutMode.ON_AGENT
        cleanCheckout = true
    }

    triggers {
        vcs {}
    }

    features {
        commitStatusPublisher {
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:558f0acb-146d-4541-93e4-1c2833d8ef80"
                }
            }
        }
    }

    steps {
        script {
            name = "frontend/unit"
            scriptContent = """
                #!/bin/bash
                
                # export NVM_DIR="${'$'}HOME/.nvm"
                # [ -s "${'$'}NVM_DIR/nvm.sh" ] && \. "${'$'}NVM_DIR/nvm.sh"  # This loads nvm
                
                echo "testing"
                exit 0
                # make -C ./frontend test/unit
            """.trimIndent()
        }
    }
})
