build js-ews-api.jar and place it here

git submodule init
git submodule update 
cd ews-java-api
mvn package
cd ..
cp ews-java-api/target/ews-java-api-1.3.jar lib/
