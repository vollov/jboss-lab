mvn archetype:create -DgroupId=org.demo.dit -DartifactId=issue-track -DarchetypeArtifactId=maven-archetype-webapp


Run Test with remote mode
============================
1) start jboss
2) run test in eclipse

Run Test with remote mode in maven
============================
1) start jboss
2) run test in maven
mvn test -P test-remote

Run deploy in maven
============================
1) start jboss
2) run deploy in maven
mvn jboss-as:deploy
2	


mvn -Dwtpversion=2.0 eclipse:eclipse

