def call() {
    dir('frontend') {
        sh '''
            trivy fs . \\
                --scanners vuln,misconfig,secret \\
                --skip-files 'betterleaks.json,semgrep.json,.pkgjson.md5' \\
                --format json -o trivy-fs-report.json

            trivy convert \\
                --format template --template "@/vagrant/html.tpl" \\
                -o trivy-fs-report.html trivy-fs-report.json
        '''
        archiveArtifacts artifacts: 'trivy-fs-report.*', fingerprint: true
    }
}
