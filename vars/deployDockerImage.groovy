def call() {
    dir('frontend') {
        sh 'docker compose down -v'
        sh 'docker compose up -d --build'
    }
}