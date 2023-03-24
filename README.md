## Running
```sh
# The sample .ipa file is split because of GH's 100 MB file size limit
cat src/main/resources/apps/YouTube_17.43.1_1668160730.ipa.segment* > src/main/resources/apps/YouTube_17.43.1_1668160730.ipa

JAVA_HOME=~/graalvm-ce-java11-22.3.1/ ./gradlew -PjvmArgs="-XX:MinHeapFreeRatio=10 -XX:MaxHeapFreeRatio=20  -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5011 -XX:+UseSerialGC -Xmx4096m -XX:NativeMemoryTracking=detail" bootRun
```