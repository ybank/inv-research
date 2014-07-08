## inv-research
============

This project serves for the purposes of the research into database of invariants. We would like to see how database of invariants can help programming tasks and in general, software engineering.

### Tool Status

YY: source code uploaded. 
 -- Surefire-helper (./src/main/java/surefire-helper) is the Daikon integration
 -- Surefire-provider (./src/main/java/surefire-junit4) is the modified surefire junit4 plugin
 
 After setting up local maven environment, go to any downloaded maven project, navigate to the directory where the pom.xml file is located and run the following command to see the contracts infered.
 
         mvn test org.apache.maven.plugins:maven-surefire-plugin:2.18-SNAPSHOT:test

 
