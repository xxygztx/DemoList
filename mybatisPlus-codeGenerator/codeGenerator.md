#mybatis-plus的代码生生成器

我们只需要简单的根据注解根据自己的项目将自己的包名，表命，等进行简单配置就能快速生成
我们想要的代码

#生成代码后的配置
在使用SpringBoot的时候我们默认扫描xml文件在resources中，我们想要在java包下使用，
我们需要对**pom.xml文件和 application.yaml文件进行配置**

#pom.xml
````xml 
          <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <!--src/main/java目录下的**/*.xm都会被扫到-->
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
````
#application.yaml

````yaml 
mybatis-plus:
  mapper-locations: classpath:com/zfp/combat/mapper/xml/*.xml
  #配置日志
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
````

