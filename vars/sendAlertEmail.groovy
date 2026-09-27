def call(Map args) {
    emailext(
        from: "build@example.com",
        to: "${args.alertMail}",
        subject: "${args.subject}",
        body: "${args.body}",
        attachLog: true,
        compressLog: false
    )
}