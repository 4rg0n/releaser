Releaser
========

## What is it?

Releaser -- I'm not really pleased with the name... -- is a tool which crawls a folder recursively for movie releases and collecting information from various apis.
I started this project, because I have to learn Java for my job, so please don't blame me for the code. I'm' trying my best =)

## Why?

Some people own a very large movie collection on their harddrives. Imagine following situation: "I want to watch a horror movie".
Have fun & good luck searching your collection for horror movies. This tool will help you filter your collection as you need it.

## Current Features

Currently this is a very early alpha version. You can pre-order it right now and get instant access... just kidding ;P

* Searching recursive for release folder names
* Collecting information for each release from [xrel.to](http://xrel.to) and [omdapi.com](http://omdbapi.com)
* Displaying all the shit

## Planned Features

* Show detailed information for each release
* Filter movies for genres, actors, names etc.
* Detect unnecessary double releases
* Start playing a movie in release folder
* Add support for non release conform folders (common movie names)
* Add multithreading support while collecting information from apis
* Add scheduler for collecting information from apis (because some apis have a request limit)
* Add support for more apis (could cause huge software architecture changes...)
* ...

## Filter Concept

* Syntax:

        category:search-term, category:search-term, [...]
* Example:

        genre:horror, actor:Anthony Perkins

### Categories

* genre
* actor
* title
* director
* writer
* imdb-id
* country
* release-group

### Special Categories

**released**

* Syntax:

        released:<operator><year>
* Example:

        released:=2015
* Example:

        released:<1990

**imdb**

* Syntax:

        imdb:<operator><number>
* Example:

        imdb:<7
* Example:

        imdb:>5.5

**metascore**

* Syntax:

        metascore:<operator><number>
* Example:

        metascore:>=67
* Example:

        metascore:<78


