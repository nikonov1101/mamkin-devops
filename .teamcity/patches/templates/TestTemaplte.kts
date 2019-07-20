package patches.templates

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Template
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a template with id = 'TestTemaplte'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, Template({
    id("TestTemaplte")
    name = "Test  Temaplte"

    vcs {
        root(AbsoluteId("MamkinDevopsVcsRoot"))

        checkoutMode = CheckoutMode.ON_AGENT
        cleanCheckout = true
    }

    steps {
        script {
            name = "frontend/unit"
            id = "RUNNER_1"
            scriptContent = """
                #!/bin/bash
                
                export NVM_DIR="${'$'}HOME/.nvm"
                [ -s "${'$'}NVM_DIR/nvm.sh" ] && \. "${'$'}NVM_DIR/nvm.sh"  # This loads nvm
                
                echo "testing"
                make -C ./frontend test/unit
            """.trimIndent()
        }
    }

    triggers {
        vcs {
            id = "TRIGGER_1"
        }
    }
}))
