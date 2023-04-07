# TripPlanner: My Personal Project 

## Description 

The purpose of trip planner is to *make your vacation as easy, effortless, and organized as
possible*. It will help take the weight of planning your vacation off your shoulders through the use 
of a program that keeps track of your finances during the trip, lets you create an agenda for your
day-to-day activities, and allows you to rate and keep track of previous vacation spots so you 
can remember where you've visited. 

Some *features* of TripPlanner include:
- Including a budget/finance tracker to ensure you stay within your spending limit on a trip
- Create an agenda for your day to day vacation activities
- Make a list of places you want to visit during your vacation
- Allows you to see a quick overview of current/previous trips for easy reference 

## Target Audience 

This project is intended to be used by **anyone who wants to plan a trip**! Whether you're 
someone who loves to go with the flow or wants to have every detail planned out, the wide 
array of features allows for usability from virtually anyone who needs a little extra help 
with their next vacation. 

## Personal Interest

I feel interested in taking on this project because I oftentimes find it difficult to keep on 
top of things when on vacation. Options like Google Docs or Notion are great, but lack structure and are
oftentimes difficult to set up which can feel overwhelming at times. By creating an application that provides you 
a structured and consistent template that you can enter all your trip details in, I hope to make planning for myself and 
others just a little bit easier so people can simply enjoy their vacations rather than stressing about the logistics. 

## User Stories 

As a user, I want to be able to...
- create a trip to add to my list of trips 
- end my current trip allowing me to declare a new trip 
- have a budget tracker that shows my budget, how much I've spent so far, and the difference between the two
- edit my budget and amount spent so far, as well as trip details
- view all of my previous trips
- save my previous list of trips and current trip that I am on
- have the option to reload my saved previous trips and current trip

## Instruction for Grader 

- You can generate the first required action relating to X and Y's by pressing "Add Trip", and then going into "Edit Trip" and editing the budget. 
- You can generate the second required action relating to X and Y's by ending the trip you are currently on. 
- You can located my visual component on the home screen beside all the buttons 
- You can save the state of my application by pressing the save button on the home screen
- You can reload the state of my application by pressing the load button on the home screen 
- For viewing all the X's and Y's, press the "View Past Trips" button. I added some trips already in loaded data :) 

## Phase 4: Task 2
- Create a new trip, edit the budget twice, and end the trip to get this output.

New trip has been created. (Trip class)

Added 100.0 to spendings. (Budget class)

Added 10.0 to spendings.

Current trip moved to past trips. (Trips class)

## Phase 4: Task 3 

In terms of refactoring, I think a good choice would be to split up the PlannerUI class. Currently, I have 3-4 different
panels that are created from the PlannerUI class, each of which do very different things. In the interest of time, I chose to 
just put them all into 1 class, but I think the code would be much cleaner and cohesive if I were to separate the frames 
into their own classes and add them as fields for my main class. 

Additionally, moving the "end trip" functionality into the Trips class rather than having it in the UI would be another sensible
refactoring choice. If I were to add a Boolean field of "current" to the Trip class, it would allow me to keep all trip related functionality
to the model and overall improve the cohesion of the program. It would also be useful if I wanted to add functionality in the future such that users
can have an arbitrary number of current trips (since users can only be on 1 trip at a time right now). Overall these are the 2 main refactoring choices 
that I believe would improve my design :)