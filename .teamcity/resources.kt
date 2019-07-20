package mamkin.devops.resources

import jetbrains.buildServer.configs.kotlin.v2018_2.AbsoluteId
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.CommitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

object PublishCommitStatusFeature : CommitStatusPublisher({
    publisher = github {
        githubUrl = "https://api.github.com"
        authType = personalToken {
            token = "zxx8082c5f54077ba0eae2247c34e4adce68b5f5731999b0024cc2498a733fb320e3905446b8404e32a775d03cbe80d301b"
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