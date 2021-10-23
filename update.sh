#!/opt/procursus/bin/fish

mvn clean install
mv target/me.sillysock.SillyCore-1.0-SNAPSHOT.jar Server/Server/plugins/
