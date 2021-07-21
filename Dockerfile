FROM oracle/graalvm-ce:20.1.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/gamapi-cli
WORKDIR /home/app/gamapi-cli

RUN native-image --no-server -cp build/libs/gamapi-cli-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/gamapi-cli/gamapi-cli /app/gamapi-cli
ENTRYPOINT ["/app/gamapi-cli"]
