import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.CommitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

object PublishCommitStatusFeature : CommitStatusPublisher({
    publisher = github {
        githubUrl = "https://api.github.com"
        authType = personalToken {
            token = "credentialsJSON:558f0acb-146d-4541-93e4-1c2833d8ef80"
        }
    }
})

version = "2019.1"

// root project, the entrypoint of this configuration
project {
    vcsRoot(MamkinDevopsVcsRoot)

    buildType(Test)
}

object Test : BuildType({
    name = "test"

    vcs {
        root(MamkinDevopsVcsRoot)
        checkoutMode = CheckoutMode.ON_AGENT
        cleanCheckout = true
    }

    triggers {
        vcs {}
    }

    features {
        PublishCommitStatusFeature
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

object MamkinDevopsVcsRoot : GitVcsRoot({
    id = AbsoluteId("MamkinDevopsVcsRoot")

    name = "sshaman1101/mamkin-devops"
    url = "git@github.com:sshaman1101/mamkin-devops.git"
    authMethod = uploadedKey {
        uploadedKey = "id_rsa_sshaman1101"
    }

    userNameStyle = UserNameStyle.FULL

    branch = "master"
    branchSpec = """
        +:refs/heads/*
    """.trimIndent()
})
