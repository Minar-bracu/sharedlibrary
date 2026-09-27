def call(String alertMail) {
    dir('frontend') {
        def rc = sh(script: 'betterleaks dir . --report-path betterleaks.json --report-format json', returnStatus: true)
        if (rc != 0) {
            unstable("betterleaks found potential secrets (exit ${rc})")
            emailext(
                from: "build@example.com",
                to: "${alertMail}",
                subject: "Potential Secrets Detected: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "betterleaks found potential secrets (exit ${rc}).",
                attachLog: true,
                compressLog: false
            )
        }
        archiveArtifacts artifacts: 'betterleaks.*', fingerprint: true
    }
}