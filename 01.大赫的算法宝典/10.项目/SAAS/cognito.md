- Cognito Documentation
  - [Authorization](https://docs.aws.amazon.com/zh_cn/cognito/latest/developerguide/authorization-endpoint.html)
  - [Token](https://docs.aws.amazon.com/zh_cn/cognito/latest/developerguide/token-endpoint.html)
  - [Login](https://docs.aws.amazon.com/zh_cn/cognito/latest/developerguide/login-endpoint.html)

1. 获取 Authorization Code

   - ```
     https://preka.auth.ca-central-1.amazoncognito.com/oauth2/authorize?response_type=code&client_id=1f7romq14disc5kn1h1drokln6&redirect_uri=https://preka.auth.ca-central-1.amazoncognito.com
     
     ```

2. 根据 Authorization Code 获取 token

   - ```
     https://preka.auth.ca-central-1.amazoncognito.com/oauth2/token
     ```

   - ![image-20220318172924004](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220318172924004.png)

   - ![image-20220318173045549](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220318173045549.png)

