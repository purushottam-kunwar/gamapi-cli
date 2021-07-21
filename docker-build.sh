#!/bin/sh
docker build . -t gamapi-cli
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run -p 8080:8080 gamapi-cli"
