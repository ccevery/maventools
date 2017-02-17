# required configuration files
# directory.properties
# networkProxy.properties
# threadPool.properties

. setenv.sh
java  -classpath '.:../lib/*' merge.mavens.CleanMavenProject %1