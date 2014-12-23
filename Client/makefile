JC=javac
JJC=jar
JFLAGS=-d build/
JJFLAGS=-cfm
CPATH=-cp :./lib/httpclient-4.3.6.jar:./lib/httpcore-4.3.3.jar:./lib/commons-codec-1.6.jar:./lib/commons-logging-1.1.3.jar:./lib/httpmime-4.3.6.jar:.lib/fluent-hc-4.3.6.jar:./lib/httpclient-cache-4.3.6.jar:./lib/webcam-capture-0.3.10.jar:./lib/bridj-0.6.2.jar:./lib/slf4j-api-1.7.2.jar:.
BUILD=build/
TARGET=LiteralLabView.jar
MAIN=LLW

jar: build Constants PostCreator PhotoCapture LLW manifest.txt
	$(JJC) $(JJFLAGS) $(TARGET) manifest.txt -C $(BUILD) .

LLW: LLW.java
	$(JC) $(JFLAGS) LLW.java $(CPATH)

PostCreator: PostCreator.java
	$(JC) $(JFLAGS) PostCreator.java $(CPATH)

PhotoCapture: PhotoCapture.java
	$(JC) $(JFLAGS) PhotoCapture.java $(CPATH)

Constants: Constants.java
	$(JC) $(JFLAGS) Constants.java $(CPATH)

build:
	mkdir $(BUILD)

clean:
	rm -rf $(BUILD)
	rm LiteralLabView.jar