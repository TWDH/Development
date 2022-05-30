# LightSpeed

1. Self introduction
   
   - ```
      My name is He Zhu, I graduated from University of Ottawa majoring in electrical and computer engineering. I have work with OTT Financial Group for 1 year as a software developer. 
      
      I mainly dedicated myself in back-end java development, I have developed bunch of new features in one of our mobile project "OTT Pocket" which is a online shopping plateform that sells giftcard, voucher and coupon. What's more, back into few month before, I was assigned to be the tech lead, leading a small team to develop a new SaaS platform called PREKA, it stands for present card and prepaid card. The PREKA is designed for issuing business giftcard / voucher / coupon for our partner merchants, which could be bought and redeemed in OTT pocket which make it a full cycle of business. I mainly responsible for developing the backend server-side application, our tech stack includes SpringBoot, SpringCloud that uses the methodology of micro-services and distribute system. My daily works are creating APIs and test it with front-end developers, I also help the team to set up the cloud server AWS EC2 for deployment. 
      ```
   
2. **What is your approach to debugging?** . Eg - The payment is being credited to the wrong merchant account. How do you debug this problem in production?*

   1. Take the reported steps (logs) to **reproduce the bug in dev**
   2. Write **unit test** to reproduce the bug
     - My go to approach is the second one. Let's assume there is a payment endpoint, unit test will reveal bugs and likely causes. A cause might be a wrong environment variable or even hardcoded value. Unit test is the best way to reproduce bugs IMO because you are testing outcomes against expectations and tests fails when they dont match.
     - Let's assume the cause of the bug is a wrong environment variable, next thing to do is to **confirm the variable in prod** to ascertain the correctness. That's an easy fix that does not require any coding.
     - If the cause of the bug is something else like hard coded value, a **hotfix or bugfix** should be pushed and deployed first to the test environment, then to prod afterwards if all is well in the test environment.

3. ***How do you handle website slowness without rewriting in a different language or going for different architecture?***

   - **Vertical and horizontal scaling.** 
     - **Vertical** scaling involves increasing the machine resources by **increasing the RAM size or CPU**. 
     - **Horizontal** scaling on the other hand involves **increasing in the number of machines.** 
     - Think of VS as making a man stronger (adding more muscles) and HS as increasing the number of average men. There's only how far you get increase resources but the number of machines with the same compute capabilities you can add is infinite. Both approaches improves performance of a system without refactoring, rewriting/building or changing architecture.

4. *When do you do unit and end-to-end testing?*

   - **Unit test** is done **by developers in dev**. Involves testing individual components in isolation. This often leads to code thats decoupled, easy to scale and maintain
   - **E2E** is when all the different components are tested at once. Done usually at the **end of the SDLC （ Software Development Life Cycle）**

