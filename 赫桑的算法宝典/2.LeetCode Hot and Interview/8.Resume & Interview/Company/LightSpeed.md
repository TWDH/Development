# LightSpeed

- ```
  1. What are the popular technical opinions you don’t agree with and why?
  2. What is your approach to debugging?. Eg - The payment is being credited to the wrong merchant account. How do you debug this problem in production?
  3. What is something that you didn’t agree with about the current company's recent technology decision? How did you handle it?
  4. By showing a code example, they asked the following questions:- what does the function do?. Can it be implemented in a better way?. Where is the ideal place to add this function?. What can be more documented and how do you test this particular function.
  5. How do you handle website slowness without rewriting in a different language or going for different architecture?
  6. When do you do unit and end-to-end testing?
  
  ------------------------------------------------------------------------------
  
  1. What are the popular technical opinions you don’t agree with and why?
  
  Ans:
  I'll share just one. It's the idea that a programming language is better than another. Or that the choice of programming language has a direct impact on its reliability or usability. While that may be true in some instances, what is truer and of higher effect is the problem solving skills of the developers themselves. After all, the programming language is simply a tool, how the tool is used is not dependent on the tool itself but the person that wields the tool.
  
  2. What is your approach to debugging?. Eg - The payment is being credited to the wrong merchant account. How do you debug this problem in production?
  
  Ans:
  There are 2 ways I'd go about it
  - Take the reported steps to reproduce the bug in dev
  - Write unit test to reproduce the bug
  
  My go to approach is the second one. Let's assume there is a payment endpoint, unit test will reveal bugs and likely causes. A cause might be a wrong environment variable or even hardcoded value. Unit test is the best way to reproduce bugs IMO because you are testing outcomes against expectations and tests fails when they dont match.
  
  Let's assume the cause of the bug is a wrong environment variable, next thing to do is to confirm the variable in prod to ascertain the correctness. That's an easy fix that does not require any coding.
  
  If the cause of the bug is something else like hard coded value, a hotfix or bugfix should be pushed and deployed first to the test environment, then to prod afterwards if all is well in the test environment.
  
  Skip question 3
  
  5. How do you handle website slowness without rewriting in a different language or going for different architecture?
  
  Ans:
  Vertical and horizontal scaling. Vertical scaling involves increasing the machine resources by increasing the RAM size or CPU. Horizontal scaling on the other hand involves increasing in the number of machines. Think of VS as making a man stronger (adding more muscles) and HS as increasing the number of average men. There's only how far you get increase resources but the number of machines with the same compute capabilities you can add is infinite. Both approaches improves performance of a system without refactoring, rewriting/building or changing architecture.
  
  6. When do you do unit and end-to-end testing?
  
  Ans:
  - Unit test is done by developers in dev. Involves testing individual components in isolation. This often leads to code thats decoupled, easy to scale and maintain
  - E2E is when all the different components are tested at once. Done usually at the end of the SDLC
  ```

- ```
  - From the past development experience describe some achievement you are proud of
  
  - From the past experience describe some situation which you would consider a failure (rephrased as understood by me)
  
  - Describe a technical challenge you faced when trying to scale
  ```

- ```
  Java OOP design and Java 8 skills
  ```

- ```
  What to consider when scaling up system?
  
  How to manage distributed database?
  
  Technical questions related to integration/microservices/API/software engineering.
   
  What are the core differences between REST APIs and GraphQL?
  
  Describe different micro-frontends implementation approaches and their advantages/disadvantages
  ```

- 