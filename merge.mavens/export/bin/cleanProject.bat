@echo off

rem required configuration files
rem directory.properties
rem networkProxy.properties
rem threadPool.properties

@echo off
call "setenv.bat"
%_RUN_JAVA%  -classpath ".;../lib/*" merge.mavens.CleanMavenProject %1