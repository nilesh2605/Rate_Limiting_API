# Rate_Limiting_API
API to limit the no. of hits per hour per user.

1)This API imposes a restriction on the number of calls it can recieve per user in a given time frame. <br />
2)In this case the time frame taken is 1 hour. <br />
3)The rate limit is a configurable parameter (X) defined in the application.properties file with the value taken to be 100 for this API. <br />
4)The endpoint url is like /users/{userId} where userId is passed as a path parameter and is used to identify different users. <br />
5)After every hit, the user is displayed the number of remaining attempts he has and the time by which those attempts are valid. <br />
6)The status code is 200(OK) for the above case and the message displayed to user is like "Welcome! No. of attempts left 99 till 2021-05-10 02:46:18:183". <br />
7)If the user hits more than X number of times in 1 hour, he is displayed the relevant error code along with the message which also tells the number of minutes till which he can retry. <br />
8)The error code is 429(TOO MANY REQUESTS) for the above case and the message displayed to user is like "No. of attempts exhausted. Please try after 15 minutes." <br />
9)After the given time has elapsed, the user can again hit the API for X number of times. <br />

