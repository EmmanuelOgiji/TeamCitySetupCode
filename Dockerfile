FROM jetbrains/teamcity-server
RUN git clone https://github.com/EmmanuelOgiji/TeamCityDataDir.git /config_data
RUN git clone https://github.com/EmmanuelOgiji/TeamCitySetupCode.git /JavaSetupCode
WORKDIR /data/teamcity_server/datadir
RUN apt-get update && apt-get install -y maven
CMD [ "echo","Ready" ]