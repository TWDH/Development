# REST架构特征

- **以资源为基础** ：资源可以是一个图片、音乐、一个XML格式、HTML格式或者JSON格式等网络上的一个实体，除了一些二进制的资源外普通的文本资源更多以JSON为载体、面向用户的一组数据(通常从数据库中查询而得到)。
- **统一接口**: 对资源的操作包括获取、创建、修改和删除，这些操作正好对应HTTP协议提供的GET、POST、PUT和DELETE方法。换言而知，使用RESTful风格的接口但从接口上你可能只能定位其资源，但是无法知晓它具体进行了什么操作，需要具体了解其发生了什么操作动作要从其HTTP请求方法类型上进行判断。具体的HTTP方法和方法含义如下：
  - GET（SELECT）：从服务器取出资源（一项或多项）。
  - POST（CREATE）：在服务器新建一个资源。
  - PUT（UPDATE）：在服务器更新资源（客户端提供完整资源数据）。
  - PATCH（UPDATE）：在服务器更新资源（客户端提供需要修改的资源数据）。
  - DELETE（DELETE）：从服务器删除资源。
- ![img](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/v2-4c87f23be230fdf16dc99398781ebb1b_720w.jpg)
- **URI指向资源**：URI = Universal Resource Identifier 统一资源标志符，用来标识抽象或物理资源的一个紧凑字符串。URI包括 URL 和 URN，在这里更多时候可能代指URL (统一资源定位符)。RESTful 是面向资源的，每种资源可能由一个或多个 URI 对应，但一个 URI 只指向一种资源。
- **无状态**：**服务器不能保存客户端的信息**， 每一次从客户端发送的请求中，要包含所有必须的状态信息，会话信息由客户端保存， 服务器端根据这些状态信息来处理请求。 当客户端可以切换到一个新状态的时候发送请求信息， 当一个或者多个请求被发送之后, 客户端就处于一个状态变迁过程中。 每一个应用的状态描述可以被客户端用来初始化下一次的状态变迁。

## REST架构限制条件

