package mamkin.devops.resources

import jetbrains.buildServer.configs.kotlin.v2018_2.AbsoluteId
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.CommitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

object PublishCommitStatusFeature : CommitStatusPublisher({
    publisher = github {
        githubUrl = "https://api.github.com"
        authType = personalToken {
            token = "credentialsJSON:c90c9078-cf47-4709-bf2b-6c3d5440b4ac"
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