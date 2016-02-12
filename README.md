# Instagram Simple Client

# Pre-work - Instagram Simple Client

Instagram Simple Client is an android app that show the list of the most popular Instagram Photos. It uses two external libraries:  android-async-http and  Picasso. Using Gradle to add these libraries.

Submitted by: Luiza Carneiro

Time spent: Not sure

## User Stories

The following user stories must be completed:

* [x] User can scroll through current popular photos from Instagram (5 points)
* [x] For each photo displayed, user can see the following details:
Graphic, Caption, Username (2 points)
* [x] (Optional) relative timestamp, like count, user profile image (3 points)

The following advanced user stories are optional but recommended:

* [] Advanced: Add pull-to-refresh for popular stream with SwipeRefreshLayout (1 point)
* [] Advanced: Show latest comment for each photo (bonus: show last 2 comments) (1 point + 1 bonus)
* [x Advanced: Display each user profile image using a RoundedImageView (2 points)
* [] Advanced: Display a nice default placeholder graphic for each image during loading (read more about Picasso) (1 point)
* [x] Advanced: Improve the user interface through styling and coloring (1 to 5 points depending on the difficulty of UI improvements)
* [] Bonus: Allow user to view all comments for an image within a separate activity or dialog fragment (3 points)
* [] Bonus: Allow video posts to be played in full-screen using the VideoView (2 points)
* [] Bonus: Apply the popular Butterknife annotation library to reduce view boilerplate. (1 point).

Extras added by me:
* [x] Horizontal line that visually shows the end of one instagram photo.
* [x] Changed colors of user name and caption.

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

[Video Walkthrough](http://imgur.com/s0YID59)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes
* The App crashed several times when changes were done. Usually deleting the app from emulator and reloading worked. Sometimes it did not. The problem is usually when reading the JSON information (I got some nulls in the caption for some reason and had to put some "defensive" code)
* Had several issues with the App while debugging. The only way to get around (sometimes) was to delete the app and run again. It can be because of the OS that I use... Who knows. The problem happened only at debugging time (the program crashed if I did not go step by step)
.

## License

    Copyright [2016] [Luiza Carneiro]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
