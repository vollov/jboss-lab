##set up pom.xml

mvn archetype:generate -DarchetypeGroupId=net.avh4.mvn.archetype -DarchetypeArtifactId=java-1.6-archetype

Define value for property 'groupId': : org.demo.wineshop <ENTER>
Define value for property 'artifactId': : wineshop <ENTER>
Define value for property 'version': : <ENTER>
Define value for property 'package': : <ENTER>
Confirm properties configuration:
groupId: org.demo.wineshop
artifactId: wineshop
version: 1.0-SNAPSHOT
package: org.arquillian.example
Y: : <ENTER>


## update maven pom.xml

	<build>
		<plugins>
			<plugin>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>2.9</version>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>2.3.2</version>
				<configuration>
					<source>1.6</source>
					<target>1.6</target>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.jboss.as.plugins</groupId>
				<artifactId>jboss-as-maven-plugin</artifactId>
				<version>${version.jboss.maven.plugin}</version>
				<configuration>
					<force>true</force>
					<id>${jboss.id}</id>
					<fileNames>
						<fileName>target/${plugin.war.warName}.war</fileName>
					</fileNames>
				</configuration>
				<executions>
					<execution>
						<phase>install</phase>
						<goals>
							<goal>deploy</goal>
						</goals>
						<configuration>
							<deployEnabled>true</deployEnabled>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
	
		<dependencies>

		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.8.1</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.jboss.arquillian.junit</groupId>
			<artifactId>arquillian-junit-container</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>