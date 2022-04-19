- Cognito Documentation
  - [Authorization](https://docs.aws.amazon.com/zh_cn/cognito/latest/developerguide/authorization-endpoint.html)
  - [Token](https://docs.aws.amazon.com/zh_cn/cognito/latest/developerguide/token-endpoint.html)
  - [Login](https://docs.aws.amazon.com/zh_cn/cognito/latest/developerguide/login-endpoint.html)

1. 获取 Authorization Code

   - ```
     //
     https://preka.auth.ca-central-1.amazoncognito.com/oauth2/authorize?response_type=code&client_id=1f7romq14disc5kn1h1drokln6&redirect_uri=https://preka.auth.ca-central-1.amazoncognito.com
     
     // userpool2
     https://saasbusiness7be1bfca-7be1bfca-dev.auth.ca-central-1.amazoncognito.com/login?response_type=code&client_id=5hp5srb3pmrjlpgse9jqgj8upj&redirect_uri=http://localhost:3000/welcome/
     ```
   
2. 根据 Authorization Code 获取 token

   - ```
     https://preka.auth.ca-central-1.amazoncognito.com/oauth2/token
     ```

   - ![image-20220318172924004](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220318172924004.png)

   - ![image-20220318173045549](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220318173045549.png)



```
https://preka.auth.ca-central-1.amazoncognito.com/oauth2/token&
                       Content-Type='application/x-www-form-urlencoded'&
                       Authorization=Basic MWY3cm9tcTE0ZGlzYzVrbjFoMWRyb2tsbjY6MTlwZWlrdGVzbzcydWFydjZwMjFhampoODByMm9sODFrcnU3cmhoZHVlOG1oMnMzMHMxbA==
                       grant_type=authorization_code&
                       client_id=1f7romq14disc5kn1h1drokln6&
                       code=fe98c132-72b6-4dc5-9dee-d005119104a5&
                       redirect_uri=https://preka.auth.ca-central-1.amazoncognito.com
                       
                       
                       
https://preka.auth.ca-central-1.amazoncognito.com

https://preka.auth.ca-central-1.amazoncognito.com
```

