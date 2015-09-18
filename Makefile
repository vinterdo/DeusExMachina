FORGE_VERSION=1.7.10-10.13.4.1448-1.7.10
FORGE_DOWNLOAD_SHA256=0417977515b7b243507f231ae4ac108bd8d7c8402baea25f5d682920e34083e8

all: get-forge setup clean

get-forge:
	curl -fSL -o forge-src.zip "http://files.minecraftforge.net/maven/net/minecraftforge/forge/${FORGE_VERSION}/forge-${FORGE_VERSION}-src.zip"
	echo "${FORGE_DOWNLOAD_SHA256} *forge-src.zip" | sha256sum -c -
	unzip -n -q forge-src.zip -d .

setup:
	chmod +x gradlew
	./gradlew setupDecompWorkspace eclipse

clean:
	-rm forge-src.zip
	-rm CREDITS-fml.txt
	-rm LICENSE-fml.txt
	-rm MinecraftForge-Credits.txt
	-rm MinecraftForge-License.txt
	-rm README.txt
	-rm forge-*-changelog.txt
	-rm -r src/main/java/com/example/
