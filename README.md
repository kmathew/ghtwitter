# GHTwitter

This application pulls list of recently updated projects with relation to the term "Reactive". Then, it searches for tweets on Twitter based on each projects full name. It prunes projects without any tweets and publishes the list of the first 10 projects with tweets to a .json file.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

Maven
JDK 1.8
IDE (Optional - only if you want modify rates)

### Installing

Clone repo or Extract zip/tar


```
mvn clean package 
```

A configuration file is needed with Twitter OAuth secret keys and tokens in the following format and named twitter4j.properties. You will need to apply to Twitter Apps and generate a consumer key, consumer secret, token, and token secret for your application.

[Twitter oAuth](https://developer.twitter.com/en/docs/basics/authentication/overview/application-only.html)

It needs to be placed in root directory of the project.

```
consumerkey
consumersecret
token
tokensecret
```

If the above is not clear:

```
XxxxxxxxxxxxxXxxXxXxxXxXx                             <-- consumer key
XXxxxXxxxxXXXxxxxasrf35235dxXXxxxxxxxxxxxxxxxxxxxx    <-- consumer secret
52356362-XxxXxxx3562XxxXXXXXXXXXXXXXXXXXXXXXxxxxXx    <-- token
Xxew5526677343xxxxxxxxxxxxXxXxxXXXXXXXXXXXXxx         <-- token secret
```

You can run the compiled jar package with the following command:

```
java -jar ghtwitter-0.1.0.jar

```

Example of signs of the right drection:

```
10:08:00.895 [main] DEBUG ghtwitter.Main - configuration file name: twitter4j.properties
10:08:00.895 [main] DEBUG ghtwitter.Main - project search term: Reactive
10:08:00.895 [main] DEBUG ghtwitter.Main - output file name: output.json
10:08:04.098 [main] DEBUG ghtwitter.Main - Adding projects to list: page 1 of 10
10:08:13.545 [main] DEBUG org.springframework.web.client.RestTemplate - Created GET request for "https://api.github.com/search/repositories?archived=false&sort=updated&q=Reactive&per_page=100&page=1"
```

## Possible Future Enhancements

* Improve rate limit caching to return more projects with tweets
* Add support for live streaming using Twitter's Streaming API
* Store projects and their respective tweets in a database
* Add command line options to change default config file names, search for other projects other than "Reactive", etc
* Add support for authenticating on GitHub
* Add more output file format support
* More unit tests

## Built With

* [Twitter4j](https://github.com/yusuke/twitter4j/) - Used to pull tweets
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring](https://rometools.github.io/rome/) - Used to directly call GitHub API
* [JSON-Simple](https://code.google.com/archive/p/json-simple/) - Used to process GitHub JSON responses
 

## Authors

* **Kevin Mathew** - *Initial work* 


## Acknowledgments

* Edgar Magana for answering clarifications on the assignment.
* Sam Posemsky for selecting me for this opportunity.
* You for taking the time to read all of this.
