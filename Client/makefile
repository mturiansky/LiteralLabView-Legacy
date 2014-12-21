JC=javac
JJC=jar
JFLAGS=-d build/
JJFLAGS=-cfe
BUILD=build/
TARGET=LiteralLabView.jar
MAIN=LLW

all: build LLW

jar: LLW
	$(JJC) $(JJFLAGS) $(TARGET) $(MAIN) -C $(BUILD) .

LLW: LLW.java
	$(JC) $(JFLAGS) LLW.java

build:
	mkdir $(BUILD)

clean:
	rm -rf $(BUILD)
	rm LiteralLabView.jar