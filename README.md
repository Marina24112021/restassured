<h1>A hosted REST-API ready to respond to your AJAX requests.</h1>
<img title="reqres" src="resources/images/reqres.png" width="850">  

Reqres is a real API
Reqres simulates real application scenarios. If you want to test a user authentication system, Reqres will respond to a successful login/register request with a token for you to identify a sample user, or with a 403 forbidden response to an unsuccessful login/registration attempt.

<i>A common front-end scenario that's easily forgotten is loading states, which can be easily simulated through Reqres by appending
```bash  
?delay=<a number of seconds> 
```
to any endpoint URL, which will delay the API response time. Animated loading GIFs & SVGs at the ready!