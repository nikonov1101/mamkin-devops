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
                    token = "zxx8082c5f54077ba0eae2247c34e4adce68b5f5731999b0024cc2498a733fb320e3905446b8404e32a775d03cbe80d301b"
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
