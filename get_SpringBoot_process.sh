#!/bin/bash
#Fragt den/die SpringBoot-Prozesse ab

ps auwx | grep -i "spring-boot:run" | grep -v grep