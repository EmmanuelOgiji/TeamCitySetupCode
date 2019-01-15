FROM jetbrains/teamcity-server

RUN git clone https://github.com/EmmanuelOgiji/TeamCitySetupCode.git /JavaSetupCode
WORKDIR /JavaSetupCode
RUN apt-get update && apt-get install -y maven
CMD ["echo", "Test Run"]