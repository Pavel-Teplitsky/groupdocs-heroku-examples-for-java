This quickstart will get you going with the GroupDocs Java sample app on Heroku.

## Prerequisites

* A Heroku user account with [Heroku toolbelt](https://toolbelt.heroku.com/) installed on the local workstation.
* Add GroupDocs addon to your app.

## Add GroupDocs Add-on to your app

	:::term
    $ heroku addons:add groupdocs
    -----> Adding groupdocs to sharp-mountain-4006... done

## Clone the sample repository to your local folder

	:::term
	$ git clone git://github.com/groupdocs/groupdocs-heroku-examples-for-java.git

## Store Your App in Git

    :::term
	$ cd groupdocs-heroku-examples-for-java
    $ git init
    $ git add .
    $ git commit -m "init"

## Deploy to Heroku/Cedar

Create the app on the Cedar stack:

    :::term
    $ heroku create --stack cedar
    Creating stark-sword-399... done, stack is cedar
    http://stark-sword-399.herokuapp.com/ | git@heroku.com:stark-sword-399.git
    Git remote heroku added

Deploy your code:

    :::term
    $ git push heroku master

## Live running app
This sample app is also running live on Heroku. To view and try, please open [http://groupdocs-heroku-java-examples.herokuapp.com/](http://groupdocs-heroku-java-examples.herokuapp.com/).

## To deploy project locally follow this instructions:

* Download and install JDK 6, Maven.
* Open console and run: `git clone https://github.com/liosha2007/groupdocs-heroku-examples-for-java`
* cd to project directory and run: `mvn clean install jetty:run`
* Now open your browser and go to http://localhost:8080/