5. ***[What to consider when scaling up system? ](https://taazaa.com/key-factors-to-consider-when-scaling-your-software-application/)***

   1. Scalability Can Cost You
   2. Scaling **Shared Resources** Is Critical (Database, MQ, Micro-services ...)
      1. server 多了，但是 database没有变
      2. protect against cascade failures by including resilient patterns (circuit breakers, bulkheads, fallbacks, retries, and timeouts)
   3.  Scaling The **Data Tier** Is Painful
      1. increases the load on your shared transactional databases
      2. a no-SQL, schema less database, a distributed database, or a managed cloud-based database
      3. using caching as a way to reduce the load on your database
   4. **Monitoring** Is Essential.
      1. setting up alerts for when memory or disk space runs low, remote calls fail, or other infrastructure problems arise.

6. **[*How to manage distributed database?*](https://fauna.com/blog/the-why-and-how-of-distributed-databases)**

   1. Types of distributed databases
      1. homogeneous: consistency, system attributes such as physical resources, operating system, and DBMS are uniform across all the sites.
      2. heterogeneous: allow different sites to have different attributes. The sites might not be aware of each other, and each site might use a different communication protocol, requiring additional translation of data between sites.
   2. Data storage methods for distributed databases
      1. Fragmentation 分割
         1. vertical 竖着切（只关心某列）
         2. horizontal 横着切
      2. replication 复制
         1. one of the sites as the “primary site”, and periodically syncing the other sites with the primary site. 
         2. *synchronous* and *asynchronous*
         3. [分布式系统数据一致性解决方案](https://blog.csdn.net/WXF_Sir/article/details/123264049) (CAP, 两阶段提交协议:准备阶段（投票反馈阶段）和 提交阶段（执行阶段） )
   3. prons / cons
      1. Improved performance
      2. Enabling massive scalability
      3. Delivering round-the-clock reliability

7. ***Technical questions related to integration / microservices / API / software engineering.***

8. ***What are the core differences between REST APIs and GraphQL?***

   1. GraphQL
      1. 优点：
         1. 前端自己决定需要那些数据
         2. 一次请求，获得多个 API 的数据
         3. 访问集中提供的 API
         4. 后端改变不影响前端的数据请求
   2. Rest API
      1. 问题：
         1. 后端返回数据**过多**，前端只用到其中一部分 （response from backend is too heavey）
         2. 后端返回的数据**过少**，前端需要发送多个请求 (not enough data has been responded)
         3. 后端 API 分散，不方便统一管理 (APIs are separate, not easy to manage them)

9. ***Describe different micro-frontends implementation approaches and their advantages/disadvantages***

   - The idea behind Micro Frontends is to think about a website or web app as **a composition of features** which are owned by **independent teams**. Each team has a **distinct area of business** or **mission** it cares about and specialises in. A team is **cross functional** and develops its features **end-to-end**, from database to user interface.
   - Advantages
     - Micro-frontend architectures may be simpler, and thus easier to reason about and manage.
     - Independent development teams can collaborate on a front-end app more easily.
     - They can provide a means for migrating from an “old” app by having a “new” app running side by side with it.
     - **Be Technology Agnostic**
     - **Isolate Team Code**
     - **Establish Team Prefixes**
     - **Favor Native Browser Features over Custom APIs** 优先使用本地浏览器功能，而不是自定义api
     - **Build a Resilient Site** 建立一个有弹性的网站
   - [Micro Frontends](https://micro-frontends.org/)

10. Domain Driven Design

    - Software entropy (rots)
    - focus on business, reconciling the technical and non-technical forces that collide in a software project
    - Breaking down the model into **Bounded Contexts (business domain, teams, and code)** that interact with each other 
    - ![image-20220527084333629](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220527084333629.png)
    - Nacos: dynamic service discovery, service configuration, service metadata and traffic management.
    - sentinel: circuit break, system adaptive protection, Real-time monitoring
    - sleuth: monitor







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


## GraphQL

[什么是 GraphQL？](https://www.zhihu.com/question/264629587)

- GraphQL
  - GraphQL 最大的优势是查询 **图状数据**
  - ![image-20220524110145765](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524110145765.png)
  - 这是一个超级复杂的树状结构，如果我们用常见的 RESTful API 涉及，每个 API 负责请求一种类型的对象，例如用户是一个类型，帖子是另一个类型，那就需要非常多个请求才能把这个页面所需的所有数据拿回来。而且这些请求直接还存在依赖关系，不能平行地发多个请求，例如说在获得帖子数据之前，无法请求评论数据；在获得评论数据之后，才能开始请求评论作者数据。
  - 如何解决这种问题？一个简单粗暴的办法是专门写一个 RESTful API，请求上述树状复杂数据。但很快新问题就会出现。现在 Facebook 想要做一个新的产品，例如说是宠物，然后要在我的页面上显示我的宠物信息，那这个 RESTful API 的实现就要跟着改。
  - GraphQL 能够很好地解决这个问题，但前提是数据已经以图的数据结构进行保存。例如上面说到的用户、帖子、评论是顶点，而用户跟用户发过的帖子存在边的关系，帖子跟帖子评论存在一对多的边，评论跟评论作者存在一对一的边。这时候如果新产品引入了新的对象类型（也就是顶点类型）和新的边类型，那没有关系。在查询数据时用 GraphQL 描述一下要查询的这些边和顶点就行，不需要去改 API 实现。

### 问题

- https://www.zhihu.com/zvideo/1464339652362985472?playTime=41.0

- 后端返回数据**过多**，前端只用到其中一部分
  - ![image-20220524112444418](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524112444418.png)
- 后端返回的数据**过少**，前端需要发送多个请求
  - ![image-20220524112430906](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524112430906.png)
- 后端 API 分散，不方便统一管理
  - ![image-20220524112516991](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524112516991.png)

### 方案

- 新的 API 查询语言
- 前端自己决定需要那些数据
  - ![image-20220524112711340](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524112711340.png)
- 一次请求，获得多个 API 的数据
  - ![image-20220524112231806](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524112231806.png)
- 访问集中提供的 API
  - ![image-20220524112618471](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524112618471.png)
- 后端改变不影响前端的数据请求
  - ![image-20220524112655191](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524112655191.png)
- GraphQL 来源
  - DB 、 API、文件系统
  - 通过一个类型，找到跟他关联的其他类型
    - ![image-20220524112915697](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/image-20220524112915697.png)

## GraphQL VS Rest API

![img](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/v2-6b5cd9412f5e16ae89444bb09599cc82_720w.jpg)

### 优势1：数据更少

这一点很好理解，也是 GraphQL 最大的亮点。在数据请求过程中，用户使用的数据从数据库某处取出，如果您直接使用 SQL 操作数据库，那么您知道只请求所需的几个字段而不是所有字段都拿出来会更有效、更快速，同时造成更少的资源浪费。

在 REST 请求中，几乎不可能仅仅指定所需的几个字段。结果就是您通常会拿到冗余数据。如果遇到需要一次请求多个数据、查询多个表的情况，那将面临的是指数级增长的麻烦。

比如一种常见的情况，先请求一个 `userList` 列表，然后在请求每一个 `user` 的某个字段，比如 `user.name`，一般获取 `user` 接口设计为获取个人的全部数据，那么对于前端而言，除 `name` 之外的数据无处可用，造成了浪费。



### 优势2：合并请求

GraphQL 的另一个显着优势是能够合并多个请求。举一个 REST 中常见的情况：

1. 我们需要展示一个小组。我们称之为`/team_by_id/:team_id`。
2. 该小组有一个用户 ID 列表。我们需要获取每个用户信息，所以为每个 ID 分别调用`/user/:user_id`, 每个用户调一次。
3. 但是我们还想显示该用户所在的其他小组情况。现在，我们再次调用`/team_by_user_id/:user_id`，每个团队每个用户调一次。

总计一下：

```text
/team/:id 1 
/user/:id n（n: 小组中的用户数）
/team/:id n×t（t: 每位用户的小组数）
```

如果这是一个由 30 个用户组成的团队，并且每个用户都是 3 个团队，那么我们只获取一次 REST 完整数据需要请求执行 1 + 30 + 30*3 = 121 次。

当然，您可以找到优化 REST 方法，但是必须手动进行，各种数据缓存来减少请求次数，但是同时也引入了数据缓存和更新的新问题，复杂度维护成本直线提升。

而它们如果在 GraphQL 中执行。可以按照以下方法将所有这些 REST 请求合并到一个 GraphQL 查询中：

```text
query TEAM_USERS_DATA {
  team(*id*: $team_id) {
    users {
      userName
      avatar      
      teams {
        teamName
      }
    }
  }
}
```

使用 GraphQL 就可以在单个请求中完成相同的操作。不仅可以提高性能，还仅通过一次调用就完成了原来的递归或循环实现的个不同的查询，降低代码复杂性。



### 优势3：订阅

GraphQL 的另一个好处是“订阅”——进行查询并自动更新的功能。通常，这是使用 [WebSockets](https://link.zhihu.com/?target=https%3A//developer.mozilla.org/en-US/docs/Web/API/WebSockets_API) 在 GraphQL 服务器端实现的。假设我们要使用 GraphQL 创建一个聊天应用。我们可能会执行以下操作：

```text
subscription MESSAGES() {
  messagesSubscribe(last: 200) {
    msg    author {
      avatar
      userName
    }
  }
}
```

在应用中，`messagesSubscribe` 将是一系列消息，每次发送新消息时都会自动更新。否则的话，我们就得轮训发送请求，短时间内产生数百个请求。

对于订阅，打开连接后唯一传输的数据是发送或接收消息的时间。要充分利用订阅，您必须使用支持该订阅的客户端，大多数流行的客户端（例如[Apollo Client](https://link.zhihu.com/?target=https%3A//www.apollographql.com/docs/react/)）都是内置的。

有关 GraphQL 订阅可以看这个有趣的示例，[Eve Porcello](https://link.zhihu.com/?target=https%3A//twitter.com/eveporcello) 在 React Rally 2018 上的演讲，其中五名与会者使用 Subscribe 创建了即兴音乐。



### 局限1：HTTP 缓存

一个非常明显的问题，因为无法使用固定规范的 HTTP 请求，也就无法把数据缓存在 HTTP 层，即使做再多的 GraphQL 服务端缓存，也无法解决网络级别的通信量拥堵问题，目前社区提供了一些客户端级别的缓存方案来解决一部分问题，比如使用 [Apollo Client](https://link.zhihu.com/?target=http%3A//www.apollographql.com/client) 、[Relay](https://link.zhihu.com/?target=https%3A//facebook.github.io/relay/)，但是这些当然也不是免费的，需要开发者持续投入精力，增加了不少成本。

### 局限2：HTTP Status

正常情况下 GraphQL 只会返回 `Status Code 200`，无论当前数据请求是成功或失败，这样传统方法的通讯状态判断和逻辑就无法使用，虽然开发者可以自定义一套错误处理逻辑，但也增加了复杂度。

### 局限3：复杂度陡升

面对一个传统有效的解决方案（REST），除非有非做不可的痛点，否则我们一般不会对他下手。在现实中这也体现了出来，GraphQL 虽然已经面世了几年时间，更新迭代几版逐渐趋于稳定状态，但是市场接受度只能说不温不火。一方面 GraphQL 方式涉及了客户端和服务端的传统开发模式，代码入侵较大；另一方面，它对前后端开发人员都有一定的门槛，各种 scheme、query、type、resolver 又增加了复杂度，在情况千差万别的业务场景下，很难说对于整体性能是提升还是下降；同时由于客户端需要有 GraphQL Client 才能使用 API，这也导致 API 的复用和扩展有所受限。

### 结论

REST 和 GraphQL 都是基于 HTTP 的数据传输解决方案，GraphQL 可以**显著的节省网络传输资源**，在带宽紧张的环境中（例如移动端），这将发挥巨大的作用。尽管 GraphQL 相比 REST 有很多显著的优点和升级，但在真实场景中，它并不一定是最适合你的实现。总结来说，如果你希望做的应用追求简单而敏捷，且没有什么特殊考量，那就没什么必要使用 GraphQL，**REST 可靠、经济、不易出错**；反而言之，如果应用的关键点在于组织复杂数据逻辑，请求存在较多 Over-fetching、Under-fetching 的情况，或者对于网络环境敏感，那么 GraphQL 会是一发银弹。