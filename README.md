# Bristech-Server

[![Crates.io](https://img.shields.io/crates/l/rustc-serialize.svg?maxAge=2592000)]()  [![Generic badge](https://img.shields.io/badge/license-Firebase-<red>.svg)](https://shields.io/)  [![Build status](https://travis-ci.org/google/licenseclassifier.svg?branch=master)](https://travis-ci.org/google/licenseclassifier)

## Aim
The aim of the project is to develop a more elegant way for speakers, managers and attendees to interact in the run-up to and during events. The key motivation for the new platform is to go beyond what meetup currently offers Bristech and its users.

## Solution
Our solution is a dynamic mobile application partially integrated with meetup.com, to begin the transition to a holistic application while not losing the benefits of meetup.com. Members of the event will be able to view past and upcoming events, register for events, provide feedback on events, and be prompted to provide feedback when attending a talk, as well as monitoring their attendance through a geofencing check-in system to ensure they are in the vicinity of the venue at the correct time. 


This is one part of a two part solution which in this case takes the form of a **Server_**.  
It's built with *IntelliJ* using **Spring boot** and deployed to a **Heroku server**.

## Pre-requisites
- Gradle 4.4
- Java 7
- IntelliJ / Eclipse
- Access to Internet


## Build Instructions
1. Clone the repository *git clone **https://github.com/TheWalkingFridge/Bristech-Server***
2. Install [**Java**](https://java.com/en/download/help/download_options.xml)
3. Install [**IntelliJ**](https://www.jetbrains.com/idea/) or Eclipse 
4. Install [**Gradle**](https://gradle.org/) 
5. Build the project and run it

## Endpoints
### User
- User login: `user/login` 
- Get all users: `user/all`
- Register to event: `user/register`
- User attend event: `user/attend`
### Event
- Get all events: `event/all`
- Get upcoming events: `event/upcoming`
- Get past events: `event/past`
- Synchronise events from Meetup: `event/update`

## Features
- HTTP request handling for *Users* and *Events*
- **MySQL** database with Events and Users deployed on **Oracle**
- Integration with **Firebase** for user authentication and authorisation with Email, Google and Facebook
- Stores User details and leaves Account details to firebase
- Synchronisation with Meetup's database for Bristech's events
- Hosted on Heroku servers


## License
Copyright 2017 Bristech

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

