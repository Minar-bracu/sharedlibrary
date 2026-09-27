def call() {
    dir('frontend') {
        sh '''
            trivy image \\
                --scanners vuln,misconfig,secret \\
                --skip-files '**/betterleaks.json,**/semgrep.json' \\
                --format json -o trivy-image-report.json "${PROJECT_NAME}:${IMAGE_TAG}"

            trivy convert \\
                --format template --template "@/vagrant/html.tpl" \\
                -o trivy-image-report.html trivy-image-report.json
        '''
        archiveArtifacts artifacts: 'trivy-image-report.*', fingerprint: true
    }
}