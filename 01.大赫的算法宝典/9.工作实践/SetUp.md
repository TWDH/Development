# 1. dev, test, prod

- ```xml
  <!--配置环境的profile-->
  <profiles>
      <profile>
          <id>dev</id>
          <properties>
              <!--使用${environment}获取值-->
              <environment>dev</environment>
          </properties>
      </profile>
      <profile>
          <id>test</id>
          <properties>
              <environment>test</environment>
          </properties>
      </profile>
      <profile>
          <id>prod</id>
          <properties>
              <environment>prod</environment>
          </properties>
      </profile>
  </profiles>
  <build>
      <!--suppress UnresolvedMavenProperty -->
      <finalName>hhInterface-xxl-${environment}</finalName>
      <plugins>
          <plugin>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-maven-plugin</artifactId>
              <configuration>
                  <includeSystemScope>true</includeSystemScope>
              </configuration>
          </plugin>
      </plugins>
      <resources>
          <!--排除环境配置文件-->
          <resource>
              <directory>src/main/resources</directory>
              <excludes>
                  <exclude>application-*.yml</exclude>
                  <exclude>application.yml</exclude>
              </excludes>
          </resource>
  
          <resource>
              <directory>src/main/resources</directory>
              <filtering>true</filtering>
              <!-- 打包时包含的文件 -->
              <includes>
                  <!--suppress UnresolvedMavenProperty -->
                  <include>application-${environment}.yml</include>
                  <include>application.yml</include>
              </includes>
          </resource>
      </resources>
  </build>
  
  <pluginRepositories>
      <pluginRepository>
          <id>spring-snapshots</id>
          <url>http://repo.spring.io/libs-snapshot</url>
      </pluginRepository>
  </pluginRepositories>
  ```

- 