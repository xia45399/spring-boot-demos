idea运行
    一 : Main方法运行:
        1 该模块必须为顶级项目，不能为模块
        2 pom package 为jar或war都行。
    二: maven 运行
        1 mvn clean spring-boot:run
        2 或者 mvn plugin->spring-boot-->spring-boot:run

打包运行
    必须为war
    mvn clean package
    java -jar .war