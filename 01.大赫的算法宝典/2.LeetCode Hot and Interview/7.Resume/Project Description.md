# Projects

- OTT Pocket / PREKA - SaaS

  - ```
    Let me introduce the two projects that I involved in the past year. The first project is called OTT Pocket, which is a online shopping platform that sells giftcard, voucher and coupon. We have a lot of Key Account partners, for example Esso, CF, Ivanhoe, Oxford and so on. The over all trascation amount was over 30 million dollars. I mainly responsibly for developing and maintaining the Order Mangement System with Spring Boot and RESTful API. Various CRUD operations are written to cope with business logic regarding marketing requirements. I also lead the task of implementing RBAC system which is the core of a back-end management system, it supports dynamic menu mangement that each one of the roles can see a specific menu options and can only use specific button based on their roles. The permissions are stored in jwt token will be intercepted by springSecuirty's filter and put those permission into the "authorities" so that when calling the API, we can get if the user has the permission or not by traversing the SecurityContext. 
    ```

  - ```
    Another project is call PREKA, a SaaS platform that designed for issuing the giftcard, voucher and coupon for our merchants, so every one of our partners can have their own giftcard. By the way, PREKA stands for present card and prepaid card. I was assigned as the Tech lead of it since the company wants to train new people. Our tech stack of front-end are React and back-end uses distribute system SpringCloud Alibaba with Nacos as our service discovery, management, and config tool. We also integrated with some popular middleware including Redis, RabbitMQ and ElasticSearch. what's more, our project collaborate with AWS cognito and consider it as a authorization server the authenticate users with OAuth2 authorization code flow. All the permission related things are intercepted in Spring Gateway. It's a little bit different with the previous RBAC system as it doesn't have a SecurityContext that hold global authroties information. What we did was to put all the userContext into requestContext and let it go through the gateway. For the permission of each API, we add a authortiy filter in the Spring Gateway that by parsing the request URI, it check with the database if this role has the authorities to access this API (list all the roles that can access this api and see if current user has this role), to improve the processing time, we store those information in Redis.  
    ```



### 3.1.1. Authorization Code Flow Steps

The Authorization Code Flow goes through the following steps.

1. Client prepares an Authentication Request containing the desired request parameters.
2. Client sends the request to the Authorization Server.
3. Authorization Server Authenticates the End-User.
4. Authorization Server obtains End-User Consent/Authorization.
5. Authorization Server sends the End-User back to the Client with an Authorization Code.
6. Client requests a response using the Authorization Code at the Token Endpoint.
7. Client receives a response that contains an ID Token and Access Token in the response body.
8. Client validates the ID token and retrieves the End-User's Subject Identifier.

![An Introduction to OAuth 2 | DigitalOcean](https://assets.digitalocean.com/articles/oauth/abstract_flow.png)