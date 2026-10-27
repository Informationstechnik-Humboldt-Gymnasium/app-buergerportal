@echo off
setlocal
set APP_HOME=%~dp0
set CLASSPATH=%APP_HOME%gradle\wrapper\gradle-wrapper.jar
set DEFAULT_JVM_OPTS=-Xmx2048m -Xms256m
java %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% -cp "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
endlocal
