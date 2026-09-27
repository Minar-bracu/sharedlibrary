def call() {
    dir('frontend') {
        sh "semgrep scan --config auto --json --output=semgrep.json"
        archiveArtifacts artifacts: 'semgrep.*', fingerprint: true
    }
}