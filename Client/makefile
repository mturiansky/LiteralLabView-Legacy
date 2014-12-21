JC=javac
JJC=jar
JFLAGS=-d build/
JJFLAGS=-cfm
CPATH=-cp :./lib/httpclient-4.3.6.jar:./lib/httpcore-4.3.3.jar:./lib/commons-codec-1.6.jar:./lib/commons-logging-1.1.3.jar:.
BUILD=build/
TARGET=LiteralLabView.jar
MAIN=LLW

jar: build LLW manifest.txt
	$(JJC) $(JJFLAGS) $(TARGET) manifest.txt -C $(BUILD) .

LLW: LLW.java
	$(JC) $(JFLAGS) LLW.java $(CPATH)

build:
	mkdir $(BUILD)

clean:
	rm -rf $(BUILD)
	rm LiteralLabView.jar