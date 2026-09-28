def call() {
    dir('frontend') {
        writeFile file: 'docker-compose.yaml', text: '''
services:
    frontend:
        build:
            context: .
            dockerfile: Dockerfile
        image: frontend-frontend2:latest
        container_name: frontend_container_training
        restart: always
        ports:
            - "5173:5173"
        environment:
            VITE_API_URL: http://localhost:4000/api/v1
        networks:
            - someexternal

networks:
    someexternal:
'''
        archiveArtifacts artifacts: 'docker-compose.yaml', fingerprint: true
    }
}
