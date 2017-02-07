# SeniorCommanderWeb

Web application for the SeniorCommander bot.

# Architecture
Uses Spring Boot for the primary architecture. 

# Communication with Bot
The web application has direct access to the same database as the bot so it can "communicate" by
modifying the state of the data. Eventually I'd like a global "channel" set up in the bot that
allows the web application to send raw messages via HTTP. Even further into the future, I'd like
to use a shared message queue like RabbitMQ.
