FROM jetbrains/teamcity-server
COPY datadir/. /data/teamcity_server/datadir
WORKDIR /data/teamcity_server/datadir
RUN apt-get update && apt-get install -y maven