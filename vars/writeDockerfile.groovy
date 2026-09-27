def call() {
    dir('frontend') {
        writeFile file: 'Dockerfile', text: '''
FROM node:20-slim as builder
WORKDIR /app
COPY package*.json ./
RUN npm install

FROM node:20-slim
WORKDIR /app
COPY --from=builder /app/node_modules ./node_modules
COPY . .

EXPOSE 5173

CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]
'''
        archiveArtifacts artifacts: 'Dockerfile', fingerprint: true
    }
}