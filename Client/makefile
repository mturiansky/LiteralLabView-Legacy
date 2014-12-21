JC=javac
JJC=jar
JFLAGS=-d build/
JJFLAGS=-cfm
CPATH=-cp :./lib/httpclient-4.3.6.jar:./lib/httpcore-4.3.3.jar:./lib/commons-codec-1.6.jar:./lib/commons-logging-1.1.3.jar:./lib/httpmime-4.3.6.jar:.lib/fluent-hc-4.3.6.jar:./lib/httpclient-cache-4.3.6.jar:.
BUILD=build/
TARGET=LiteralLabView.jar
MAIN=LLW

jar: build PostCreator LLW manifest.txt
	$(JJC) $(JJFLAGS) $(TARGET) manifest.txt -C $(BUILD) .

LLW: LLW.java
	$(JC) $(JFLAGS) LLW.java $(CPATH)

PostCreator: PostCreator.java
	$(JC) $(JFLAGS) PostCreator.java $(CPATH)

build:
	mkdir $(BUILD)

clean:
	rm -rf $(BUILD)
	rm LiteralLabView.jar