- **客户端-服务端（Client-Server）**: 这个更专注客户端和服务端的分离，服务端独立可更好服务于前端、安卓、IOS等客户端设备。
- **无状态（Stateless）**：服务端不保存客户端状态，客户端保存状态信息每次请求携带状态信息。
- **可缓存性（Cacheability）** ：服务端需回复是否可以缓存以让客户端甄别是否缓存提高效率。
- **统一接口（Uniform Interface）**：通过一定原则设计接口降低耦合，简化系统架构，这是RESTful设计的基本出发点。当然这个内容除了上述特点提到部分具体内容比较多详细了解可以参考这篇[REST论文内容](https://link.zhihu.com/?target=https%3A//www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm)。
- **分层系统（Layered System）**：客户端无法直接知道连接的到终端还是中间设备，分层允许你灵活的部署服务端项目。
- **按需代码（Code-On-Demand，可选）**：按需代码允许我们灵活的发送一些看似特殊的代码给客户端例如JavaScript代码。



## RESTful API设计规范

### URL设计规范

- URL为统一资源定位器 ,接口属于服务端资源，首先要通过URL这个定位到资源才能去访问，而通常一个完整的URL组成由以下几个部分构成：

  - ```
    URI = scheme "://" host  ":"  port "/" path [ "?" query ][ "#" fragment ]
    ```

  - scheme: 指底层用的协议，如http、https、ftp

  - host: 服务器的IP地址或者域名

  - port: 端口，http默认为80端口

  - path: 访问资源的路径，就是各种web 框架中定义的route路由

  - query: 查询字符串，为发送给服务器的参数，在这里更多发送数据分页、排序等参数。

  - fragment: 锚点，定位到页面的资源

- 一个RESTful API的path组成如下：

  - ```
    /{version}/{resources}/{resource_id}
    ```

  - version：API版本号，有些版本号放置在头信息中也可以，通过控制版本号有利于应用迭代。

  - resources：资源，RESTful API推荐用小写英文单词的复数形式。

  - resource_id：资源的id，访问或操作该资源。

- 有时候可能资源级别较大，其下还可细分很多子资源也可以灵活设计URL的path，例如：

  - ```
    /{version}/{resources}/{resource_id}/{subresources}/{subresource_id}
    ```

- 此外，有时可能增删改查无法满足业务要求，可以在URL末尾加上action，例如

  - ```
    /{version}/{resources}/{resource_id}/action
    ```

- RESTful API的URL具体设计的规范如下：

  1. 不用大写字母，所有单词使用英文且小写。
  2. 连字符用中杠`"-"`而不用下杠`"_"`
  3. 正确使用 `"/"`表示层级关系,URL的层级不要过深，并且越靠前的层级应该相对越稳定
  4. 结尾不要包含正斜杠分隔符`"/"`
  5. URL中**不出现动词**，用请求方式表示动作
  6. 资源表示用**复数**不要用单数
  7. 不要使用文件扩展名

### HTTP动词

- 在RESTful API中，不同的HTTP请求方法有各自的含义

  - ```
    GET /collection：从服务器查询资源的列表（数组）
    GET /collection/resource：从服务器查询单个资源
    POST /collection：在服务器创建新的资源
    PUT /collection/resource：更新服务器资源
    DELETE /collection/resource：从服务器删除资源
    ```

  - RESTful风格的API则要求在URL上都以名词的方式出现，从几种请求方式上就可以看出想要进行的操作，这点与非RESTful风格的API形成鲜明对比。

- **安全性和幂等性**

  - 读的为安全的，写的操作为非安全的
  - **操作一次和操作多次的最终效果相同**，客户端重复调用也只返回同一个结果
  - ![img](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/v2-3d59c2beffb51d40d7c05fcf83ed372c_720w.jpg)

### 状态码和返回数据

- 服务器响应时, 包含**状态码**和**返回数据**两个部分。

- > ### 200
  >
  > 200 `OK - [GET]`：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
  > 201 `CREATED - [POST/PUT/PATCH]`：用户新建或修改数据成功。
  > 202 `Accepted - [*]`：表示一个请求已经进入后台排队（异步任务）
  > 204 `NO CONTENT - [DELETE]`：用户删除数据成功。
  >
  > ### 400
  >
  > 400 `INVALID REQUEST - [POST/PUT/PATCH]`：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
  > 401 `Unauthorized - [*]`：表示用户没有认证（令牌、用户名、密码错误）。
  > 403 `Forbidden - [*]` 表示用户得到授权（与401错误相对），但是访问是被禁止的。
  > 404 `NOT FOUND - [*]`：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
  > 406 `Not Acceptable - [GET]`：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
  > 410 `Gone -[GET]`：用户请求的资源被永久删除，且不会再得到的。
  > 422 `Unprocesable entity - [POST/PUT/PATCH]` 当创建一个对象时，发生一个验证错误。
  >
  > ### 500
  >
  > 500 `INTERNAL SERVER ERROR - [*]`：服务器发生错误，用户将无法判断发出的请求是否成功。

### 案例

- ![img](https://raw.githubusercontent.com/TWDH/Leetcode-From-Zero/pictures/img/v2-46b880b82c5cec18708506f7a34547b4_720w.jpg)

  > **GET请求用来获取资源**：GET请求会向数据库发索取数据的请求，从而来获取资源，该请求就像数据库的select操作一样，只是用来查询数据，不会影响资源的内容。无论进行多少次操作，结果都是一样的。
  >
  > 并且GET请求会把请求的参数附加在URL后面，但是不同的浏览器对其有不同的大小长度限制。
  >
  > 在本案例中，我们设计两个GET请求的API。
  > `GET /dogs` ：用来返回dog资源的列表。
  > `GET /dogs/{dogid}` ：用来查询此id的单个dog资源。
  >
  > **POST请求用来新增一个资源** : POST请求向服务器发送数据，但是该请求会改变数据的内容(新添)，就像数据库的`insert`操作一样，会创建新的内容。且POST请求的请求参数都是请求体中，其大小是没有限制的。
  >
  > 在本案例中，我们设计以下POST请求的API。
  > `POST /dogs` ：服务端新增一个dog资源。
  >
  > **PUT请求用来更新资源**，PUT请求是向服务器端发送数据的， 与POST请求不同的是，**PUT请求侧重于数据的修改** ,就像数据库中update一样，而POST请求侧重于数据的增加。
  >
  > 在本案例中，我们设计以下POST请求的API。
  > `PUT /dogs/{dogid}` ：用来更新此id的单个dog资源。
  >
  > **DELETE 请求用来删除资源**，DELETE请求用途和它字面意思一致，用来删除资源。和数据库中delete相对应。

- **form-data**： 就是form表单中的multipart/form-data，会将表单数据处理为一条信息，用特定标签符将一条条信息分割开，而这个文件类型通常用来上传二进制文件。

- **x-www-form-urlencoded**：就是application/x-www-form-urlencoded，是form表单默认的encType，form表单会将表单内的数据转换为键值对，这种格式不能上传文件。

  - 因为GET请求查询参数在URL上，其他类型请求使用x-www-form-urlencoded方式向后端传值。

- **raw**：可以上传任意格式的文本，可以上传Text，JSON，XML等，但目前大部分还是上传JSON格式数据。当后端需要接收JSON格式数据处理的时候，可以采用这种格式来测试。



# Get & Put

- [GET 和 POST请求的本质区别是什么？](https://mp.weixin.qq.com/s/be6H3gPEeEGkuaCEIfr0vQ)

- > - GET在浏览器回退时是无害的，而POST会再次提交请求。
  > - GET产生的URL地址可以被Bookmark，而POST不可以。
  > - GET请求会被浏览器主动cache，而POST不会，除非手动设置。
  > - GET请求只能进行url编码，而POST支持多种编码方式。
  > - GET请求参数会被完整保留在浏览器历史记录里，而POST中的参数不会被保留。
  > - GET请求在URL中传送的参数是有长度限制的，而POST么有。
  > - 对参数的数据类型，GET只接受ASCII字符，而POST没有限制。
  > - GET比POST更不安全，因为参数直接暴露在URL上，所以不能用来传递敏感信息。
  > - GET参数通过URL传递，POST放在Request body中。
  > - GET 发送2次，POST发送一次
  > - 浏览器通常都会限制url长度在2K个字节，而（大多数）服务器最多处理64K大小的url





































































