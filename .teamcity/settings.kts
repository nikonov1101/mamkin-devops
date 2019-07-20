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
    buildType(FrontendE2ETest)
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
        feature(PublishCommitStatusFeature)
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

object FrontendE2ETest : BuildType({
    name = "frontend/e2e"

    vcs {
        root(MamkinDevopsVcsRoot)
        checkoutMode = CheckoutMode.ON_AGENT
        cleanCheckout = true
    }

    triggers {
        vcs {}
    }

    features {
        feature(PublishCommitStatusFeature)
    }

    steps {
        script {
            name = "frontend/unit"
            scriptContent = """
                #!/bin/bash

                echo "testing"
                exit 0
            """.trimIndent()
        }
    }
})
