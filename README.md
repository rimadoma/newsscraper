# Hacker News scraper
A simple command line tool for scraping stories from Hacker News, and printing them in JSON.

## How to run
1. [Install Java](https://www.java.com/en/download/help/download_options.xml)
2. Go to command line and run `java -version` to check that the Java installation succeeded.
3. [Download](https://github.com/rimadoma/newsscraper/releases/download/Demo/myScraper-jar-with-dependencies.jar) the all-in-one JAR 
4. Go to command line and type `java -jar myScraper-jar-with-dependencies.jar --posts n`, where _n_ is the number of posts you want scraped (max 100).

## Libraries
I chose the `jsoup` and `org.json.json` libraries because
* They were available through Maven
* Both came up early in a Google search, so I thought they'd have lots of help, documentation and examples available
* `org.json.json` is "straight from the horses mouth"
`jsoup` is for parsing HTML, and `org.json.json` for working with JSON data.

## Comments
* The fields in the JSON output are not in the same order as in the spec.

See [`pom.xml`](https://github.com/rimadoma/newsscraper/blob/master/pom.xml) for detailed information about libraries.
